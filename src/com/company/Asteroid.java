package com.company;

import java.awt.*;

public class Asteroid extends VectorSprite{

    int size;

    private Polygon createAsteroidShape() {
        Polygon shape = new Polygon();
        shape.addPoint(0 * size,-25 * size);
        shape.addPoint(13 * size, -20 * size);
        shape.addPoint(17 * size, -5 * size);
        shape.addPoint( 13 * size, 10 * size);
        shape.addPoint(0 * size, 15 * size);
        shape.addPoint( -12 * size, 5 * size);
        shape.addPoint( -12 * size, -15 * size);
        return shape;
    }

    public Asteroid() {
        size = 3;
        initiolizeAsteroid();

    }

    public Asteroid(double x, double y, int s) {
        size = s;
        initiolizeAsteroid();
        xposition = x;
        yposition = y;

    }

    public void initiolizeAsteroid() {
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
