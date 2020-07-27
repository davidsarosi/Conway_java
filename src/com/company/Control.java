package com.company;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.event.MouseInputListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.*;


/**
 * Handles the actionevents from the gui
 */
class Control extends Thread implements ActionListener, ItemListener, MouseInputListener, MenuListener {

    /**
     * Main combobox
     */
    JComboBox mainComboBox;
    /**
     * Data
     */
    Model data;
    /**
     * Pattern used by insert
     */
    Pattern paste;
    /**
     * Is insert open
     */
    boolean insertisopen = false;
    /**
     * Current stepmode
     */
    private String stepmode = "manual";

    /**
     * View
     */
    private View view;
    /**
     * Textfield of the stepping interval
     */
    private JTextField interval;
    /**
     * Textfield of size
     */
    private JTextField size;
    /**
     * Stepping
     */
    private JComboBox stepping;
    /**
     * Background color combobox
     */
    private JComboBox Bcolour;
    /**
     * Cell colour combobox
     */
    private JComboBox Ccolour;
    /**
     * Grid color combobox
     */
    private JComboBox Gcolour;
    /**
     * gametable
     */
    private View.Table table;
    /**
     * maintable
     */
    private JPanel maintable;
    /**
     * timer for the maintable
     */
    private Timer mainc;
    /**
     * timer for the gametable
     */
    private Timer gamec;

    /**
     * @param data
     * Constructor
     */
    Control(Model data) {
        this.data = data;
        gamec = null;


    }

    /**
     * @return Cells
     * calls data getcells
     */
    boolean[][] getCells() {
        return data.getCells();
    }

    /**
     * @return maincells
     * calls data getmaincells
     */
    boolean[][] getMaincells() {
        return data.getMaincells();
    }

    /**
     * starts mainc
     */
    void startMainc() {
        mainc = setClock(1, data.maintable, maintable, false);
        gamec = setClock(data.getTime(), data.gametable, table, true);
        mainc.start();
    }

    /*void loadmaintable() {
        data.setMaincells(data.loadTable("Patterns\\mainbackground.ser"));

    }
    */

    /**
     * @param interval textfield for interval
     *                 Sets the interval
     */
    void setInterval(JTextField interval) {
        this.interval = interval;
    }

    /**
     * @param time clock parameter
     * @param cells clock parameter
     * @param tabl clock parameter
     * @param isgametable clock parameter
     * @return created clock
     */
    private Timer setClock(double time, Cells cells, JPanel tabl, boolean isgametable) {
        return new Timer((int) (time * 1000), e -> {
            data.change(cells);
            if (isgametable) {
                view.generations.setText("Generation: " + data.gametable.generation);
                view.livecellcnt.setText("Live cell count: " + data.gametable.livecellcnt);
            }
            tabl.repaint();
        }

        );
    }

    /**
     * @param size
     * Size setter
     */
    void setSize(JTextField size) {
        this.size = size;
    }

    /**
     * @param Bcolour
     * Sets Bcolour
     */
    void setBcolour(JComboBox Bcolour) {
        this.Bcolour = Bcolour;
    }

    /**
     * @param Gcolour
     * Sets Gcolour
     */
    void setGcolour(JComboBox Gcolour) {
        this.Gcolour = Gcolour;
    }

    /**
     * @param Ccolour
     * Sets Ccolour
     */
    void setCcolour(JComboBox Ccolour) {
        this.Ccolour = Ccolour;
    }

    /**
     * @return cell colour
     * gets cell colour
     */
    String getcszin() {
        return data.gametable.Ccolour;
    }

    /**
     * @return backgound color
     * gets backgound color
     */
    String getbszin() {
        return data.gametable.Bcolour;
    }

    /**
     * @return grid color
     * gets grid color
     */
    String getgszin() {
        return data.gametable.Gcolour;
    }

    /**
     * @param stepping
     * sets stepping
     */
    void setStepping(JComboBox stepping) {
        this.stepping = stepping;
    }

