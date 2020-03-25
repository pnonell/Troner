package Controlador;

import Client_Servidor.DedicatedServer;
import Model.ModelUsuari;
import Model.Posicio;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Classe de partida de 4 jugadors
 * Created by Grup 6 on 10/05/2017.
 */
public class Partida4 {
    private ArrayList<DedicatedServer> jugadors;
    private String[] logins = new String[4];
    private int[] puntuacions = new int[4];
    private String[] posicions = new String[4];
    private int morts;
    private boolean abandona;

    /**
     * Constructor que crea una partida de 4 jugadors
     * @param jugadors
     * @throws IOException
     */
    public Partida4(ArrayList<DedicatedServer> jugadors) throws IOException {
        this.jugadors = jugadors;
        morts = 0;
        jugadors.get(0).setPartida4(this);
        jugadors.get(1).setPartida4(this);
        jugadors.get(2).setPartida4(this);
        jugadors.get(3).setPartida4(this);
        logins[0] = jugadors.get(0).getLogin();
        logins[1] = jugadors.get(1).getLogin();
        logins[2] = jugadors.get(2).getLogin();
        logins[3] = jugadors.get(3).getLogin();
        for(int i = 0; i < 4; i++){
            jugadors.get(i).getDoStreamO().writeObject("JUGADOR");
            jugadors.get(i).getDoStreamO().writeObject(logins);
            jugadors.get(i).getDoStreamO().writeObject(jugadors.get(i).getNum());
            jugadors.get(i).getDoStreamO().writeObject("COMENÇA");
            jugadors.get(i).setJuga(true);
        }
        for(int i = 0; i < 4; i++){
            posicions[i] = "1r";
            puntuacions[i] = 20;
        }
        abandona = false;
    }

    /**
     * Mètode que envia la serp al client
     * @param dir
     * @param cap
     * @param emisor
     */
    public void enviaSerp(int dir, Posicio cap, Socket emisor) {
        try {
            int j=-1;
            for (int i = 0; i < jugadors.size(); i++) {
                if (jugadors.get(i) != null && jugadors.get(i).getsClient() == emisor) {
                    j = i;
                }
            }
            for (int i = 0; i < jugadors.size(); i++) {

                if (jugadors.get(i) != null &&jugadors.get(i).getsClient() != emisor) {
                    jugadors.get(i).getDoStreamO().writeObject("MOU");
                    jugadors.get(i).getDoStreamO().writeObject(j);
                    jugadors.get(i).getDoStreamO().writeObject(dir);
                    jugadors.get(i).getDoStreamO().writeObject(cap);
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Mètode que finalitza la partida quan un jugador ha mort i reparteix els punts
     * @param mort
     */
    public void haMort(int mort){
        try{
            /*int j=-1;                     //BORRAR?
            for (int i = 0; i < jugadors.size(); i++) {
                if (jugadors.get(i) != null && jugadors.get(i).getsClient() == emisor) {
                    j = i;
                }
            }*/
            morts++;
            for (int i = 0; i < jugadors.size(); i++) {

                if (jugadors.get(i) != null && i != mort) {
                    jugadors.get(i).getDoStreamO().writeObject("MORT");
                    jugadors.get(i).getDoStreamO().writeObject(mort);
                }else if(i == mort){
                    switch (morts){
                        case 1:
                            posicions[i] = "4t";
                            puntuacions[i] = -20;
                            break;
                        case 2:
                            posicions[i] = "3r";
                            puntuacions[i] = -10;
                            break;
                        case 3:
                            posicions[i] = "2n";
                            puntuacions[i] = 10;
                            break;
                    }
                }
            }
            if(morts == 3){
                fiPartida();
                if(abandona){
                    boolean ok = true;
                    for (int i = 0; i < jugadors.size() && ok; i++) {
                        if (jugadors.get(i) != null) {
                            ok = false;
                            jugadors.get(i).acabaPartida4();
                        }
                    }
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Mètode que envia els punts al client quan ha finalitzat la partida
     */
    public void fiPartida() {

        ModelUsuari modelUsuari = new ModelUsuari();

        try {
            int guanyador = -1;
            for (int i = 0; i < jugadors.size(); i++) {
                if (posicions[i].equals("1r")) {
                    guanyador = i;
                }
            }
            for (int i = 0; i < jugadors.size(); i++) {
                if(jugadors.get(i) != null){
                    modelUsuari.updatePuntuacio(jugadors.get(i).getLogin(), puntuacions[i]);
                    jugadors.get(i).getDoStreamO().writeObject("PUNTS");
                    jugadors.get(i).getDoStreamO().writeObject(posicions[i]);
                    jugadors.get(i).getDoStreamO().writeObject(puntuacions[i]);
                    jugadors.get(i).getDoStreamO().writeObject(modelUsuari.getPuntsUsuari(jugadors.get(i).getLogin()));
                    jugadors.get(i).getDoStreamO().writeObject(guanyador);
                }

            }
            reinicia();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException se){
            se.printStackTrace();
        }
    }

    /**
     * Mètpde que reinicia els paràmetres de la partida
     */
    public void reinicia(){
        morts = 0;
        for(int i = 0; i < 4; i++){
            posicions[i] = "1r";
            puntuacions[i] = 20;
        }
    }

    public void setAbandona(boolean abandona) {
        this.abandona = abandona;
    }

    public int getMorts() {
        return morts;
    }

    public String[] getPosicions() {
        return posicions;
    }
}

