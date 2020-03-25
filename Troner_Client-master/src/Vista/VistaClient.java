package Vista;

import controlador.Controlador;
import controlador.ControladorJoc;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Grup 6 on 19/04/2017. VistaClient.
 * JFrame que conté les vistes del Client.
 */
public class VistaClient extends JFrame {

    //Atributs
    private  CardLayout layout;
    private Controls controls;
    private Configuracio configuracio;
    private  Joc joc;
    private Ranquing ranquing;
    private Identificacio identificacio;

    //Constructors

    /**
     * Constructor de la vista del menú del client. Inicialitza els elements a mostrar
     */
    public VistaClient (){

        this.setTitle("LS Troner");
        this.setSize (1200, 700);
        layout = new CardLayout();

        this.getContentPane().setLayout(layout);

        //Inicialització dels panells
        configuracio = new Configuracio();
        joc = new Joc();
        ranquing = new Ranquing();
        identificacio = new Identificacio();
        controls = new Controls();

        this.getContentPane().add("INICI", configuracio);
        this.getContentPane().add("IDENTIFICACIO", identificacio);
        this.getContentPane().add("RANQUING", ranquing);
        this.getContentPane().add("JOC", joc);
        this.getContentPane().add("CONTROLS",controls);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    //Metodes


    /**
     * Registra els controladors necessaris.
     * @param c Controlador
     * @param cj ControladorJoc
     */
    public void registerController(Controlador c, ControladorJoc cj) {

        identificacio.registerController(c);
        configuracio.registerController(c);
        ranquing.registerController(c);
        joc.registraControlador(cj);
        controls.registerController(c);

    }

    /**
     * Va canviant les vistes segons el which que rep.
     * @param which indica quin es el panell necessari.
     */
    public void changePanel(String which){
        layout.show(this.getContentPane(), which);
    }

    public int getPort(){return configuracio.getPort();}

    public String getIp(){return configuracio.getIp();}

    public Registre getRegistre(){
        return identificacio.getRegistre();
    }

    public IniciarSessio getIniciarSessio(){
        return identificacio.getIniciarSessio();
    }

    public int[] getControls(){ return  controls.getControls();}


    /**
     * Actualitza els controls a la vista de "Controls" per tal de indicar quins eren els que l'usuari havia guardat previament.
     * @param control son els Int associats a cada tecla guardada per l'usuari
     */
    public void  actualitzaControls(int[] control){
        controls.actualitzaControls(control);
    }


    /**
     * Inicia el Joc
     */
    public void iniciaPartida(){
        joc.iniciaJoc();
    }

    public VistaJoc getJoc() {
        return joc.getPanel1();
    }

    public Ranquing getRanquing(){return ranquing;}

    /**
     * Insereix els jugadors que participaran a la partida.
     * @param logins Array de logins que conté els logins de tots els jugadors.
     */
    public void insereixJugador(String[] logins){
        joc.insereixJugador(logins);
    }

    public void insereixRondes(int[] rondes){
        joc.setRondes(rondes);
    }

    public void aturaPartida(){
        joc.aturar();
    }

    public void setPunts(int p){
        joc.getPanel1().setPunts(p);
    }

    public void setPos(String pos){
        joc.getPanel1().setPosicio(pos);
    }

    public void reinicia(){
        joc.reinicia();
    }

    public void actualitzaTecles(int[] tecla) {joc.actualitzaTecles(tecla);}

}
