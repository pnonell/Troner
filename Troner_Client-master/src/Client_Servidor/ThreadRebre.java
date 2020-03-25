package Client_Servidor;

import Model.*;
import Model.Partida;
import Vista.VistaClient;
import controlador.ControladorJoc;


import java.io.IOException;
import java.io.ObjectInputStream;


/**
 * Created by Grup 6 on 03/05/2017 ThreadRebre.
 * Classe que hereda de Thread i es comunica amb el servidor per passar els paràmetres del joc
 */
public class ThreadRebre extends Thread {
    private ObjectInputStream diStreamO;
    private Client model;
    private ControladorJoc cj;
    private String opcio = "";
    private VistaClient vista;
    private Network network;

    /**
     * Constructor
     * @param diStreamO ObjectInputStream
     * @param model     Model
     * @param vista     Vista
     * @param network   Network
     * @param cj        Controlador del joc
     */
    public ThreadRebre(ObjectInputStream diStreamO, Client model, VistaClient vista, Network network, ControladorJoc cj) {

        this.diStreamO = diStreamO;
        this.model = model;
        this.vista = vista;
        this.network = network;
        this.cj = cj;
    }

    /**
     * Mètode run encarregat d'executar el Thread
     */
    @Override
    public void run() {

        try {
            while (true) {

                opcio = (String) diStreamO.readObject();

                switch (opcio) {
                    case "COMENÇA":

                        //inicialitzem el conte enrere
                        cj.setContador(4);

                        //començarPartida
                        vista.iniciaPartida();

                        break;

                    case "MOU":
                        //rebreSerp
                        int jug = (int)diStreamO.readObject();
                        int dir = (int) diStreamO.readObject();
                        Posicio cap = (Posicio)diStreamO.readObject();
                        model.getPartida().mouSerp(dir, cap, jug);
                        break;

                    case "JUGADOR":     //afegir jugador
                        String[] j = (String[])diStreamO.readObject();
                        vista.insereixJugador(j);
                        int k = (int)diStreamO.readObject();
                        model.getPartida().setSerp(k);
                        vista.insereixRondes(model.getPartida().getRondes());
                        break;

                    case "MORT":        //mort
                        model.getPartida().getSerps().get((int) diStreamO.readObject()).setViu(false);
                        break;

                    case "PUNTS":       //rebre punts
                        vista.setPos((String) diStreamO.readObject());
                        vista.setPunts((int) diStreamO.readObject());
                        vista.getJoc().setTotal((int) diStreamO.readObject());
                        int guanyador = (int) diStreamO.readObject();
                        vista.aturaPartida();
                        if(guanyador > -1){
                            model.getPartida().setRondes(guanyador);
                        }
                     //   model.elimina(eliminat);              BORRAR!
                        vista.insereixRondes(model.getPartida().getRondes());
                        break;
                    case "ELIMINAT":

                        int n = (int) diStreamO.readObject();
                        for (int i= 0; i < n; i++){
                            int eliminat = (int) diStreamO.readObject();
                            model.elimina(eliminat);
                        }
                        model.reinciaEliminats();
                        break;
                    case "ABANDONAT": //En prèmer el botó abandona
                        cj.reinicia();
                        model.abandonaPartida();
                        vista.getJoc().setContador(cj.getContador());
                        vista.getJoc().setSerps(cj.getSerps());
                        vista.reinicia();
                        break;

                    case "RANQUING":        //Mostrar el rànquing rebut del servidor
                        vista.getRanquing().setRanquing((String) diStreamO.readObject());
                        break;

                    case "ENVIACONTROLS":       //Establir els controls rebuts del servidor
                        int[] controls = new int[4];
                        controls[0] = (int) diStreamO.readObject();
                        controls[1] = (int) diStreamO.readObject();
                        controls[2] = (int) diStreamO.readObject();
                        controls[3] = (int) diStreamO.readObject();
                        vista.actualitzaControls(controls);
                        vista.actualitzaTecles(controls);
                        break;
                }

            }
        }catch(IOException e){
        }catch(ClassNotFoundException e){
                e.printStackTrace();
        }
    }
}
