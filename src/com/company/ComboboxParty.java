package com.company;


import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Hashtable;


/**
 * The double combobox which is used in the Insert popup
 */
public class ComboboxParty extends JPanel implements ActionListener {
    /**
     * Eventdispatcher
     */
    Control control;
    /**
     * The main combobox
     */
    private JComboBox mainComboBox;
    /**
     * The sub combobox
     */
    private JComboBox subComboBox;
    /**
     * Hastable which is required for the items
     */
    private Hashtable subItems = new Hashtable();

    /**
     * @param control eventlistener
     *                Initializes the 2 combobox and uploads them
     */
    public ComboboxParty(Control control) {
        this.control = control;
        String[] items = {"Oscillators", "Still Lifes", "Gliders"};
        mainComboBox = new JComboBox(items);
        mainComboBox.addActionListener(this);
        control.mainComboBox=mainComboBox;

        //  prevent action events from being fired when the up/down arrow keys are used
        mainComboBox.putClientProperty("JComboBox.isTableCellEditor", Boolean.TRUE);
        this.add(mainComboBox, BorderLayout.WEST);


        subComboBox = new JComboBox();
        subComboBox.addActionListener(control);
System.out.println(subComboBox.getActionCommand());

        subComboBox.setPrototypeDisplayValue("XXXXXXXXXX"); // JDK1.4
        this.add(subComboBox, BorderLayout.EAST);


        String[] subItems0 = {"Select Pattern","Blinker", "Beacon", "Pulsar", "Galaxy", "Pentadecathlon"};
        subItems.put(items[0], subItems0);

        String[] subItems1 = {"Select Pattern", "Square", "Pond"};
        subItems.put(items[1], subItems1);

        String[] subItems2 = {"Select Pattern ", "Glider", "Spaceship" };
        subItems.put(items[2], subItems2);

        mainComboBox.setSelectedIndex(1);
    }


    /**
     * @param e actienevent fired by the main combobox
     *          sets the subcombobox items by the main comboboxes selected item
     */
    public void actionPerformed(ActionEvent e) {
        String item = (String) mainComboBox.getSelectedItem();
        Object o = subItems.get(item);

        if (o == null) {
            subComboBox.setModel(new DefaultComboBoxModel());
        } else {
            subComboBox.setModel(new DefaultComboBoxModel((String[]) o));
        }


    }

}

/**
 * The insert menu's popup window
 */
class popup extends JDialog {
    /**
     * Eventlistener
     */
    Control control;

    /**
     * @param parent The parent frame of the dialog
     * @param control Eventlistener
     */
    popup(JFrame parent, Control control) {
        super(parent, "something");
        this.control = control;
        this.setContentPane(new ComboboxParty(control));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.addWindowListener(new WindowListener() {
            /**
             * @param e event fired by the popup window
             *          sets the insertisopen to true
             */
            @Override
            public void windowOpened(WindowEvent e) {
                control.insertisopen=true;
            }

            /**
             * @param e event fired by the popup window
             *          sets the insertisopen to false
             */
            @Override
            public void windowClosing(WindowEvent e) {
                control.insertisopen=false;
                System.out.println("closes");

            }

            /**
             * @param e
             * Not implemented
             */
            @Override
            public void windowClosed(WindowEvent e) {

            }

            /**
             * @param e
             * Not implemented
             */
            @Override
            public void windowIconified(WindowEvent e) {

            }

            /**
             * @param e
             * Not implemented
             */
            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            /**
             * @param e
             * Not implemented
             */
            @Override
            public void windowActivated(WindowEvent e) {

            }

            /**
             * @param e
             * Not implemented
             */
            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });

    }


}

