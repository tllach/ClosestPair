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


    /**
     *
     * References:
     * [0]: https://www.hackerearth.com/submission/75894682/?
     * @param //p
     * @return

    @Override
    public int compareTo(Point p){
        if(this.x < p.x){
            return -1;
        }else if(this.x == p.x){
            if(this.y < p.y){
                return -1;
            }else{
                return 1;
            }
        }
        return 1;
    }
    */

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