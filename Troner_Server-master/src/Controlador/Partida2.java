package Controlador;

import Client_Servidor.DedicatedServer;
import Model.ModelUsuari;
import Model.Posicio;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 * Classe de partida de 2 jugadors
 * Created by Grup 6 on 10/05/2017.
 */
public class Partida2 {

    //Atributs
    private ArrayList<DedicatedServer> jugadors;
    private String[] logins = new String[2];
    private int[] puntuacions = new int[2];
    private String[] posicions = new String[2];


    /**
     * Constructor que crea una partida de 2 jugadors
     * @param jugadors
     * @throws IOException
     */
    public Partida2(ArrayList<DedicatedServer> jugadors) throws IOException{
        this.jugadors = jugadors;
        jugadors.get(0).setPartida2(this);
        jugadors.get(1).setPartida2(this);
        logins[0] = jugadors.get(0).getLogin();
        logins[1] = jugadors.get(1).getLogin();
        for(int i = 0; i < 2; i++){
            jugadors.get(i).getDoStreamO().writeObject("JUGADOR");
            jugadors.get(i).getDoStreamO().writeObject(logins);
            jugadors.get(i).getDoStreamO().writeObject(jugadors.get(i).getNum());
            jugadors.get(i).getDoStreamO().writeObject("COMENÇA");
            jugadors.get(i).setJuga(true);
        }
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
                if (jugadors.get(i).getsClient() == emisor) {
                    j = i;
                }
            }
            for (int i = 0; i < jugadors.size(); i++) {

                if (jugadors.get(i).getsClient() != emisor) {
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
     * @param emisor
     */
    public void haMort(Socket emisor){
        try{
            int j=-1;
            for (int i = 0; i < jugadors.size(); i++) {
                if (jugadors.get(i).getsClient() == emisor) {
                    j = i;
                }
            }
            for (int i = 0; i < jugadors.size(); i++) {

                if (jugadors.get(i).getsClient() != emisor) {
                    jugadors.get(i).getDoStreamO().writeObject("MORT");
                    jugadors.get(i).getDoStreamO().writeObject(j);
                    posicions[i] = "1r";
                    puntuacions[i] = 10;
                } else{
                    posicions[i] = "2n";
                    puntuacions[i] = -10;
                }
            }
            fiPartida();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Mètode que envia els punts al client quan ha finalitzat la partida
     */
    public void fiPartida(){

        ModelUsuari model_usuari = new ModelUsuari();

        try{
            int guanyador = -1;
            for (int i = 0; i < jugadors.size(); i++) {
                if(posicions[i].equals("1r")){
                    guanyador = i;
                }
            }
            for (int i = 0; i < jugadors.size(); i++) {
                model_usuari.updatePuntuacio(jugadors.get(i).getLogin(), puntuacions[i]);
                jugadors.get(i).getDoStreamO().writeObject("PUNTS");
                jugadors.get(i).getDoStreamO().writeObject(posicions[i]);
                jugadors.get(i).getDoStreamO().writeObject(puntuacions[i]);
                jugadors.get(i).getDoStreamO().writeObject(model_usuari.getPuntsUsuari(jugadors.get(i).getLogin()));
                jugadors.get(i).getDoStreamO().writeObject(guanyador);
            }
        }catch (IOException e){
            e.printStackTrace();
        }catch (SQLException se){
            se.printStackTrace();
        }
    }
}
