package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends JFrame implements KeyListener, ActionListener {

    public Window panel; //creates an instance of the window class called panel
    Spacecraft ship;// creats an instance of vectorsprite called ship
    Asteroid rock;
    Timer timer;
    Image offscreen; // an image to be loaded offscreen
    Graphics offg; // a graphics object to go along with offscreen image
    boolean upKey, rightKey, leftKey; // fix the key locking
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
        rock = new Asteroid();
        add(this.panel = new Window(this), BorderLayout.CENTER);
        pack();
        timer = new Timer(20, this);
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

    }

    private void keyCheck() {
        if(rightKey == true) {
            ship.rotateRight();
        }
        if(leftKey == true) {
            ship.rotateLeft();
        }
        if(upKey == true) {
            ship.accelerate();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        keyCheck();
        ship.updatePosition();
        rock.updatePosition();
        checkCollisions();
        repsawnShip();
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

        if (collision(ship, rock)) {
            ship.hit();
        }

    }

    public void repsawnShip() {
        if (ship.active == false) {
            ship.reset();
        }
    }

}
