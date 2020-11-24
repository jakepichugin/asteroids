package com.company;

import java.awt.*;

public class Spacecraft extends VectorSprite{

    public Spacecraft() {
        ROTATION = 0.1;
        THRUST = 0.5;

        shape = new Polygon();
        shape.addPoint(15,0);
        shape.addPoint(-10, 10);
        shape.addPoint(-10, -10);

        drawShape = new Polygon();
        drawShape.addPoint(15,0);
        drawShape.addPoint(-10, 10);
        drawShape.addPoint(-10, -10);

        xposition = 450;
        yposition = 300;

    }

    public void accelerate() {

        xspeed += Math.cos(angle ) * THRUST;// HOW FAST you you zoom across nothingness
        yspeed += Math.sin(angle    ) * THRUST;
    }

    public void rotateLeft() {
        angle -= ROTATION;
    }
    public void rotateRight() {
        angle += ROTATION;
    }
}
