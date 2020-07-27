package com.company;

import java.io.*;


/**
 *Contains the array of cells and other attributes
 */
public class Cells implements Serializable {
    /**
     * Grid colour
     */
    String Gcolour = "White";
    /**
     * Cell colour
     */
    String Ccolour = "Black";
    /**
     * Bacground color
     */
    String Bcolour = "Red";
    /**
     * arra of cells
     */
    boolean[][] cells;
    /**
     * size of cells
     */
    int size = 45;
    /**
     * only used for cropsave
     */
    int width=1;
    /**
     * only used for cropsave
     */
    int heigth=3;
    /**
     * Living cell count
     */
    int livecellcnt = 0;
    /**
     * Generations number
     */
    int generation = 0;


    /**
     * Creates the cells
     */
    Cells() {

        cells = new boolean[size][size];


    }


    /**
     * @param path file path
     *             Loads the table
     */
    void loadTable(String path) {
        Cells e;
        try {
            System.out.println(path);
            FileInputStream fileIn = path.contains(".ser") ? new FileInputStream(path) : new FileInputStream(path + ".ser");

            ObjectInputStream in = new ObjectInputStream(fileIn);
            e = (Cells) in.readObject();
            in.close();
            fileIn.close();
            this.Gcolour = e.Gcolour;
            this.Ccolour = e.Ccolour;
            this.Bcolour = e.Bcolour;
            this.size = e.size;
            this.livecellcnt = e.livecellcnt;
            this.generation = e.generation;
            this.cells=e.cells;
        } catch (IOException | ClassNotFoundException i) {
            i.printStackTrace();
        }

    }

    /**
     * @param path filepath
     *             saves the table
     */
    void savetable(String path) {
        try {
            FileOutputStream fileOut = path.contains(".ser") ? new FileOutputStream(path) : new FileOutputStream(path + ".ser");

            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

   }
