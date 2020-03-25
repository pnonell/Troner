package Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by Grup 6 on 20/04/17. Configuracio.
 * Vista on apareixen els camps de IP i port, per tal de que un usuari es connecti un cop hagi clicat el botó Iniciar.
 */

public class Configuracio extends JPanel {

    //Atributs
    private JPanel jpConfiguracio;
    private JPanel jpAtras;
    private JLabel jlTitle;
    private JLabel jlPort;
    private JPanel jpPort;
    private JTextField jtPort;
    private JTextField jtIP;
    private JLabel jlIP;
    private JPanel jpIP;
    private JButton jbIniciar;

    //Constructor
    public Configuracio () {

        this.setSize (350,350);

        jpConfiguracio = new JPanel();
        jpAtras = new JPanel(new BorderLayout());
        jpPort = new JPanel(new BorderLayout());
        jpIP = new JPanel(new BorderLayout());

        jlTitle = new JLabel("            Configuració");
        jlPort = new JLabel("Port         ");
        jtPort = new JTextField("11111");
        jlIP = new JLabel("IP Servidor      ");
        jtIP = new JTextField("localhost");
        jbIniciar = new JButton("Iniciar");

        jpConfiguracio.setLayout(new GridLayout(4, 1));
        jpAtras.add (jlTitle, BorderLayout.CENTER);
        jpIP.add(jlIP, BorderLayout.LINE_START);
        jpIP.add(jtIP, BorderLayout.CENTER);
        jpPort.add (jlPort, BorderLayout.LINE_START);
        jpPort.add (jtPort, BorderLayout.CENTER);

        jpConfiguracio.add(jpAtras, BorderLayout.CENTER);
        jpConfiguracio.add(jpIP);
        jpConfiguracio.add(jpPort);
        jpConfiguracio.add(jbIniciar, BorderLayout.LINE_END);

        this.add(jpConfiguracio,BorderLayout.PAGE_START);
    }

    //Metodes

    /**
     * Registra el controlador
     * param c ActionListener
     * @return void
     */

    public void registerController(ActionListener c){

        jbIniciar.addActionListener(c);
        jbIniciar.setActionCommand("INICIAR");

    }

    public String getIp(){
        return jtIP.getText();
    }

    public int getPort(){
        return Integer.parseInt(jtPort.getText()) ;
    }



}
