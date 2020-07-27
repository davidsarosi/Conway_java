package com.company;

import java.io.*;

/**
 * Class which contain a pattern
 */
public class Pattern implements Serializable {

    /**
     * array of cells
     */
    boolean [][] cells;
    /**
     * width pattenr
     */
    int width;
    /**
     * heigth of pattern
     */
    int heigth;

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

    /**
     * @param path filepath
     *             loads the table
     */
    void loadTable(String path) {
     Pattern e;
        try {
            System.out.println(path);
            FileInputStream fileIn = path.contains(".ser") ? new FileInputStream(path) : new FileInputStream(path + ".ser");

            ObjectInputStream in = new ObjectInputStream(fileIn);
            e = (Pattern) in.readObject();
            in.close();
            fileIn.close();
            this.cells = e.cells;
            this.width = e.width;
            this.heigth=e.heigth;
        } catch (IOException | ClassNotFoundException i) {
            i.printStackTrace();
        }

    }
}