    /**
     * @param view
     * Sets the mainframe
     */
    void setFrame(View view) {
        this.view = view;
    }

    /**
     * @param e actionevents fired by the gui
     *          Handles the actionevents fired by the gui
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("case");
        switch (e.getActionCommand()) {

            case"comboBoxChanged":
                paste=new Pattern();
                JComboBox subComboBox= (JComboBox) e.getSource();
                System.out.println(mainComboBox.getSelectedItem()+"\\"+subComboBox.getSelectedItem()+".ser");
                if(!subComboBox.getSelectedItem().equals("Select Pattern"))
                paste.loadTable(mainComboBox.getSelectedItem()+"\\"+subComboBox.getSelectedItem()+".ser");
                System.out.println("works");
                break;
            case "Options":
                view.setOptions();
                mainc.stop();

                break;
            case "Start":
                view.setTablesize(20 * data.getSize(data.gametable));
                view.setSize(data.getSize(data.gametable));
                view.setGame();
                setLabels();
                mainc.stop();

                break;
            case "Quit":
                System.exit(0);
                break;
            case "Save table":
                savefile();

                break;
            case "Load":
                loadfile(data.gametable);
                view.setTablesize(20 * data.getSize(data.gametable));
                view.setSize(data.getSize(data.gametable));
                gamec = setClock(data.getTime(), data.gametable, table, true);
                view.pack();
                table.repaint();
                view.setLocationRelativeTo(null);
                break;
            case "Random":
                data.random(data.gametable);
                setLabels();
                view.getContentPane().revalidate();
                view.getContentPane().repaint();
                break;
            case "Play":
                if (stepmode.equals("manual")) {

                    data.change(data.gametable);
                    setLabels();
                    table.repaint();
                } else {

                    gamec.start();


                }
                break;
            case "Stop":
                data.cropSave(data.gametable);
                if (!stepmode.equals("manual"))
                    gamec.stop();
                break;
            case "Back":
                mainc.start();
                view.setMain();
                if (!stepmode.equals("manual"))
                    gamec.stop();

                break;
            case "Save":
                if (Bcolour.getSelectedItem().equals(Ccolour.getSelectedItem())) {
                    JOptionPane.showMessageDialog(null,
                            "Background and cell colour must be different! \n Changes not saved please try again.",
                            "Error",
                            JOptionPane.WARNING_MESSAGE);

                } else if ((!size.getText().isEmpty() && interval.getText() != null) && (Integer.parseInt(size.getText()) > 45 || Integer.parseInt(size.getText()) < 15)) {

                    JOptionPane.showMessageDialog(null,
                            "Size value must be between 15 and 45! \n Changes not saved please try again.",
                            "Error",
                            JOptionPane.WARNING_MESSAGE);

                } else if (!interval.getText().isEmpty() && interval.getText() != null && Double.parseDouble(interval.getText()) < 0.1) {
                    JOptionPane.showMessageDialog(null,
                            "Custom time interval must be bigger than 0.5!. \n Changes not saved please try again.",
                            "Error",
                            JOptionPane.WARNING_MESSAGE);

                } else {

                    data.save(size.getText(), Bcolour.getSelectedItem(),
                            Ccolour.getSelectedItem(), Gcolour.getSelectedItem(), stepping.getSelectedItem(), interval.getText());
                    JOptionPane.showMessageDialog(null,
                            "Changes saved!",
                            "Succes!",
                            JOptionPane.INFORMATION_MESSAGE);
                    stepmode = stepping.getSelectedItem().toString().toLowerCase();
                    data.isauto = stepmode.equals("Auto");
                    gamec = setClock(data.getTime(), data.gametable, table, true);
                }
                break;

        }


    }

    /**
     * Sets the labels livingcellcount and generation
     */
    void setLabels() {
        view.livecellcnt.setText("Live cell count: " + data.gametable.livecellcnt);
        view.generations.setText("Generation: " + data.gametable.generation);
    }

