package com.company;


/**
 *The main class, which contains the main initializing method
 */
class Main {

    /**
     * The main method creates the other classes
     *
     * @param args arguments
     */
    public static void main(String[] args) {
        Model data = new Model();
        Control control = new Control(data);
        View view = new View(control);


    }
}
