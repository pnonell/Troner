package Vista;


import controlador.Controlador;

import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.*;

/**
 * Created by Grup 6  Control.
 * Classe encarregada de mostrar la vista per canviar els controls del joc
 */
public class Controls extends JPanel {

    //Atributs
    private JPanel contentPane;
    private JLabel jlEspai;
    private JLabel jlControls;
    private JLabel jlUp;
    private JTextField jtfUp;
    private JLabel jlDown;
    private JLabel jlEspai2;
    private JTextField jtfRight;
    private JLabel jlRight;
    private JLabel jlEspai3;
    private JTextField jtfLeft;
    private JLabel jlLeft;
    private JLabel jlEspai4;
    private JTextField jtfDown;
    private JButton jbGuardar;
    private JLabel jlEspai5;
    private int teclaUp;
    private int teclaDown;
    private int teclaLeft;
    private int teclaRight;

    /**
     *Constructor
     */
    public Controls() {

        jlEspai = new JLabel();
        jlControls = new JLabel();
        jlUp = new JLabel(KeyEvent.getKeyText(teclaUp));
        jtfUp = new JTextField();
        jlDown = new JLabel(KeyEvent.getKeyText(teclaDown));
        jlEspai2 = new JLabel();
        jtfRight = new JTextField();
        jlRight = new JLabel(KeyEvent.getKeyText(teclaRight));
        jlEspai3 = new JLabel();
        jtfLeft = new JTextField();
        jlLeft = new JLabel(KeyEvent.getKeyText(teclaLeft));
        jlEspai4 = new JLabel();
        jtfDown = new JTextField();
        jbGuardar = new JButton();
        jlEspai5 = new JLabel();

        //Fem unaGridBagLayout

        setLayout(new GridBagLayout());
        ((GridBagLayout) getLayout()).columnWidths = new int[]{0, 136, 0, 0, 214, 0, 131, 0, 0};
        ((GridBagLayout) getLayout()).rowHeights = new int[]{0, 0, 0, 48, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 51, 0, 0, 0};
        ((GridBagLayout) getLayout()).columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
        ((GridBagLayout) getLayout()).rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

        //Afegim un JLabel buit
        jlEspai.setText(" ");
        add(jlEspai, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 25, 25), 0, 0));

        //Afegim el JLabel de controls
        jlControls.setText("Controls");
        jlControls.setFont(new Font("Akrobat", Font.BOLD, 20));
        add(jlControls, new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 25, 25), 0, 0));

        //Afegim la imatge d'amunt
        jlUp.setIcon(new ImageIcon("imatges\\arriba.png"));
        add(jlUp, new GridBagConstraints(3, 3, 1, 3, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 25, 25), 0, 0));
        add(jtfUp, new GridBagConstraints(4, 4, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 25, 25), 0, 0));

        //Afegim la imatge d'avall
        jlDown.setIcon(new ImageIcon("imatges\\abajo.png"));
        add(jlDown, new GridBagConstraints(3, 6, 1, 3, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 25, 25), 0, 0));

        //Afegim un espai
        jlEspai2.setText(" ");
        add(jlEspai2, new GridBagConstraints(4, 6, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 25, 25), 0, 0));
        add(jtfDown, new GridBagConstraints(4, 7, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 25, 25), 0, 0));

        //Afegim imatge de dreta
        jlRight.setIcon(new ImageIcon("imatges\\derecha.png"));
        add(jlRight, new GridBagConstraints(3, 9, 1, 4, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 25, 25), 0, 0));

        //Afegim un espai
        jlEspai3.setText(" ");
        add(jlEspai3, new GridBagConstraints(4, 10, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 25, 25), 0, 0));
        add(jtfRight, new GridBagConstraints(4, 11, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 25, 25), 0, 0));

        //Afegim una imatge d'esquerra
        jlLeft.setIcon(new ImageIcon("imatges\\izq.png"));
        add(jlLeft, new GridBagConstraints(3, 13, 1, 4, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 25, 25), 0, 0));

        //Afegim un espai
        jlEspai4.setText(" ");
        add(jlEspai4, new GridBagConstraints(4, 14, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 25, 25), 0, 0));
        add(jtfLeft, new GridBagConstraints(4, 15, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 25, 25), 0, 0));

        //Afegim el botó de guardar
        jbGuardar.setText("Guardar");
        add(jbGuardar, new GridBagConstraints(6, 16, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 25, 25), 0, 0));

        //Afegim un espai
        jlEspai5.setText(" ");
        add(jlEspai5, new GridBagConstraints(0, 17, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 25, 25), 0, 0));


        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setLayout(null);
        contentPane.setFocusable(true);

        //Afegim els esdeveniments per reconeixer si es prem una tecla
        //Tecla left
        jtfLeft.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                teclaLeft = e.getKeyCode(); //obtenim el codi de la tecla
                jtfLeft.setText("");        //esborrem cada cop que s'escrigui una tecla
                jlLeft.setText( e.getKeyText(e.getKeyCode()));  //Mostrem quina tecla s'ha apretat
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        //JTextField Tecla left
        jtfRight.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                teclaRight = e.getKeyCode();
                jtfRight.setText("");
                jlRight.setText( e.getKeyText(e.getKeyCode()));
            }

            public void keyReleased(KeyEvent e) {
            }
        });

        //JTextField Tecla Down
        jtfDown.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                teclaDown = e.getKeyCode();
                jtfDown.setText("");
                jlDown.setText( e.getKeyText(e.getKeyCode()));
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        //JTextField Tecla Up
        jtfUp.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                teclaUp = e.getKeyCode();
                jtfUp.setText("");
                jlUp.setText( e.getKeyText(e.getKeyCode()));
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

    }

    //Mètodes

    /**
     * Registra el controlador del botó
     * @param c Controlador
     */
    public void registerController( Controlador c){
        jbGuardar.setActionCommand("GUARDAR");
        jbGuardar.addActionListener(c);

    }

    /**Mètode que actualiza els jLabels quan s'obre la vista
     * @param control
     */
    public void actualitzaControls(int[] control){
        teclaUp = control[0];
        teclaDown = control[1];
        teclaLeft = control[2];
        teclaRight = control[3];
        jlUp.setText( KeyEvent.getKeyText(teclaUp));
        jlDown.setText( KeyEvent.getKeyText(teclaDown));
        jlLeft.setText( KeyEvent.getKeyText(teclaLeft));
        jlRight.setText( KeyEvent.getKeyText(teclaRight));
    }

    /**Mètode que retorna els controls assignats
     * @return array d'ints que conté els controls de les 4 tecles
     */
    public int[] getControls(){

        int[] controls = new int[4];
        controls[0] = teclaUp;
        controls[1] = teclaDown;
        controls[2] = teclaLeft;
        controls[3] = teclaRight;
        return controls;

    }
}