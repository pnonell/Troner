package Controlador;

import Client_Servidor.DedicatedServer;
import Model.*;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Grup 6 on 20/06/17. GestionarPartides.
 * Classe que permet controlar les cues dels diferents modes de joc.
 */
public class GestionarPartides {

    //Atributs
    private ArrayList<ArrayList<DedicatedServer>> cua2;
    private ArrayList<ArrayList<DedicatedServer>> cua4;
    private ArrayList<ArrayList<DedicatedServer>> cuaTorneig;

    /**
     * Constructor
     */
    public GestionarPartides() {

        cua2 = new ArrayList<>();
        cua4 = new ArrayList<>();
        cuaTorneig = new ArrayList<>();

    }

    //Metodes


    /**
     * Gestiona la cua de jugadors del mode de 2 jugadors.
     * @param d DedicatedServer connexió amb el Client d'un usuari.
     * @throws IOException
     */
    public void addJoc2(DedicatedServer d) throws IOException {

        if (cua2.isEmpty()) {
            cua2.add(new ArrayList<DedicatedServer>());
            cua2.get(cua2.size() - 1).add(d);
            d.setNum(cua2.get(cua2.size() - 1).size() - 1);
        } else {

            cua2.get(cua2.size() - 1).add(d);
            d.setNum(cua2.get(cua2.size() - 1).size() - 1);

            if (cua2.get(cua2.size() - 1).size() == 2) {

                Partida2 p2 = new Partida2(cua2.get((cua2.size() - 1)));
                cua2.add(new ArrayList<DedicatedServer>());
            }
        }
    }

    /**
     * Gestiona la cua de jugadors del mode de 4 jugadors.
     * @param d DedicatedServer connexió amb el Client d'un usuari.
     * @throws IOException
     */
    public void addJoc4(DedicatedServer d) throws IOException {

        if (cua4.isEmpty()) {
            cua4.add(new ArrayList<DedicatedServer>());
            cua4.get(cua4.size() - 1).add(d);
            d.setNum(cua4.get(cua4.size() - 1).size() - 1);
        } else {

            cua4.get(cua4.size() - 1).add(d);
            d.setNum(cua4.get(cua4.size() - 1).size() - 1);

            if (cua4.get(cua4.size() - 1).size() == 4) {

                Partida4 p4 = new Partida4(cua4.get((cua4.size() - 1)));
                cua4.add(new ArrayList<DedicatedServer>());
            }
        }
    }

    /**
     * Gestiona la cua de jugadors del mode de Torneig.
     * @param d DedicatedServer connexió amb el Client d'un usuari.
     * @throws IOException
     */
    public void addCampeonat(DedicatedServer d) throws IOException {
        if (cuaTorneig.isEmpty()) {
            cuaTorneig.add(new ArrayList<DedicatedServer>());
            cuaTorneig.get(cuaTorneig.size() - 1).add(d);
            d.setNum(cuaTorneig.get(cuaTorneig.size() - 1).size() - 1);
        } else {

            cuaTorneig.get(cuaTorneig.size() - 1).add(d);
            d.setNum(cuaTorneig.get(cuaTorneig.size() - 1).size() - 1);

            if (cuaTorneig.get(cuaTorneig.size() - 1).size() == 4) {

                PartidaTorneig pt = new PartidaTorneig(cuaTorneig.get((cuaTorneig.size() - 1)));
                cuaTorneig.add(new ArrayList<DedicatedServer>());
            }
        }

    }

