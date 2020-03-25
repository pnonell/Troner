package Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Classe del panell de ranquing
 * Created by Grup 6 on 04/05/2017.
 */
public class Ranquing extends  JPanel {
    private JButton jbConfig;
    private JButton jbTancar;
    private JLabel jlRanquing;
    private JTextArea jtRanquing;
    private JButton jb2X;
    private JButton jb4X;
    private JButton jbCamp;
    private JPanel jpButtons;
    private JPanel jpRanquing;
    private JPanel jpJocs;
    private JLabel jlRight;
    private JLabel jlLeft;

    /**
     * Constructor de la classe
     */
    public Ranquing (){

        //Inicialitzem components
        jbConfig = new JButton("Ajustes");
        jbTancar = new JButton("Tancar Sessió");
        jlRanquing = new JLabel("                                                                                           Ranquing TOP 10");
        jtRanquing = new JTextArea(50,10);
        jb2X = new JButton("2X");
        jb4X = new JButton("4X");
        jbCamp = new JButton("Campeonat");
        jpButtons = new JPanel();
        jpRanquing = new JPanel();
        jpJocs = new JPanel();
        jlLeft = new JLabel("                                                                                     ");
        jlRight = new JLabel("                                                                                    ");

        jtRanquing.setEditable(false);
        jtRanquing.setAlignmentX(CENTER_ALIGNMENT);
        jtRanquing.setAlignmentY(CENTER_ALIGNMENT);
        this.setLayout(new BorderLayout());

        jpButtons.setLayout(new BorderLayout());
        jpButtons.add(jbConfig, BorderLayout.LINE_START);
        jpButtons.add(jbTancar, BorderLayout.LINE_END);
        this.add(jpButtons, BorderLayout.NORTH);

        jpRanquing.setLayout(new BorderLayout());
        jpRanquing.add(jlRanquing, BorderLayout.NORTH);
        jpRanquing.add(jtRanquing, BorderLayout.CENTER);
        jpRanquing.add(jlLeft, BorderLayout.WEST);
        jpRanquing.add(jlRight, BorderLayout.EAST);


        this.add(jpRanquing, BorderLayout.CENTER);

        jpJocs.setLayout(new FlowLayout());
        jpJocs.add(jb2X);
        jpJocs.add(jb4X);
        jpJocs.add(jbCamp);
        this.add(jpJocs, BorderLayout.SOUTH);
    }


    /**
     * Registra el controlador
     * @param c Controlador
     * @return void
     */
    public void registerController(ActionListener c){
        jb2X.addActionListener(c);
        jb4X.addActionListener(c);
        jbCamp.addActionListener(c);
        jbConfig.addActionListener(c);
        jbTancar.addActionListener(c);

        jbTancar.setActionCommand("TANCAR");
        jbConfig.setActionCommand("CONTROLS");
        jb2X.setActionCommand("JOC2");
        jb4X.setActionCommand("JOC4");
        jbCamp.setActionCommand("CAMPEONAT");
    }


    /**
     * Afegeix el rànquing en el JTextField
     * @param ranquing
     */
    public void setRanquing(String ranquing){
        jtRanquing.setText("");
        jtRanquing.setText(ranquing);
    }
}
