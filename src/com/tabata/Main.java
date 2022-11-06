package com.tabata;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        ClosestPair closestPair = new ClosestPair();
    }
}

class ClosestPair{

    ArrayList<List<Integer>> coords;
    double d_min;
    List<Integer> tempCoord1;
    List<Integer> tempCoord2;

    public ClosestPair(){

    }

    /**
     * Funcion que implementa el algoritmo de fuerzabruta
     * @param coords: lista de coordenadas
     */
    public void brutalForce(ArrayList<List<Integer>> coords){
        d_min = 123456789;
        int n = coords.size();
        double distance;

        for(int i = 0; i <= n-1; i++){
            for(int j = i+1; j <= n-1; j++){
                distance = distance(coords, i, j);
                if(distance < d_min){
                    tempCoord1 = coords.get(i);
                    tempCoord2 = coords.get(j);
                    d_min = distance;
                }
            }
        }
    }

    /**
     * Funcion que usa BrutalForce para hallar la distancia dado una lista de coordenadas
     * @param coord: lista de coordenadas
     * @param i
     * @param j
     */
    public double distance(ArrayList<List<Integer>> coord, int i , int j){

        int x1 = coord.get(i).get(0);
        int y1 = coord.get(i).get(1);

        int x2 = coord.get(j).get(0);
        int y2 = coord.get(j).get(1);

        double d = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
        return d;
    }


}