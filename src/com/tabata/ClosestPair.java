package com.tabata;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

class ClosestPair{

    ArrayList<List<Integer>> coords;
    double d_min;
    List<Integer> tempCoord1;
    List<Integer> tempCoord2;
    ArrayList<List<Integer>> Px;
    Random rand;
    int n, numOper;
    long elapsedTime;
    String filename;
    ArrayList<ArrayList<List<Integer>>> closetCoords;
    ArrayList<Double> distanceClosetCoords;
    boolean isDividing;

    public ClosestPair(){
        filename = "pruebas.txt";
        rand = new Random();
        coords = new ArrayList<>();
        isDividing = false;
        create(filename);
    }

    /**
     * Funcion que ejecuta todo el codigo
     * @param nmax max exponent of 2^n which gives total of coordinates that will be created
     * @throws FileNotFoundException
     */
    public void run(int nmax) throws FileNotFoundException {
        PrintWriter out = new PrintWriter(filename);
        int j = 5;
        int i = (int) Math.pow(2, j);
        while(i <= Math.pow(2,nmax)){
            numOper = 0;
            j += 1;
            this.n = i;
            createCoordinates();
            closetCoords = new ArrayList<>();
            distanceClosetCoords = new ArrayList<>();
            divideAndConquer(coords);
            String line = n + " " + numOper + " " + elapsedTime;
            out.println(line);
            i = (int) Math.pow(2,j);
        }
        out.close();
    }

    /**
     * Funcion que implementa el algoritmo divideAndConquer
     * @param coords lista de coordenadas a dividir
     */
    public void divideAndConquer(ArrayList<List<Integer>> coords){
        long start = System.nanoTime();

        ArrayList<List<Integer>> Lx;
        ArrayList<List<Integer>> Rx;

        if(!isDividing){
            Px = sorting(coords);
        }else{
            Px = coords;
        }


        int size = Px.size();
        Lx = new ArrayList<>(Px.subList(0, size / 2));
        Rx = new ArrayList<>(Px.subList(size / 2, size));

        //list created just to iterate between List
        ArrayList<ArrayList<List<Integer>>> lists = new ArrayList<>();
        lists.add(Lx);
        lists.add(Rx);

        tempCoord1 = new ArrayList<>();
        tempCoord2 = new ArrayList<>();

        for(ArrayList<List<Integer>> list: lists){
            //checking size of the list
            if(list.size() <= 3){
                isDividing = false; //to not sort what is already sorted
                brutalForce(list);
                ArrayList<List<Integer>> pairOfCoords = new ArrayList<>();
                pairOfCoords.add(tempCoord1);
                pairOfCoords.add(tempCoord2);
                closetCoords.add(pairOfCoords);
                distanceClosetCoords.add(d_min);
            }else{
                isDividing = true;
                divideAndConquer(list);
            }
        }
        long end = System.nanoTime();
        elapsedTime = end - start;
        numOper = 2*n;
    }

    /**
     * Funcion sort la lista de coordenadas dada
     * @param coords: lista de coordenadas
     */
    public ArrayList<List<Integer>> sorting(ArrayList<List<Integer>> coords) {
        int x = 0; int y = 1;
        ArrayList<List<Integer>> organizedCoords = new ArrayList<>();

        for(List<Integer> coord: coords) {
            //for the first time
            if (organizedCoords.isEmpty()) {
                organizedCoords.add(coord);
            } else {
                int i = 1;
                int size = organizedCoords.size();
                List<Integer> coordBefore = organizedCoords.get((size - i));
                //to check for x primarly and then check for y
                while (coordBefore.get(x) >= coord.get(x)) {
                    i++;
                    //in case x are equals, checks for y secondly
                    if (coordBefore.get(x).equals(coord.get(x))) {
                        if (coordBefore.get(y) > coord.get(y)) {
                            coordBefore = organizedCoords.get((size - i + 1));
                        } else {
                            i--;
                            break;
                        }
                    }
                    //in case the "new coord" have to be in the first position
                    if (size - i == -1) {
                        break;
                    }
                    //in case x of coordBefore is > than "new coord" x
                    if (coordBefore.get(x) > coord.get(x)) {
                        coordBefore = organizedCoords.get((size - i));
                    }
                }
                organizedCoords.add(size - i + 1, coord);
            }
        }
        return organizedCoords;
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
     * @param coord lista de coordenadas
     * @param i
     * @param j
     */
    public double distance(ArrayList<List<Integer>> coord, int i , int j){

        int x1 = coord.get(i).get(0);
        int y1 = coord.get(i).get(1);

        int x2 = coord.get(j).get(0);
        int y2 = coord.get(j).get(1);

        return  Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
    }

    /**
     * Funcion que crea las coordenadas aleatorias
     */
    public void createCoordinates(){
        int y = rand.nextInt(1000);
        for(int i = 0; i <= n; i++){
            if(i < n/2){
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
     * Funcion para crear un archivo
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