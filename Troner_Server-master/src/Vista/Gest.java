/*
 * Created by JFormDesigner on Mon May 08 16:04:19 CEST 2017
 */

package Vista;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.text.Document;

import Controlador.Controlador;
import net.miginfocom.swing.*;

/**
 * Created by Grup 6 on 18/04/17. Gestionar.
 * Vista on apareix una combo box per seleccionar l'usuari, un textfield per mostrar les seves dades i un botó per eliminar l'usuari
 */
public class Gest extends JPanel {


    private JLabel label6;
    private JLabel label5;
    private JComboBox comboBox1;
    private JPanel hSpacer3;
    private JPanel hSpacer4;
    private JScrollPane scrollPane1;
    private JTextPane textPane1;
    private JPanel hSpacer5;
    private JPanel hSpacer2;
    private JButton button1;

    /**
     * Constructor
     */
    public Gest() {

        label6 = new JLabel();
        label5 = new JLabel();
        comboBox1 = new JComboBox();
        hSpacer3 = new JPanel(null);
        hSpacer4 = new JPanel(null);
        scrollPane1 = new JScrollPane();
        textPane1 = new JTextPane();
        hSpacer5 = new JPanel(null);
        hSpacer2 = new JPanel(null);
        button1 = new JButton();

        //======== this ========

        setLayout(new GridBagLayout());
        ((GridBagLayout)getLayout()).columnWidths = new int[] {98, 0, 90, 379, 0, 0, 0};
        ((GridBagLayout)getLayout()).rowHeights = new int[] {0, 118, 166, 56, 0};
        ((GridBagLayout)getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
        ((GridBagLayout)getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 1.0E-4};

        //---- label6 ----
        label6.setText(" ");
        add(label6, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 7, 7), 0, 0));

        //---- label5 ----
        label5.setText("Usuari");
        add(label5, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE,
                new Insets(0, 0, 7, 7), 0, 0));
        add(comboBox1, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 7, 7), 0, 0));
        add(hSpacer3, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.BASELINE, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 7, 7), 0, 0));
        add(hSpacer4, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.BASELINE, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 7, 7), 0, 0));

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(textPane1);
        }
        add(scrollPane1, new GridBagConstraints(2, 2, 2, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 7, 7), 0, 0));
        add(hSpacer5, new GridBagConstraints(4, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.BASELINE, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 7, 7), 0, 0));
        add(hSpacer2, new GridBagConstraints(5, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.BASELINE, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 7, 0), 0, 0));

        //---- button1 ----
        button1.setText("Elimina");
        button1.addActionListener(e -> button1ActionPerformed(e));
        add(button1, new GridBagConstraints(2, 3, 2, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE,
                new Insets(0, 0, 0, 7), 0, 0));

    }

    private void button1ActionPerformed(ActionEvent e) {
    }

    /**
     * Registra el controlador
     * @param c
     */
    public void registerController(Controlador c){
        button1.setActionCommand("ELIMINA");
        comboBox1.setActionCommand("TRIA");

        button1.addActionListener(c);
        comboBox1.addActionListener(c);

    }

    public void setLlistaLogin(ArrayList<String> logins){
        comboBox1.removeAllItems();

        for (String s:logins){
            comboBox1.addItem(s);
        }

    }

    /**
     * Actualitza el text
     * @param text
     */
    public void updateInfo(String text){
        textPane1.setText("");
        textPane1.replaceSelection(text);
    }

    public String getSelectedLogin(){
        return (String) comboBox1.getSelectedItem();
    }
}
