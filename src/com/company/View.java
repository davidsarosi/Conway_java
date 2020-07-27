package com.company;

import javafx.scene.effect.BoxBlur;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * The main frame, which contains the other panels
 */
class View extends JFrame {

    /**
     *
     */
    private final Control control;
    /**
     * Colours, where the user can choose from
     */
    private final String[] Colours = {"Green", "Red", "Blue", "Yellow", "Black", "White"};
    /**
     * Stepping modes
     */
    private final String[] Stepping = {"Manual", "Auto", "Custom"};
    /**
     * The live cell counter label
     */
    JLabel livecellcnt;
    /**
     * The generation counter label
     */
    JLabel generations;
    /**
     * The main frame
     */
    private JPanel main;
    /**
     * The options frame
     */
    private JPanel options;
    /**
     * The game's frame
     */
    private JPanel game;
    /**
     * The size of the maintable
     */
    private int tablesize = 30;
    /**
     * The size of the table
     */
    private int size;
    /**
     * Boolean that indicates if the grid is active
     */
    private boolean grid = false;


    /**
     * @param control the event dispatcher class
     * Constructor intializes the other panels and the mainframe
     */
    View(Control control) {
        this.control = control;
        control.setFrame(this);


        initmain();
        initoptions();
        initgame();


        //control.loadmaintable();
        this.setResizable(false);
        this.setContentPane(main);

        control.startMainc();
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    /**
     * @param grid indicator
     * Setter of the grid
     */
    void setGrid(boolean grid) {
        this.grid = grid;
    }


    boolean getGrid(){
        return grid;
    }

    /**
     * @param size tablesize
     *             Size setter
     */
    void setSize(int size) {
        this.size = size;
    }

    public int getCellSize() {
        return size;
    }

    /**
     * @param tablesize size of the table
     *                  Tablesize setter
     */
    void setTablesize(int tablesize) {
        this.tablesize = tablesize;
    }


    /**
     * Sets the main panel as the the mainframes contentpane
     */
    void setMain() {
        this.setContentPane(main);
        this.pack();
        this.revalidate();
        this.repaint();
        this.setLocationRelativeTo(null);

    }

    /**
     * Sets the options panel as the the mainframes contentpane
     */
    void setOptions() {
        this.setContentPane(options);
        this.pack();
        this.revalidate();
        this.repaint();
        this.setLocationRelativeTo(null);
    }

    /**
     * Sets the game panel as the the mainframes contentpane
     */
    void setGame() {
        this.setContentPane(game);
        this.pack();
        this.revalidate();
        this.repaint();
        this.setLocationRelativeTo(null);
    }

    /**
     * Initializes the main panel
     */
    private void initmain() {

        main = new MainTable();


        JPanel box = new JPanel();


        //Creating menu elements
        JMenuBar menubar = new JMenuBar();
        JMenu menu = new JMenu("File");
        ArrayList<String>menutexts=new ArrayList<>();
        menutexts.add("Load");
        menutexts.add("Save");
        JMenuItem load = new JMenuItem(menutexts.get(0));
        JMenuItem save = new JMenuItem(menutexts.get(1));
        JMenu insert = new JMenu("Insert");
        save.setActionCommand("Save table");

        menubar.add(menu);
        menubar.add(insert);
        menu.add(load);
        menu.add(save);
        insert.addMenuListener(control);

        //Setting layouts
        main.setLayout(new GridBagLayout());
        box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));


        //Setting GridbagConstraints
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;


        // Creating and positioning of Buttons
        JButton start = new JButton("Start");
        JButton options = new JButton("Options");
        JButton quit = new JButton("Quit");


        start.setAlignmentX(Component.CENTER_ALIGNMENT);
        options.setAlignmentX(Component.CENTER_ALIGNMENT);
        quit.setAlignmentX(Component.CENTER_ALIGNMENT);


        // Adding butttons
        box.add(start);
        box.add(Box.createRigidArea(new Dimension(10, 10)));
        box.add(options);
        box.add(Box.createRigidArea(new Dimension(10, 10)));
        box.add(quit);


        //Adding action listeners

        start.addActionListener(control);
        options.addActionListener(control);
        quit.addActionListener(control);
        load.addActionListener(control);
        save.addActionListener(control);


