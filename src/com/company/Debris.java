package com.company;

import java.awt.*;

public class Debris extends VectorSprite{



    public Debris(double x, double y, double angle) {

        shape = new Polygon();

        shape.addPoint(1,1);
        shape.addPoint(-1,-1);
        shape.addPoint(-1,-1);
        shape.addPoint(1,-1);

        drawShape = new Polygon();

        drawShape.addPoint(1,1);
        drawShape.addPoint(-1,-1);
        drawShape.addPoint(-1,-1);
        drawShape.addPoint(1,-1);

        xposition = x;
        yposition = y;



        double h, a;
        h = Math.random() + 1;
        if (angle == 361) { // when we have debris
            a = Math.random() * (2 * Math.PI);

        }
        else { // when we have thruster
            a = Math.random() + (angle - Math.PI / 4 + Math.PI);
        }
        xspeed = Math.cos(a) * h;
        yspeed = Math.sin(a) * h;

        active = true;

    }

}
