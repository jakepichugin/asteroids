package com.company;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.MalformedURLException;
import java.util.ArrayList;

public class Game extends JFrame implements KeyListener, ActionListener {

    public Menu mainMenu; // creates an sinstance of the menu class mainmenu
    public Window panel; //creates an instance of the window class called panel
    Spacecraft ship;// creats an instance of vectorsprite called ship
    boolean startGame = false;

    ArrayList<Asteroid> asteroidList; // a bunch of rocks
    ArrayList<Bullet> bulletsList;      // list of shooting things
    ArrayList<Debris> debrisList; // not the minecraft one
    double randomNumo; // randooo numbre

    Timer timer;
    double gameCounter;
    int wavesSpawned;

    Image offscreen; // an image to be loaded offscreen
    Graphics offg; // a graphics object to go along with offscreen image

    boolean upKey, rightKey, leftKey, spacekey; // fix the key locking

    int score; // keeps tack of current score

    AudioUtil spotipie; // a tool for utilisizing audio Functionality and stuff
    Clip laser; // Audio clip thats plays when we shoot
    Clip thruster;// sound of exalarations
    Clip explode;
    Clip wasted;
    Clip gamemusic;
    //this is the start of the game


    public void init() throws MalformedURLException {

        this.setVisible(true);
        this.setSize(900, 600);
        this.setTitle("Asteroids!!! By the great JAKY JAKE");
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.addKeyListener(this);

        offscreen = createImage(this.getWidth(), this.getHeight());
        offg = offscreen.getGraphics();
        ship = new Spacecraft();
        asteroidList = new ArrayList();

        bulletsList = new ArrayList();
        debrisList = new ArrayList();
        score = 0;

        add(this.mainMenu = new Menu(this), BorderLayout.CENTER);//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        pack();

        timer = new Timer(20, this);
        timer.start();
        gameCounter = 0;
        wavesSpawned = 0;

        spotipie = AudioUtil.getInstance();
        laser = spotipie.readSoundFile("./src/sounds/laser79.wav");
        thruster = spotipie.readSoundFile("./src/sounds/thruster.wav");
        explode = spotipie.readSoundFile("./src/sounds/explode0.wav");
        wasted = spotipie.readSoundFile("./src/sounds/explode0.wav");
//        gamemusic = spotipie.readSoundFile();
    }