        //Adding elements to frame
        this.setJMenuBar(menubar);
        main.add(box);


    }

    /**
     * Initializes the options panel
     */
    private void initoptions() {


        options = new JPanel();
        options.setPreferredSize(new Dimension(800, 600));
        JPanel grid = new JPanel();
        grid.setLayout(new GridBagLayout());


        JPanel cboxrow = new JPanel();
        JPanel cboxrow1 = new JPanel();
        JPanel cboxrow2 = new JPanel();
        JPanel cboxrow3 = new JPanel();
        JPanel Box = new JPanel();
        Box.setLayout(new BoxLayout(Box, BoxLayout.Y_AXIS));
        JLabel szin = new JLabel("Cell Colour:");
        JLabel lepes = new JLabel("Stepping:");
        JLabel Hszin = new JLabel("Background Colour:");
        JLabel Gszin = new JLabel("Grid Colour");
        cboxrow.setLayout(new FlowLayout(FlowLayout.CENTER));
        cboxrow1.setLayout(new FlowLayout(FlowLayout.CENTER));
        cboxrow2.setLayout(new FlowLayout(FlowLayout.CENTER));
        JComboBox<String> stepper = new JComboBox<>(Stepping);
        JComboBox<String> cellcolour = new JComboBox<>(Colours);
        JComboBox<String> backgroundcolour = new JComboBox<>(Colours);
        JComboBox<String> gridcolour = new JComboBox<>(Colours);
        control.setBcolour(backgroundcolour);
        control.setCcolour(cellcolour);
        control.setGcolour(gridcolour);


        JButton back = new JButton("Back");
        JButton save = new JButton("Save");
        JTextField interval = new JTextField();
        interval.setPreferredSize(new Dimension(50, 20));
        GridBagConstraints c = new GridBagConstraints();


        c.gridx = 1;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;

        options.add(grid);
        grid.add(Box, c);

        JLabel sizelabel = new JLabel("Set size:");
        JTextField size = new JTextField();
        control.setSize(size);
        size.setEditable(true);
        JPanel sizepanel = new JPanel();
        sizepanel.add(sizelabel);
        sizepanel.add(size);
        size.setPreferredSize(new Dimension(50, 20));


        //Cell colour
        cboxrow.add(szin);
        cboxrow.add(cellcolour);
        Box.add(cboxrow);

        //Background colour
        cboxrow1.add(Hszin);
        cboxrow1.add(backgroundcolour);
        Box.add(cboxrow1);

        //Grid Colour
        cboxrow2.add(Gszin);
        cboxrow2.add(gridcolour);
        Box.add(cboxrow2);

        //Stepping
        cboxrow3.add(lepes);
        cboxrow3.add(stepper);
        cboxrow3.add(interval);
        Box.add(cboxrow3);

        stepper.addItemListener(control);
        interval.setVisible(false);
        control.setInterval(interval);
        control.setStepping(stepper);


        //Size textfield
        Box.add(sizepanel);


        back.addActionListener(control);
        save.addActionListener(control);


        JPanel backpanel = new JPanel();
        backpanel.setLayout(new FlowLayout());
        backpanel.add(back);
        backpanel.add(save);
        c.insets = new Insets(50, 10, 10, 10);
        c.gridy = 2;
        c.gridx = 1;
        c.fill = 0;
        c.anchor = GridBagConstraints.CENTER;
        grid.add(backpanel, c);


    }


    /**
     * Initializes the game panel
     */
    private void initgame() {


        game = new JPanel();


        JPanel controls = new JPanel();
        JPanel undercont = new JPanel();
        JPanel stats = new JPanel();
        Table table = new Table();
        control.setTable(table);
        game.setLayout(new GridBagLayout());


        //creating JLabels
        livecellcnt = new JLabel();
        this.generations = new JLabel();


        GridBagConstraints c = new GridBagConstraints();

        //creating JButtons, Checkboxes,

        JButton random = new JButton("Random");
        JButton start = new JButton("Play");
        JButton stop = new JButton("Stop");
        JButton back = new JButton("Back");
        JCheckBox grid = new JCheckBox("Grid");


        back.addActionListener(control);
        start.addActionListener(control);
        stop.addActionListener(control);
        grid.addItemListener(control);
        random.addActionListener(control);


        controls.setLayout(new BoxLayout(controls, BoxLayout.X_AXIS));
        controls.add(start);
        controls.add(Box.createRigidArea(new Dimension(20, 0)));
        controls.add(stop);
        controls.add(Box.createRigidArea(new Dimension(20, 0)));
        controls.add(random);
        controls.add(grid);

        undercont.setLayout(new BoxLayout(undercont, BoxLayout.X_AXIS));
        undercont.add(back);

        stats.setLayout(new BoxLayout(stats, BoxLayout.X_AXIS));
        stats.add(livecellcnt);
        stats.add(Box.createRigidArea(new Dimension(50, 30)));
        stats.add(generations);

        JPanel tarolo = new JPanel();
        tarolo.setLayout(new BoxLayout(tarolo, BoxLayout.Y_AXIS));
        tarolo.add(controls);
        tarolo.add(Box.createRigidArea(new Dimension(20, 5)));
        tarolo.add(stats);
        tarolo.add(Box.createRigidArea(new Dimension(20, 5)));
        tarolo.add(undercont);

        c.gridy = 0;
        c.gridx = 0;
        c.fill = GridBagConstraints.BOTH;

        game.add(table, c);


        c.gridy = 1;
        c.gridx = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        game.add(tarolo, c);


    }

    public int getTableSize() {
        return tablesize;
    }


    /**
     * Class that displays the cells in the gamepanel
     */
    class Table extends JPanel {
        Color cszin = Color.RED;
        Color bszin = Color.BLACK;
        Color gszin = null;

        /**
         * Adds a mouse listener to the class
         */
        Table() {
            this.addMouseListener(control);
        }


        /**
         * @param color current selected color
         * @return the selected color
         * Converts the selected color from string to Color
         */
        Color setSzin(String color) {

            switch (color) {
                case "Green":
                    return Color.GREEN;

                case "Red":
                    return Color.RED;

                case "Blue":
                    return new Color(0, 0, 128);

                case "Black":
                    return Color.BLACK;

                case "Yellow":
                    return Color.YELLOW;

                case "White":
                    return Color.WHITE;

                case "Special":
                    return null;

                default:
                    return null;

            }

        }

        /**
         * @param g
         * @param cells array of the cells
         * @param cellc color of the cells
         *              draws the cells with the given color
         */
        void drawcells(Graphics g, boolean[][] cells, Color cellc) {
            for (int i = 0; i < size; i++)
                for (int j = 0; j < size; j++) {
                    if (cells[i][j]) {
                        g.setColor(cellc);
                        g.fillRect(j * 20, i * 20, 20, 20);

                    }
                }
        }

        /**
         * @param g
         * @param size size of the array
         * @param gridc color of the grid
         *              draws the grid with the given color
         */
        void drawgrid(Graphics g, int size, Color gridc) {
            g.setColor(gridc);
            for (int i = 0; i < size + 1; i++) {
                for (int j = 0; j < size + 1; j++) {
                    g.drawLine(j * 20, i * 20, size, i * 20);
                    g.drawLine(j * 20, i * 20, j * 20, size);


                }
            }

        }


        /**
         * @param g
         * Overridden paint
         */
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);


            cszin = setSzin(control.getcszin());
            bszin = setSzin(control.getbszin());
            gszin = setSzin(control.getgszin());

            this.setBackground(bszin);
            drawcells(g, control.getCells(), cszin);

            if (grid) {
                drawgrid(g, size, gszin);
            }
        }


        /**
         * @return the new tablesize
         */
        public Dimension getPreferredSize() {
            return new Dimension(tablesize, tablesize); // appropriate constants
        }
    }

    /**
     * Class which displays the game of life in the main menu's background
     */
    class MainTable extends JPanel {

        /**
         * Sets the maintable to this in control
         */
        MainTable() {
            control.setMainTable(this);

        }
        /**
         * @param g
         * @param cells array of the cells
         * @param cellc color of the cells
         *              draws the cells with the given color
         */
        void drawcell(Graphics g, boolean[][] cells, Color cellc, int size) {
            for (int i = 0; i < size; i++)
                for (int j = 0; j < size; j++) {
                    if (cells[i][j]) {
                        g.setColor(cellc);
                        g.fillRect(j * 20, i * 20, 20, 20);

                    }
                }
        }
        /**
         * @param g
         * @param size size of the array
         * @param gridc color of the grid
         *              draws the grid with the given color
         */
        void drawgrid(Graphics g, int size, Color gridc) {
            g.setColor(gridc);
            for (int i = 0; i < size + 1; i++)
                for (int j = 0; j < size + 1; j++) {
                    g.drawLine(j * 20, i * 20, size, i * 20);
                    g.drawLine(j * 20, i * 20, j * 20, size);
                }

        }

        /**
         * @param g
         * Overridden paint methid
         */
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            this.setBackground(new Color(162, 203, 255));
            drawcell(g, control.getMaincells(), new Color(0, 0, 128), control.getMainsize());
            drawgrid(g, control.getMainsize(), Color.BLACK);
        }

        /**
         * @return new Tablesize
         * Overridden getPreferredSize method
         */
        public Dimension getPreferredSize() {
            return new Dimension(control.getMainsize() * 20, control.getMainsize() * 20); // appropriate constants
        }
    }
}

