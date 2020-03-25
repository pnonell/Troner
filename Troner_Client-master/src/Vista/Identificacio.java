/*
 * Created by JFormDesigner on Mon May 08 17:15:52 CEST 2017
 */

package Vista;

import controlador.Controlador;

import java.awt.*;
import javax.swing.*;

/**
 * Created by Grup 6. Identificacio.
 * Vista que conté els formularis de Iniciar Sessio o Registre.
 */

public class Identificacio extends JPanel {

    //Atributs
    private JLabel jlRegistre;
    private JLabel jlIniciar;
    private Registre jpRegistre;
    private IniciarSessio jpIniciar;
    private JLabel jlEspai;
    private JLabel jlEspai2;

    //Constructors

    /**
     * Constructor sense paràmetres
     */
    public Identificacio(){

        jlEspai = new JLabel();
        jlRegistre = new JLabel();
        jlIniciar = new JLabel();
        jpRegistre = new Registre();
        jpIniciar = new IniciarSessio ();
        jlEspai2 = new JLabel();

        //Creem la GridBagLayout
        setLayout(new GridBagLayout());
        ((GridBagLayout)getLayout()).columnWidths = new int[] {0, 0, 0, 0, 0, 0, 0};
        ((GridBagLayout)getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0};
        ((GridBagLayout)getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
        ((GridBagLayout)getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};


        jlEspai.setText(" ");
        add(jlEspai, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 25, 25), 0, 0));

        jlEspai2.setText(" ");
        add(jlEspai2, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 25, 25), 0, 0));

        jlRegistre.setText("Registra't");
        jlRegistre.setFont(new Font("Akrobat Bold", Font.PLAIN, 24));
        add(jlRegistre, new GridBagConstraints(4, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 25, 25), 0, 0));

        jlIniciar.setText("Inicia Sessi\u00f3");
        jlIniciar.setFont(new Font("Akrobat Bold", Font.PLAIN, 24));
        add(jlIniciar, new GridBagConstraints(6, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 25, 25), 0, 0));

        add(jpRegistre, new GridBagConstraints(4, 3, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 25, 25), 0, 0));

        add(jpIniciar, new GridBagConstraints(6, 3, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 25, 25), 0, 0));

    }


    //Metodes

    /**
     * Registra el controlador
     * @param c Controlador
     * @return void
     */

    public void registerController (Controlador c){

        jpRegistre.registercontroller(c);
        jpIniciar.registerController(c);
    }

    public Registre getRegistre(){
        return jpRegistre;
    }

    public IniciarSessio getIniciarSessio(){
        return jpIniciar;
    }

}