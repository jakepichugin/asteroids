package com.company;

import java.awt.*;

public class Debris extends VectorSprite{



    public Debris(double x, double y) {

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
        a = Math.random() * (2 * Math.PI);
        xspeed = Math.cos(a) * h;
        yspeed = Math.sin(a) * h;

        active = true;

    }

}
