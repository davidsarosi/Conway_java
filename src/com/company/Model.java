package com.company;



import java.util.Random;

/**
 * The class that contains all the required data for the program
 */
class Model {

    final transient private int mainsize = 51;
    /**
     * Number of generations
     */
    int generations = 0;
    /**
     * Class for the gametalbe
     */
    Cells gametable;
    /**
     * Class for the maintable
     */
    Cells maintable;
    /**
     * Living cell count
     */
    int livecellcnt = 0;
    /**
     * Is the stepping automatic or custom
     */
    boolean isauto = false;
    /**
     * Cell colour
     */
    private String Ccolour = "Black";         // to do delete static implement control setter getter;
    /**
     * Background colour
     */
    private String Bcolour = "Red";
    /**
     * Grid colour
     */
    private String Gcolour = "White";
    // private int size = 51;
    /**
     * Interval of the stepping
     */
     private double time = 1;
    /**
     * Grid indicator
     */
     private boolean grid = false;


    /**
     * Creates the Cells instances and loads
     */
    Model() {
        maintable = new Cells();
        gametable = new Cells();
        random(maintable);
        // random(gametable);


    }

    /**
     * @return array of cells
     * Getter for the gamecells
     */
    boolean[][] getCells() {
        return gametable.cells;
    }
    /**
     * @return array of cells
     * Getter for the maincells
     */
    boolean[][] getMaincells() {
        return maintable.cells;
    }


    /**
     * @param path Absolute path to a file
     *             Calls the gametable's savetable
     */
    void savetable(String path) {
        gametable.savetable(path);
    }


    /**
     * @param meret Paramaters from the gui
     * @param backgroundc Paramaters from the gui
     * @param cellc Paramaters from the gui
     * @param gridc Paramaters from the gui
     * @param stepper Paramaters from the gui
     * @param stepfield Paramaters from the gui
     *                  Sets the gametables attributes by the given paramaters from the GUI
     */
    void save(Object meret, Object backgroundc, Object cellc, Object gridc, Object stepper, Object stepfield) {


        if (!meret.equals("")) {
            gametable.size = Integer.parseInt((String) meret);
        }
        if (stepfield != null && !stepfield.toString().isEmpty()) {
            time = Double.parseDouble((String) stepfield);
        }
        gametable.Ccolour = (String) cellc;
        gametable.Bcolour = (String) backgroundc;
        gametable.Gcolour = (String) gridc;


        gametable.cells = new boolean[gametable.size][gametable.size];

    }

    /**
     * @param cells array of cells
     * @param l Y coordinate of cell
     * @param k x coordinate of cell
     * @param size tablesize
     * @return number of the cells neighboors
     *
     * Calculates the number of the cells neighbours
     */
    private int ncount(boolean[][] cells, int l, int k, int size) {
        int nc = 0;

        for (int i = l - 1; i < l + 2; i++)
            for (int j = k - 1; j < k + 2; j++) {
                int x = (i % size + size) % size;
                int y = (j % size + size) % size;
                if (cells[x][y])
                    nc++;
            }


        return cells[l][k] ? (nc - 1) : nc;

    }


    /**
     * @param cells Cells class
     *              Calculates the next generation
     */
    void change(Cells cells) {
        boolean[][] helpercells = new boolean[cells.size][cells.size];
        cells.livecellcnt = 0;
        for (int i = 0; i < cells.size; i++)
            for (int j = 0; j < cells.size; j++) {
                int szomszedok = ncount(cells.cells, i, j, cells.size);

                switch (szomszedok) {
                    case 2:
                        if (cells.cells[i][j]) {
                            helpercells[i][j] = true;
                            cells.livecellcnt++;
                            break;
                        } else {
                            helpercells[i][j] = false;
                            break;
                        }
                    case 3:
                        helpercells[i][j] = true;
                        cells.livecellcnt++;
                        break;

                    default:
                        helpercells[i][j] = false;
                        break;
                }
            }

        for (int i = 0; i < cells.size; i++) {
            for (int j = 0; j < cells.size; j++) {
                cells.cells[i][j] = helpercells[i][j];
            }
        }
        cells.generation++;

    }


// --Commented out by Inspection (2018. 11. 14. 19:27):void setMaincells(boolean [][] maincells){this.maincells=maincells;}


    /**
     * @param cells array of cells
     * @return
     */
    int getSize(Cells cells) {

        return cells.size;
    }


    /**
     * @return Stepping interval
     * Returns 1 or time by the given value of isauto
     */
    double getTime() {
        if (isauto)
            return 1;
        else
            return time;

    }


    /**
     * @return Is the grid active
     * Returns if there is a grid or not
     */
    boolean getGrid() {
        return grid;

    }

    /**
     * @param grid
     * Set the value of grid
     */
    void setGrid(boolean grid) {
        this.grid = grid;
    }


    /**
     * @param cells
     * Fills the cells with random values
     */
    void random(Cells cells) {

        cells.livecellcnt = 0;
        cells.generation = 0;

        Random x = new Random();
        for (int i = 0; i < cells.size; i++)
            for (int j = 0; j < cells.size; j++) {
                cells.cells[i][j] = (x.nextInt(10) + 1) < 5;
                if (cells.cells[i][j])
                    cells.livecellcnt++;
            }

    }

    /**
     * @param Cells
     * Crops the pattern from the table and saves it
     */
    void cropSave(Cells Cells) {
        int minx = Cells.size;
        int maxx = 0;
        int miny = Cells.size;
        int maxy = 0;

        for (int i = 0; i < Cells.size; i++)
            for (int j = 0; j < Cells.size; j++) {
                if (Cells.cells[i][j]) {
                    minx = j < minx ? j : minx;
                    miny = i < miny ? i : miny;
                }
            }


        for (int i = 0; i < Cells.size; i++)
            for (int j = 0; j < Cells.size; j++) {
                if (Cells.cells[i][j]) {
                    maxx = j > maxx ? j : maxx;
                    maxy = i > maxy ? i : maxy;
                }
            }

        System.out.println("MAX X:" + maxx);
        System.out.println("MAX Y:" + maxy);
        System.out.println("MIN X:" + minx);
        System.out.println("MIN Y:" + miny);

        Pattern savedPattern = new Pattern();
        savedPattern.cells = new boolean[maxy - miny + 1][maxx - minx + 1];
        savedPattern.width = maxx - minx + 1;
        savedPattern.heigth = maxy - miny + 1;

        System.out.println(savedPattern.heigth);
        System.out.println(savedPattern.width);


        for (int y = miny; y < savedPattern.heigth + miny; y++)
            for (int x = minx; x < savedPattern.width + minx; x++)
                savedPattern.cells[y-miny][x-minx] = Cells.cells[y][x];

            savedPattern.savetable("Gliders\\Ligthweight Spaceship.ser");
    }

    public boolean isGrid() {
        return grid;
    }

    public String getCcolour() {
        return Ccolour;
    }

    public String getBcolour() {
        return Bcolour;
    }

    public String getGcolour() {
        return Gcolour;
    }
}
