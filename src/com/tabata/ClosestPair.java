package com.tabata;

import java.io.FileNotFoundException;
import java.util.*;

class ClosestPair{

    public ClosestPair(){ }

    /**
     * Funcion que ejecuta todo el codigo
     * @param nmax max exponent of 2^n which gives total of coordinates that will be created
     * @throws FileNotFoundException
     */
    public void run(int nmax) throws FileNotFoundException {
        DivideAndConquer divAndConq = new DivideAndConquer();
        File_Managment file = new File_Managment("pruebas.txt");
        file.openWriter();
        int j = 4;
        int n = (int) Math.pow(2, j);
        while(n <= Math.pow(2,nmax)){
            //System.out.println(n);
            ArrayList<Point> coords = createCoordinates(n);
            coords = sort(coords);
            divAndConq.run(coords, n);
            int numOper = divAndConq.getNumOperations();
            double elapsedTime = divAndConq.getElapsedTime();
            String line = n  + " " + elapsedTime + " " + numOper;
            file.write(line);
            n = (int) Math.pow(2,++j);
        }
        file.closeWriter();
    }

    /**
     * Funcion que crea el data set de las coordenadas
     * @param n: nro total de coordenadas que se crearan
     * @return lista de coordenadas a usar
     */
    public ArrayList<Point> createCoordinates(int n){
        ArrayList<Point> coords = new ArrayList<>();
        Random rand = new Random();
        for(int i = 0; i < n; i++){
            int y = rand.nextInt(11);
            if(i < n/2){
                int x = rand.nextInt(11);
                coords.add(new Point(x,y));
                continue;
            }
            //to make the range of the other half of coordinates be between 10 to 20
            int x = (int)(Math.random()*10 + 11);
            coords.add(new Point(x,y));
        }
        return coords;
    }

    /**
     * Funcion que sort dado un dataset
     * @param coords dataset a organizar
     * @return coords sorted
     */
    public ArrayList<Point> sort(ArrayList<Point> coords){
        Collections.sort(coords ,new Comparator<Point>(){
            public int compare(Point p1, Point p2){
                return Integer.valueOf(p1.getX()).compareTo(p2.getX());
            }
        });
        return coords;
    }

    /**
     * Funcion encargada de imprimir la lista pasada por parametros
     * @param coords la lista que se va a imprimir
     */
    public void printList(ArrayList<Point> coords){
        for(Point p: coords){
            System.out.println("[" + p.getX() + "," + p.getY() + "]");
        }
        System.out.println("-------------------");
    }
}