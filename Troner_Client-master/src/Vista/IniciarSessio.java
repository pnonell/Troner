package Vista;

import controlador.Controlador;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Grup 6 on 20/04/2017. IniciarSessio.
 * Vista que conté els formularis de Iniciar Sessio o Registre.
 */

public class IniciarSessio extends JPanel {

    //Atributs
    private JPanel jpIniciar;
    private JLabel jlTitle;
    private JLabel jlID;
    private JPanel jpID;
    private JTextField jtID;
    private JPasswordField jtPassword;
    private JLabel jlPassword;
    private JPanel jpPassword;
    private JButton jbIniciar;

    //Constructors
    public IniciarSessio () {

        this.setSize (350,350);
        jpIniciar = new JPanel();
        jpID = new JPanel(new BorderLayout());
        jpPassword = new JPanel(new BorderLayout());

        jlTitle = new JLabel("            Iniciar Sessió");
        jlID = new JLabel("ID         ");
        jtID = new JTextField();
        jlPassword = new JLabel("Password      ");
        jtPassword = new JPasswordField(30);
        jbIniciar = new JButton("Iniciar");


        jpIniciar.setLayout(new GridLayout(4,1));
        jpID.add(jlID, BorderLayout.LINE_START);
        jpID.add(jtID, BorderLayout.CENTER);
        jpPassword.add (jlPassword, BorderLayout.LINE_START);
        jpPassword.add (jtPassword, BorderLayout.CENTER);

        jpIniciar.add(jlTitle, BorderLayout.CENTER);
        jpIniciar.add(jpID);
        jpIniciar.add(jpPassword);
        jpIniciar.add(jbIniciar, BorderLayout.LINE_END);

        this.add(jpIniciar,BorderLayout.PAGE_START);
    }

    //Metodes
    public String getID(){
        return jtID.getText();
    }

    public String getPassword(){

        System.out.println(jtPassword.getText());
        return jtPassword.getText();
    }

    /**
     * Registra el controlador
     * @param c Controlador
     * @return void
     */
    public void registerController(Controlador c){
        jbIniciar.setActionCommand("INICIARSESSIO");
        jbIniciar.addActionListener(c);

    }
}