    /**
     * Gestiona els diferents cassos si algun usuari abandona la espera de la partida encara en funcionament.
     * @param d connexió amb el client d'un usuari
     * @param tipuscua tipus de mode abandonat.
     */
    public void gestionaAbandona(DedicatedServer d, int tipuscua){
        try {
            boolean conte;
            ArrayList<DedicatedServer> aux = new ArrayList<>();
            switch (tipuscua){
                case 2:

                    for (int i = 0; i < cua2.size(); i++) {
                        conte = false;
                        for (int j = 0; j < cua2.get(i).size(); j++) {
                            if (cua2.get(i).get(j).getLogin().equals(d.getLogin())) {
                                conte = true;
                            }
                        }
                        if (conte) {
                            for (int j = 0; j < cua2.get(i).size(); j++) {
                                aux.add(cua2.get(i).get(j));
                                if (!aux.get(j).getLogin().equals(d.getLogin())) {
                                    ModelUsuari model = new ModelUsuari();
                                    model.updatePuntuacio(aux.get(j).getLogin(), 10);
                                    aux.get(j).getDoStreamO().writeObject("ABANDONAT");
                                }
                            }
                            cua2.remove(i);
                            aux.remove(d);
                        }
                    }

                    for (int i = 0; i < aux.size(); i++) {
                        addJoc2(aux.get(i));
                    }
                    break;
                case 4:
                    for (int i = 0; i < cua4.size(); i++) {
                        conte = false;

                        for (int j = 0; j < cua4.get(i).size(); j++) {
                            if (cua4.get(i).get(j) != null && cua4.get(i).get(j).getLogin().equals(d.getLogin())) {
                                conte = true;
                            }
                        }
                        if (conte) {
                            if (d.isJuga()) {
                                for (int j = 0; j < cua4.get(i).size(); j++) {
                                    if (cua4.get(i).get(j) != null && cua4.get(i).get(j).getLogin().equals(d.getLogin())) {
                                        cua4.get(i).set(j, null);
                                    }
                                }
                            } else {
                                for (int j = 0; j < cua4.get(i).size(); j++) {
                                    if (cua4.get(i).get(j) != null) {
                                        aux.add(cua4.get(i).get(j));
                                    }
                                }
                                cua4.remove(i);
                                for(int j = 0; j < aux.size(); j++){
                                    addJoc4(aux.get(j));
                                }
                            }
                        }
                    }
                    break;

                default:

                    for (int i = 0; i < cuaTorneig.size(); i++) {
                        conte = false;
                            for (int j = 0; j < cuaTorneig.get(i).size(); j++) {
                                if (cuaTorneig.get(i).get(j) != null && cuaTorneig.get(i).get(j).getLogin().equals(d.getLogin())) {
                                    conte = true;
                                }
                            }
                            if(conte){
                                for (int j = 0; j < cuaTorneig.get(i).size(); j++) {
                                    if (cuaTorneig.get(i).get(j) != null && cuaTorneig.get(i).get(j).getLogin().equals(d.getLogin())) {
                                        if(d.isJuga()) {
                                            cuaTorneig.get(i).set(j, null);
                                        }
                                    }
                                }

                            }
                        }
                    break;
                }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * En cas de que un jugador ha abandonat la partida. S'han d'afegir els altres a una altra cua.
     *  Mode de 4 jugadors.
     * @param d connexió amb el client d'un usuari.
     * @throws IOException
     */
    public void acabaPartida4(DedicatedServer d) throws IOException {

        ArrayList<DedicatedServer> aux = new ArrayList<>();
        boolean conte;
        for (int i = 0; i < cua4.size(); i++) {
            conte = false;
            for (int j = 0; j < cua4.get(i).size(); j++) {
                if (cua4.get(i).get(j) != null && cua4.get(i).get(j).getLogin().equals(d.getLogin())) {
                    conte = true;
                }
            }
            if (conte) {
                for (int j = 0; j < cua4.get(i).size(); j++) {
                    if(cua4.get(i).get(j) != null){
                        aux.add(cua4.get(i).get(j));
                        aux.get(aux.size() - 1).getDoStreamO().writeObject("ABANDONAT");
                    }
                }
                cua4.remove(i);
            }
        }

        for (int i = 0; i < aux.size(); i++) {
            aux.get(i).setJuga(false);
            addJoc4(aux.get(i));
        }
    }

    /**
     * En cas de que un jugador ha abandonat la partida. S'han d'afegir els altres a una altra cua.
     *  Mode de Torneig.
     * @param d connexió amb el client d'un usuari.
     * @throws IOException
     */
    public void acabaPartidaTorneig(DedicatedServer d) throws IOException{
        boolean conte;
        ArrayList<DedicatedServer> aux = new ArrayList<>();
        for (int i = 0; i < cuaTorneig.size(); i++) {
            conte = false;
            for (int j = 0; j < cuaTorneig.get(i).size(); j++) {
                if(cuaTorneig.get(i).get(j) != null){
                }
                if (cuaTorneig.get(i).get(j) != null && cuaTorneig.get(i).get(j).getLogin().equals(d.getLogin())) {
                    conte = true;
                }
            }

            if(conte){
                for (int j = 0; j < cuaTorneig.get(i).size(); j++) {
                    if(cuaTorneig.get(i).get(j) != null){
                        aux.add(cuaTorneig.get(i).get(j));
                        aux.get(aux.size() - 1).getDoStreamO().writeObject("ABANDONAT");
                    }
                }
                cuaTorneig.remove(i);

                for (int j = 0; j < aux.size(); j++) {
                    aux.get(j).setJuga(false);
                    addCampeonat(aux.get(j));
                }

            }
        }

    }

    /**
     * En cas que els jugadors estiguin en una cua sense haver començat la partida s'activa aquesta opció
     * Elimina el jugador que abandona i redistribueix els altres
     * @param d
     * @throws IOException
     */
    public void novaCuaTorneig(DedicatedServer d) throws IOException{
        boolean conte;
        ArrayList<DedicatedServer> aux = new ArrayList<>();
        for (int i = 0; i < cuaTorneig.size(); i++) {
            conte = false;
            for (int j = 0; j < cuaTorneig.get(i).size(); j++) {
                if (cuaTorneig.get(i).get(j) != null && cuaTorneig.get(i).get(j).getLogin().equals(d.getLogin())) {
                    conte = true;
                    cuaTorneig.get(i).set(j, null);
                }
            }
            if(conte){
                for (int j = 0; j < cuaTorneig.get(i).size(); j++) {
                    if(cuaTorneig.get(i).get(j) != null ){
                        aux.add(cuaTorneig.get(i).get(j));
                    }
                }
                cuaTorneig.remove(i);
                for (int j = 0; j < aux.size(); j++) {
                    addCampeonat(aux.get(j));
                }

            }
        }
    }
}
