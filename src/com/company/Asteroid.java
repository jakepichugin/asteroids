package com.company;

import java.awt.*;

public class Asteroid extends VectorSprite{

    public Asteroid() {
        shape = new Polygon();
        shape.addPoint(30,5);
        shape.addPoint(5, 20);
        shape.addPoint(-25, 10);
        shape.addPoint( -17, -15);
        shape.addPoint(10, -20);
        shape.addPoint( 30, -10);

//        shape.addPoint(0,-20);
//        shape.addPoint(12, -20);
//        shape.addPoint(15, -15);
//        shape.addPoint( 12, 10);
//        shape.addPoint(2, 15);
//        shape.addPoint( -10, 6);
//        shape.addPoint( -10, -25);

        drawShape = new Polygon();
        drawShape.addPoint(30,5);
        drawShape.addPoint(5, 20);
        drawShape.addPoint(-25, 10);
        drawShape.addPoint( -17, -15);
        drawShape.addPoint(20, -35);
        drawShape.addPoint( 30, -10);



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
