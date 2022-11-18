package com.tabata;

/*
 * Algoritmos Y Complejidad                                 10/11/22
 * Tabata Llach Bruges
 *
 * Class:
 *     implementation of a point in a cartesian coordinate system
 */

public final class Point{

    /* data members */
    private final int x;    //x-axis coordinate
    private final int y;    //y-axis coordinate

    /* constructors */

    public Point(){
        this.x = 0;
        this.y = 0;
    }

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    /*  Getters */
    public int getX(){  //returns value of X
        return this.x;
    }

    public int getY(){  //returns value of Y
        return this.y;
    }

    public double getDistance(Point p){
        return Math.sqrt(((this.x - p.getX()) * (this.x - p.getX()) + (this.y - p.getY()) * (this.y - p.getY())));
    }
}