    public void update(Graphics g) {
        paint(g);
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            rightKey = true;

        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            leftKey = true;


        }

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            upKey = true;


        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            spacekey = true;
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            rightKey = false;

        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            leftKey = false;


        }

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            upKey = false;


        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            spacekey = false;
        }

    }

    private void keyCheck() {
        if (rightKey == true) {
            ship.rotateRight();
        }
        if (leftKey == true) {
            ship.rotateLeft();
        }
        if (upKey == true) {
            ship.accelerate();
            if (ship.active ==true) {
                spotipie.playSound(thruster);
            }

            // make the back thruster go brrr
            randomNumo = Math.random() * 50 + 70; //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            for (int fart = 0; fart < randomNumo; fart++) {
                debrisList.add(new Debris(ship.xposition, ship.yposition));
            }

        }
        if (spacekey == true) {
            fireBullet();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (startGame) {
            remove(this.mainMenu);
            add(this.panel = new Window(this), BorderLayout.CENTER);
            pack();
            ship.lives = 3;
            ship.active = true;

            for (int i = 0; i < 6; i++) { // 8 is max that is possible or maby 9!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                asteroidList.add(new Asteroid());
            }
            startGame = false;

        }

        keyCheck();
        repsawnShip();
        ship.updatePosition();
        gameCounter++;
        //adding waves of big rocks that hurt
        if (gameCounter > 500 && asteroidList.isEmpty() == false) {
            asteroidList.add(new Asteroid(0, 0, 3));
            wavesSpawned++;
            if (wavesSpawned > 2) {
                asteroidList.add(new Asteroid(0,0,3));
                asteroidList.add(new Asteroid(0,0,3));
            }
            if (wavesSpawned > 5) {
                asteroidList.add(new Asteroid(0,0,3));
                asteroidList.add(new Asteroid(0,0,3));
            }

            gameCounter = 0;
        }

        for (int i = 0; i < asteroidList.size(); i++) {
            asteroidList.get(i).updatePosition();
        }
        for (int i = 0; i < bulletsList.size(); i++) {
            bulletsList.get(i).updatePosition();
            if (bulletsList.get(i).counter == 61 || !bulletsList.get(i).active) {
                bulletsList.remove(i);
            }

        }
        for (int i = 0; i < debrisList.size(); i++) {
            debrisList.get(i).updatePosition();
            if (debrisList.get(i).counter == 50) {
                debrisList.remove(i);
            }

        }
        checkCollisions();
        checkAsteroidDestruction();

    }

    public boolean collision(VectorSprite thing1, VectorSprite thing2) {

        int x, y;

        for (int i = 0; i < thing1.drawShape.npoints; i++) {

            x = thing1.drawShape.xpoints[i];
            y = thing1.drawShape.ypoints[i];

            if (thing2.drawShape.contains(x, y)) {
                return true;

            }

        }

        for (int i = 0; i < thing2.drawShape.npoints; i++) {

            x = thing2.drawShape.xpoints[i];
            y = thing2.drawShape.ypoints[i];

            if (thing1.drawShape.contains(x, y)) {
                return true;
            }

        }
        return false;
    }

    public void checkCollisions() {

        for (int i = 0; i < asteroidList.size(); i++) {
            if (collision(ship, asteroidList.get(i)) && ship.active) {
                score -= 5;
                ship.hit();
                randomNumo = Math.random() * 50 + 70; //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                for (int fart = 0; fart < randomNumo; fart++) {
                    debrisList.add(new Debris(ship.xposition, ship.yposition));
                    spotipie.playSound(explode);
                }
            }
            for (int uh = 0; uh < bulletsList.size(); uh++) {
                if (collision(bulletsList.get(uh), asteroidList.get(i))) {
                    score += 1;
                    bulletsList.get(uh).active = false;
                    asteroidList.get(i).active = false;
                    randomNumo = Math.random() * 50 + 70; //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                    for (int fart = 0; fart < randomNumo; fart++) {
                        debrisList.add(new Debris(asteroidList.get(i).xposition, asteroidList.get(i).yposition));
                        spotipie.playSound(explode);
                    }

                }
            }
        }
    }

    public void repsawnShip() {
        if (ship.active == false && ship.counter > 20 && isRespawnSafe() && ship.lives > 0) {
            ship.reset();
        }
    }

    public boolean isRespawnSafe() {
        int x, y, h;
        for (int i = 0; i < asteroidList.size(); i++) {
            x = (int) (asteroidList.get(i).xposition - 450);
            y = (int) (asteroidList.get(i).yposition - 300);
            h = (int) Math.sqrt((x * x) + (y * y));
            if (h < 100) {
                return false;
            }

        }
        return true;
    }

    public void fireBullet() {
        if (ship.counter > 7 && ship.active) { //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            bulletsList.add(new Bullet(ship.drawShape.xpoints[0], ship.drawShape.ypoints[0], ship.angle));
            ship.counter = 0;
            spotipie.playSound(laser);
        }
    }

    public void checkAsteroidDestruction() {
        for (int i = 0; i < asteroidList.size(); i++)
            if (asteroidList.get(i).active == false) {
                if (asteroidList.get(i).size > 1) {
                    asteroidList.add(new Asteroid(asteroidList.get(i).xposition, asteroidList.get(i).yposition, asteroidList.get(i).size - 1));
                    asteroidList.add(new Asteroid(asteroidList.get(i).xposition, asteroidList.get(i).yposition, asteroidList.get(i).size - 1));
                }
                asteroidList.remove(i);
            }
    }
}