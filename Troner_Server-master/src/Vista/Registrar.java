package Vista;

import Controlador.Controlador;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Grup 6. Registrar.
 * Vista del formulari de Registre d'un nou usuari.
 */
public class Registrar extends JPanel {

    //Atributs
    private JPanel jpButtons;
    private JPanel jpLogin;
    private JPanel jpPassword;
    private JPanel jpPassword2;
    private JPanel jpMail;
    private JPanel jpRegistrar;

    private JLabel jlTitle;
    private JLabel jlLogin;
    private JLabel jlPassword;
    private JLabel jlPassword2;
    private JLabel jlMail;
    private JTextField jtLogin;
    private JPasswordField jPassword;
    private JPasswordField jPassword2;
    private JTextField jtMail;
    private JButton jbRegistrar;

    /**
     * Constructor
      */
    public Registrar () {

        this.setSize (350, 350);

        jpButtons = new JPanel();
        jpLogin = new JPanel(new BorderLayout());
        jpPassword = new JPanel(new BorderLayout());
        jpPassword2 = new JPanel(new BorderLayout());
        jpMail = new JPanel(new BorderLayout());
        jpRegistrar = new JPanel(new BorderLayout());

        jPassword = new JPasswordField(30);
        jPassword2 = new JPasswordField();
        jlTitle = new JLabel("Registrar usuari", SwingConstants.CENTER);
        jtMail = new JTextField(30);
        jbRegistrar = new JButton("Registrar");
        jlLogin = new JLabel("Login                                  ");
        jlMail = new JLabel("Mail                                     ");
        jlPassword = new JLabel("Password                         ");
        jlPassword2 = new JLabel("Confirma Contrasenya  ");
        jtLogin = new JTextField();

        jpButtons.setLayout(new GridLayout(6,1));
        jpRegistrar.add(jbRegistrar, BorderLayout.LINE_END);
        jpLogin.add(jlLogin, BorderLayout.LINE_START);
        jpLogin.add(jtLogin, BorderLayout.CENTER);
        jpPassword.add(jlPassword, BorderLayout.LINE_START);
        jpPassword.add(jPassword, BorderLayout.CENTER);
        jpPassword2.add(jlPassword2, BorderLayout.LINE_START);
        jpPassword2.add(jPassword2,BorderLayout.CENTER);
        jpMail.add(jlMail, BorderLayout.LINE_START);
        jpMail.add(jtMail, BorderLayout.CENTER);

        jpButtons.add(jlTitle, BorderLayout.CENTER);
        jpButtons.add(jpLogin);
        jpButtons.add(jpPassword);
        jpButtons.add(jpPassword2);
        jpButtons.add(jpMail);
        jpButtons.add(jpRegistrar);

        this.add(jpButtons, BorderLayout.PAGE_START);
    }

    //Metodes

    /**
     * Registra el controlador
     * @param c Controlador
     */
    public void registerController(Controlador c){

        jbRegistrar.setActionCommand("REGISTRAR BOTO");
        jbRegistrar.addActionListener(c);
    }

    public String getLogin(){
        return jtLogin.getText();
    }

    public String getMail(){
        return jtMail.getText();
    }

    public String getPassword(){
        return String.valueOf(jPassword.getPassword());
    }

    public String getPassword2(){
        return String.valueOf(jPassword2.getPassword());
    }

}
