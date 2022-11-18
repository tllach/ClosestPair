package com.tabata;

/*
 * Algoritmos Y Complejidad                                 18/11/22
 * Tabata Llach Bruges
 *
 * Class:
 *     Class made for the time management
 */

public class Time_Managment {

    /* data members */
    private double elapsedTime;
    private long start;
    private long end;

    /* constructor */
    public Time_Managment() {
    }

    public void startTime(){
        this.start = System.nanoTime();
    }

    public void stopTime(){
        this.end = System.nanoTime();
    }

    /* getters */

    public double getElapsedTime(){
        elapsedTime = end - start;
        return elapsedTime;
    }

}
