package controlador;

import Model.Inicia;
import Model.Partida;
import Client_Servidor.Network;
import Vista.VistaClient;
import Model.Client;
import Model.Usuari;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Controlador del client
 * Created by Grup 6 on 24/04/2017.
 */
public class Controlador implements ActionListener {
    private Client model;
    private Network network;
    private VistaClient vista;

    /**
     * Constructor del controlador
     * @param vistaClient vista
     * @param model Model
     * @param network Network
     */
    public Controlador(VistaClient vistaClient, Client model, Network network) {
        this.vista = vistaClient;
        this.model = model;
        this.network = network;
    }

    /**Mètode que controla les accions a realitzar depenent les comandes enviades
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            //Depenenent del ActionCommand
            switch (e.getActionCommand()) {

                case "REGISTRE":    //Al clicar Registrar a la vista Registre es comproven les dades, es crea un usuari es passa al servidor

                    Usuari usuariAux = new Usuari(); //Crea Usuari

                    //Comprova les dades i li passa al servidor si és correcte, sinó mostra un missatge a l'usuari.
                    if (usuariAux.comprovaDades(vista.getRegistre().getLogin(), vista.getRegistre().getMail(),vista.getRegistre().getPassword(),vista.getRegistre().getConfirmacio())) {
                        model.setUsuari(usuariAux = new Usuari(vista.getRegistre().getLogin(), vista.getRegistre().getMail(),vista.getRegistre().getPassword()));

                        network.avisaServer("REGISTRAR");
                        if (!network.registraUsuari(usuariAux)){
                            JOptionPane.showMessageDialog(null, "No s'ha pogust completar el registre\nJa existeix aquest usuari");
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Registre completat amb exit");
                            vista.changePanel("RANQUING");
                            network.iniciaRebre();
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "No s'ha pogut completar el registre\nHi ha un error en les dades");
                    }
                    break;

                case "INICIAR": //Primer cas que es realitza, es connecta al servidor amb el port i ip indicats


                    if (network.connect(vista.getPort(), vista.getIp())){
                        vista.changePanel("IDENTIFICACIO");
                    }else {
                        JOptionPane.showMessageDialog(null, "No s'ha pogut conectar al servidor");
                    }
                    break;

                case "INICIARSESSIO":   //Al iniciar sessió passa les dades al servidor i canvia a la finestra ranquing

                    Inicia iniciaAux = new Inicia(vista.getIniciarSessio().getID(), vista.getIniciarSessio().getPassword());

                    network.avisaServer("INICIARSESSIO");
                   if (network.iniciaSessio(iniciaAux)){

                       vista.changePanel("RANQUING");
                       network.iniciaRebre();

                   }
                   else {
                        JOptionPane.showMessageDialog(null, "Error al iniciar sessió");
                    }
                    break;

                case  "JOC2": //Al clicar el boto per fer una partida de 2 persones
                    model.setPartida(new Partida(2));
                    network.avisaServer("JOC2");
                    vista.changePanel("JOC");
                    //Escolta la resposta del servidor per saber si ha de canviar a la finestra de joc
                    break;

                case  "JOC4": //Al clicar el boto per fer una partida de 4 persones
                    model.setPartida(new Partida(4));
                    network.avisaServer("JOC4");
                    vista.changePanel("JOC");
                    //Escolta la resposta del servidor per saber si ha de canviar a la finestra de joc
                    break;

                case "CAMPEONAT": //Al clicar el boto per fer una partida de campionat
                    model.setPartida(new Partida(4));
                    network.avisaServer("CAMPEONAT");
                    vista.changePanel("JOC");
                    //Escolta la resposta del servidor per saber si ha de canviar a la finestra de joc
                    break;

                case "GUARDAR": //Al guardar els controls
                    if (model.comprovaControls(vista.getControls())) { //Comprova els controls
                        network.avisaServer("CONTROLS");
                        network.passaControls(vista.getControls());
                        vista.actualitzaTecles(vista.getControls());
                        vista.changePanel("RANQUING");
                    } else {
                        JOptionPane.showMessageDialog(null, "Hi ha un control repetit!");
                    }
                    break;

                case "CONTROLS":
                    vista.changePanel("CONTROLS");
                    break;

                case "TANCAR":
                    network.avisaServer("TANCARSESSIO");
                    vista.changePanel("INICI");
                    network.tancarSessio();
                    break;

            }
        } catch (IOException ioe) {
            ioe.getMessage();
        }catch (ClassNotFoundException e1){
            e1.printStackTrace();
        }

    }
}

