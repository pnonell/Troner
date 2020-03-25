package Vista;

import Controlador.Controlador;
import sun.plugin.javascript.JSClassLoader;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by Grup 6. Ranquing.
 * Vista del Ranquing on apareixen tots els usuaris registrats ordenats segons els seus punts.
 */
public class Ranquing extends JPanel {

    //Atributs
    private JLabel jlTitle;
    private JTable jtTaula;
    private int rows;
    private JScrollPane jsRanquing;

    /**
     * Constructor
     */
    public Ranquing () {

        this.setSize (350, 200);
        jtTaula = new JTable();
        jsRanquing = new JScrollPane(jtTaula);
        jlTitle = new JLabel("Rànquing", SwingConstants.CENTER);

        this.setLayout(new BorderLayout());

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        jtTaula.setDefaultRenderer(Integer.class, centerRenderer);

        TableRanquing tableModel = new TableRanquing();
        jtTaula.setModel(tableModel);

        this.add(jlTitle, BorderLayout.NORTH);
        this.add(jsRanquing, BorderLayout.CENTER);

    }

    //Metodes

    public void registerController(Controlador c){

    }

    /**
     * Actualitza les dades del ranquing si s'ha registrat un nou usuari o s'ha eliminat, o la seva puntuació ha canviat.
     * @param data informació que omple el ranquing.
     */
    public void updateList(Object[][] data){

        TableRanquing tableModel = new TableRanquing(data);
        ((DefaultTableModel)jtTaula.getModel()).fireTableDataChanged();
        jtTaula.setModel(tableModel);

    }

}


