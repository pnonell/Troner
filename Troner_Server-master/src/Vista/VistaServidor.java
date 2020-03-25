package Vista;

import Controlador.Controlador;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Classe de la vista del menú principal del servidor
 * Created by Grup 6 on 11/04/2017.
 */
public class VistaServidor extends JFrame {

    //Atributs
    private Color blauCel = new Color(135, 206, 250);

    private Configuracio config;
    private Registrar reg;
    private Graphic graf;
    private Ranquing rank;
    private Gest gs;

    private  CardLayout layout;

    private JMenuBar jmbbarraMenu;
    private JMenuItem jmRegistrar;
    private JMenuItem jmGestionar;
    private JMenuItem jmConfiguracio;
    private JMenuItem jmRanquing;
    private JMenuItem jmGrafic;

    private JTextField jtproba;

    /**
     * Constructor de la vista del menú del servidor. Inicialitza els elements a mostrar
     */
    public VistaServidor (){
        this.setTitle("Servidor Troner");
        this.setSize (700, 600);
        this.setResizable(true);

        layout = new CardLayout();
        this.getContentPane().setLayout(layout);

        config = new Configuracio();
        reg = new Registrar();
        graf = new Graphic();
        rank = new Ranquing();
        gs = new Gest();

        jmbbarraMenu = new JMenuBar();
        jmRegistrar = new JMenuItem("Registrar");
        jmGestionar = new JMenuItem("Gestionar");
        jmConfiguracio = new JMenuItem("Configuració");
        jmRanquing = new JMenuItem("Ranquing");
        jmGrafic = new JMenuItem("Gràfic");

        jmRegistrar.setBackground(blauCel);
        jmGestionar.setBackground(blauCel);
        jmConfiguracio.setBackground(blauCel);
        jmRanquing.setBackground(blauCel);
        jmGrafic.setBackground(blauCel);

        jmbbarraMenu.add(jmRegistrar);
        jmbbarraMenu.add(jmConfiguracio);
        jmbbarraMenu.add(jmGestionar);
        jmbbarraMenu.add(jmRanquing);
        jmbbarraMenu.add(jmGrafic);

        jmbbarraMenu.setBackground(blauCel);
        this.setJMenuBar(jmbbarraMenu);

        this.getContentPane().add("CONFIGURACIO", config);
        this.getContentPane().add("REGISTRAR", reg);
        this.getContentPane().add("GRAFIC", graf);
        this.getContentPane().add("RANQUING", rank);
        this.getContentPane().add("GEST", gs);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void registerController(Controlador c){

        jmConfiguracio.setActionCommand("CONFIGURACIO");
        jmRegistrar.setActionCommand("REGISTRAR");
        jmGestionar.setActionCommand("GEST");
        jmRanquing.setActionCommand("RANQUING");
        jmGrafic.setActionCommand("GRAFIC");

        jmConfiguracio.addActionListener(c);
        jmRegistrar.addActionListener(c);
        jmGestionar.addActionListener(c);
        jmRanquing.addActionListener(c);
        jmGrafic.addActionListener(c);

        reg.registerController(c);
        config.registerController(c);
        graf.registerController(c);
        rank.registerController(c);
        gs.registerController(c);
    }

    //Metodes

    /**
     * Mètode que canvia el panel en funció del que volguem veure
     * @param which
     */
    public void changePanel(String which){
        layout.show(this.getContentPane(), which);
    }

    //registre
    public String getLogin(){
        return reg.getLogin();
    }

    //registre
    public String getMail(){
        return reg.getMail();
    }

    //registre
    public String getPassword(){
        return reg.getPassword();
    }

    //registre
    public String getPassword2(){
        return reg.getPassword2();
    }

    //Registre
    public void showMessage(boolean correcte){

        if (correcte){
            JOptionPane.showMessageDialog(null, "Registre Correcte");
        }
        else {
            JOptionPane.showMessageDialog(null, "No s'ha pogut realitzar el registre");
        }
    }

    //configuració
    public int getPort(){

        if (config.getPort().matches("[0-9]+")){
            return Integer.parseInt(config.getPort());
        }else {
            JOptionPane.showMessageDialog(null, "Port incorrecte\nS'ha iniciat per defecte el port 11111 ");
            return 11111;
        }

    }

    //pestanya grafics
    public void grUupdateLoginList(ArrayList<String> logins){
        graf.setLlistaLogin(logins);
    }

    public String getGraficLogin(){
        return graf.getLogin();
    }

    /**
     * Métode que crida a crear el gràfic
     * @param dades
     */
    public void creaGrafic(ArrayList<Integer> dades){
        graf.creaGrafic(dades);
    }

    //pestanya gestionar
    public void gsUpdateList(ArrayList<String> logins){
        gs.setLlistaLogin(logins);
    }

    //pestanya gestionar
    public void gsUpdateInfo(String text){
        gs.updateInfo(text);
    }

    //pestanya gestionar
    public String gsGetSelectedLogin(){
        return gs.getSelectedLogin();
    }

    /**
     * Métode que actualitza el port
     * @param port
     */
    public void actualitzaPort(int port){
        config.actualitzaPort(port);
    }

    //ranqing

    /**
     * Actualitza el rànquing
     * @param data
     */
    public  void rankingUpdateList(Object[][] data){
        rank.updateList(data);
    }
}
