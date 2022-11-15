package com.tabata;

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
