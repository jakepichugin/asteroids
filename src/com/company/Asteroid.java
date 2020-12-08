package com.company;

import java.awt.*;

public class Asteroid extends VectorSprite{

    private Polygon createAsteroidShape() {
        Polygon shape = new Polygon();
        shape.addPoint(0,-50);
        shape.addPoint(27, -40);
        shape.addPoint(35, -10);
        shape.addPoint( 27, 20);
        shape.addPoint(0, 30);
        shape.addPoint( -25, 10);
        shape.addPoint( -25, -30);
        return shape;
    }

    public Asteroid() {
        shape = createAsteroidShape();

        drawShape = createAsteroidShape();


        ROTATION = Math.random() / 4;
        System.out.println(ROTATION);
        THRUST = 1;

        double h, a;
        h = Math.random() + 3;
        a = Math.random() * (2 * Math.PI);
        xspeed = Math.cos(a) * h;
        yspeed = Math.sin(a) * h;

        h = Math.random() * 400 + 100;
        a = Math.random() * (2 * Math.PI);
        xposition = Math.cos(a) * h + 450;
        yposition = Math.sin(a) * h + 300;

        active = true;
    }

    public void updatePosition() {
        angle += ROTATION;
        super.updatePosition();
    }

}