    /**
     * Saves the current gametable
     */
    private void savefile() {
        JFileChooser jfc = new JFileChooser();
        int returnval = jfc.showSaveDialog(view.getRootPane());
        if (returnval == JFileChooser.APPROVE_OPTION)
            data.savetable(jfc.getSelectedFile().getAbsolutePath());
        else if (returnval == JFileChooser.CANCEL_OPTION)
            return;
    }

    /**
     * @param cells
     * Loads the gametable
     */
    void loadfile(Cells cells) {
        JFileChooser jfc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("SER File", "ser");
        jfc.setFileFilter(filter);
        jfc.setAcceptAllFileFilterUsed(false);
        int retunval = jfc.showOpenDialog(view.getRootPane());
        if (retunval == JFileChooser.APPROVE_OPTION) {
            if (gamec.isRunning())
                gamec.stop();
            cells.loadTable(jfc.getSelectedFile().getAbsolutePath());
        } else
            return;

    }


    /**
     * @param e itemevents fired by the gui
     *          Handles the checkbox and 1 combobox
     */
    @Override
    public void itemStateChanged(ItemEvent e) {


        if (e.getID() == 701) {
            if (e.getStateChange() == ItemEvent.SELECTED || e.getStateChange() == ItemEvent.DESELECTED) {

                data.setGrid(!data.getGrid());
                view.setGrid(data.getGrid());
                table.repaint();
            }
        }
        if (e.getStateChange() == ItemEvent.SELECTED)
            if (stepping.getSelectedItem().equals("Custom")) {
                view.getContentPane().revalidate();
                view.getContentPane().repaint();

                interval.setVisible(true);
            } else {
                interval.setVisible(false);

            }

    }


    /**
     * @param table
     * Sets the table
     */
    void setTable(View.Table table) {
        this.table = table;
    }

    /**
     * @param table
     * Sets the maintable
     */
    void setMainTable(View.MainTable table) {
        this.maintable = table;
    }

    /**
     * @return main size
     * Gets the mainsize
     */
    int getMainsize() {
        return data.maintable.size;
    }


    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {
        boolean[][] Cells = data.getCells();
        if (insertisopen) {
            for (int y = e.getY() / 20; y < e.getY() / 20 + paste.heigth; y++)
                for (int x = e.getX() / 20; x < e.getX() / 20 + paste.width; x++) {
                    int sy=y - e.getY() / 20;
                    int sx=x - e.getX() / 20;
                    Cells[y%data.gametable.size][x%data.gametable.size] = paste.cells[sy][sx];
                }
                table.repaint();


        } else {
            System.out.println("X:" + e.getX() + "Y:" + e.getY());
            System.out.println(e.getX() / 20 + "  " + e.getY() / 20);
            data.gametable.cells[e.getY() / 20][e.getX() / 20] = !Cells[e.getY() / 20][e.getX() / 20];
            table.repaint();
        }
    }


    /**
     * Invoked when a mouse button has been released on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     * Invoked when the mouse enters a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * Invoked when the mouse exits a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * Invoked when a mouse button is pressed on a component and then
     * dragged.  {@code MOUSE_DRAGGED} events will continue to be
     * delivered to the component where the drag originated until the
     * mouse button is released (regardless of whether the mouse position
     * is within the bounds of the component).
     * <p>
     * Due to platform-dependent Drag&amp;Drop implementations,
     * {@code MOUSE_DRAGGED} events may not be delivered during a native
     * Drag&amp;Drop operation.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseDragged(MouseEvent e) {

    }

    /**
     * Invoked when the mouse cursor has been moved onto a component
     * but no buttons have been pushed.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseMoved(MouseEvent e) {

    }

    /**
     * @param e
     * Handles when the insert is selected
     */
    @Override
    public void menuSelected(MenuEvent e) {
        System.out.println("works");
        popup windwow = new popup(view, this);
        windwow.pack();
        windwow.setVisible(true);
    }

    @Override
    public void menuDeselected(MenuEvent e) {

    }

    @Override
    public void menuCanceled(MenuEvent e) {

    }
}






