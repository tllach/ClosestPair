package com.tabata;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        ClosestPair closestPair = new ClosestPair(300);
    }
}

class ClosestPair{

    ArrayList<List<Integer>> coords;
    double d_min;
    List<Integer> tempCoord1;
    List<Integer> tempCoord2;
    Random rand;
    int numberCoords;
    int n, numOper;
    long elapsedTime;
    String filename;

    public ClosestPair(int number){
        filename = "pruebas.txt";
        rand = new Random();
        this.numberCoords = number;
        coords = new ArrayList<>();
        create(filename);
    }

    public void run() throws FileNotFoundException {
        PrintWriter out = new PrintWriter(filename);
        int j = 5;
        int i = (int) Math.pow(2, j);
        while(i <= Math.pow(2,20)){
            numOper = 0;
            j += 1;
            this.n = i;
            createCoordinates();
            divideAndConquer(coords);
            String line = n + " " + numOper + " " + elapsedTime;
            out.println(line);
            i = (int) Math.pow(2,j);
        }
        out.close();
    }

    public void divideAndConquer(ArrayList<List<Integer>> coords){

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

    /**
     * Funcion que crea las coordenadas aleatorias
     */
    public void createCoordinates(){
        int y = rand.nextInt(1000);
        for(int i = 0; i <= numberCoords; i++){
            if(i < numberCoords/2){
                int x = rand.nextInt(500);
                coords.add(Arrays.asList(x, y));
                continue;
            }
            //to make the range of the other half of coordinates be between 500 to 1000
            int x = (int)(Math.random()*(500) + 1000);
            coords.add(Arrays.asList(x, y));
        }
    }

    /**
     * Funcion encargada de imprimir la lista pasada por parametros
     * @param coords la lista que se va a imprimir
     */
    public void printList(ArrayList<List<Integer>> coords){
        coords.forEach(System.out::println);
    }

    /**
     * Funcion paara crear un archivo
     * @param fname nombre del archivo
     */
    private void create(String fname) {
        // creates a file with given name `filename'
        try {
            // creates a new File object
            File f = new File(fname);

            // creates the new file
            String msg = "creating file `" + fname + "' ... ";
            System.out.println();
            System.out.printf("%s", msg);
            f.createNewFile();
            System.out.println("done");

        } catch (IOException err) {
            // complains if there is an Input/Output Error
            System.out.println("IO Error:");
            err.printStackTrace();
        }

        return;
    }

}