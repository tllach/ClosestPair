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

    /* constructor */

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

}