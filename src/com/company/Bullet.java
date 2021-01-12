package com.company;

import java.awt.*;

public class Bullet extends VectorSprite{
    public Bullet(double shipX,double shipY, double shipAngle) {
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

        xposition = shipX;
        yposition = shipY;
        angle = shipAngle;


        THRUST = 15;
        xspeed = Math.cos(angle) * THRUST;
        yspeed = Math.sin(angle) * THRUST;

        active = true;



    }
}
