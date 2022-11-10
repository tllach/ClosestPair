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

    public static void main(String[] args) throws FileNotFoundException {
        ClosestPair closestPair = new ClosestPair();
        closestPair.run(20);
    }
}


