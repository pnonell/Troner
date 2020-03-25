package Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by Grup 6 on 18/04/17. Configuracio.
 * Vista on apareixen el camp de port, per tal de que un usuari es connecti un cop hagi clicat el botó Iniciar.
 */

public class Configuracio extends JPanel {

    //Atributs
    private JPanel jpConfiguracio;
    private JLabel jlPort;
    private JPanel jpPort;
    private JPanel jpButtons;
    private JLabel jlTitle;
    private JTextField jtPort;
    private JButton jbIniciar;
    private JButton jbAturar;

    /**
     * Constructor
     */
    public Configuracio () {

        this.setSize (350,350);

        jpConfiguracio = new JPanel();
        jpPort = new JPanel(new BorderLayout());
        jpButtons = new JPanel(new BorderLayout());

        jlTitle = new JLabel("Configuració", SwingConstants.CENTER);
        jlPort = new JLabel("Port  ");
        jtPort = new JTextField("11111");
        jtPort.setHorizontalAlignment(SwingConstants.RIGHT);
        jbIniciar = new JButton("Iniciar");
        jbAturar = new JButton("Aturar");

        jpConfiguracio.setLayout(new GridLayout(3,1));
        jpPort.add (jlPort, BorderLayout.LINE_START);
        jpPort.add (jtPort, BorderLayout.CENTER);
        jpButtons.add (jbIniciar, BorderLayout.CENTER);
        jpButtons.add (jbAturar, BorderLayout.LINE_END);

        jpConfiguracio.add(jlTitle, BorderLayout.CENTER);
        jpConfiguracio.add(jpPort);
        jpConfiguracio.add(jpButtons, BorderLayout.LINE_END);

        this.add(jpConfiguracio, BorderLayout.SOUTH);
    }

    //Metodes


    /**
     * Actualitza el port de la connexió
     * @param port int del port.
     */
    public void actualitzaPort(int port){
        String portStr= Integer.toString(port);
        jtPort.setText(portStr);
    }

    /**
     * Registra el controlador
     * @param c ActionListener
     */
    public void registerController(ActionListener c){
        jbIniciar.setActionCommand("INICIAR");
        jbAturar.setActionCommand("ATURAR");

        jbIniciar.addActionListener(c);
        jbAturar.addActionListener(c);
    }

    public String getPort(){
        return jtPort.getText();
    }

}
