package com.tabata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
 * Algoritmos Y Complejidad                                 18/11/22
 * Tabata Llach Bruges
 *
 * Class:
 *     implementation of the algorithm Divide And Conquer
 */

public class DivideAndConquer {

    /* data members */
    private int numOperations;
    private double elapsedTime;
    private ArrayList<Pair> closestPairs;

    public DivideAndConquer(){
        closestPairs = new ArrayList<>();
    }

    /**
     * Funcion que ejecuta el codugo de BrutalForce
     * @param coords dataset
     * @param n tamaño de las coordendas
     */
    public void run(ArrayList<Point> coords, int n){
        Time_Managment time = new Time_Managment();
        elapsedTime = 0;
        numOperations = 0;
        time.startTime();
        divideAndConquer(coords, n);
        findTheClosestPoint(closestPairs);
        time.stopTime();
        elapsedTime = time.getElapsedTime();
    }

    /**
     * Funcion que implementa el algoritmo divideAndConquer
     * @param coords lista de coordenadas a dividir
     */
    private void divideAndConquer(ArrayList<Point> coords, int n){
        BrutalForce brutalForce = new BrutalForce();
        ArrayList<Point> Lx = new ArrayList<Point>(coords.subList(0, n / 2));
        ArrayList<Point> Rx = new ArrayList<Point>(coords.subList(n / 2, n));

        //list created just to iterate between List
        ArrayList<ArrayList<Point>> lists = new ArrayList<>();
        lists.add(Lx);
        lists.add(Rx);

        for(ArrayList<Point> list: lists){
            int size = list.size();
            //checking size of the list
            if(size <= 3){
                closestPairs.add(brutalForce.run(list, size));
            }else{
                divideAndConquer(list, size);
            }
        }
        numOperations = n;
    }

    /**
     * Funcion que encuentra la distancia menor para hhalar el par mas cercano
     * @param coords dataset
     * References:
     *  [0]: https://www.geeksforgeeks.org/comparator-comparingdouble-method-in-java-with-examples/
     */
    private void findTheClosestPoint(ArrayList<Pair> coords){
        Collections.sort(coords, Comparator.comparingDouble(Pair::getDistance));
    }

    /* getters */
    public int getNumOperations(){
        return numOperations;
    }

    public double getElapsedTime(){
        return elapsedTime;
    }

    public void printList(ArrayList<Point> coords){
        for(Point p: coords){
            System.out.println("[" + p.getX() + "," + p.getY() + "]");
        }
        System.out.println("-------------------");
    }
}
