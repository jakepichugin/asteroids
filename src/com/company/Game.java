package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Game extends JFrame implements KeyListener, ActionListener {

    public Window panel; //creates an instance of the window class called panel
    Spacecraft ship;// creats an instance of vectorsprite called ship

    ArrayList<Asteroid> asteroidList; // a bunch of rocks

    ArrayList<Bullet> bulletsList;      // list of shooting things
    Timer timer;
    Image offscreen; // an image to be loaded offscreen
    Graphics offg; // a graphics object to go along with offscreen image
    boolean upKey, rightKey, leftKey, spacekey; // fix the key locking
    int score; // keeps tack of current score
    //this is the start of the game



    public void init() {

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
        for (int i = 0; i < 6; i++) { // 8 is max that is possible or maby 9!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            asteroidList.add(new Asteroid());
        }
        bulletsList = new ArrayList();

        score = 0;

        add(this.panel = new Window(this), BorderLayout.CENTER);
        pack();
        timer = new Timer(20,   this);
        timer.start();


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
        }
        if (spacekey == true) {
           fireBullet();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        keyCheck();
        repsawnShip();
        ship.updatePosition();

        for (int i = 0; i < asteroidList.size(); i++) {
            asteroidList.get(i).updatePosition();
        }
            for (int i = 0; i < bulletsList.size(); i++) {
                bulletsList.get(i).updatePosition();
                if (bulletsList.get(i).counter == 61 || bulletsList.get(i).active == false) {
                    bulletsList.remove(i);
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
                }
                for (int uh = 0; uh < bulletsList.size(); uh++) {
                    if (collision(bulletsList.get(uh), asteroidList.get(i))) {
                        score += 1;
                        bulletsList.get(uh).active = false;
                        asteroidList.get(i).active = false;


                    }
                }
            }
        }

        public void repsawnShip() {
            if (ship.active == false && ship.counter > 20 && isRespawnSafe()) {
                ship.reset();
            }
        }

        public boolean isRespawnSafe() {
            int x, y, h;
            for (int i = 0; i < asteroidList.size(); i++) {
                x = (int) (asteroidList.get(i).xposition - 450);
                y = (int) (asteroidList.get(i).yposition - 300);
                h = (int) Math.sqrt((x * x) + (y * y));
                if (h < 70) {
                return false;
            }

        }
        return true;
    }

    public void fireBullet() {
        if (ship.counter > 10 && ship.active) { //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            bulletsList.add(new Bullet(ship.drawShape.xpoints[0], ship.drawShape.ypoints[0], ship.angle));
            ship.counter = 0;
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