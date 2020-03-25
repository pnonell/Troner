package Controlador;

import Client_Servidor.Server;
import Model.ModelUsuari;
import Vista.VistaServidor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Arxiu;

/**
 * Classe del controlador
 * Created by Grup 6 on 30/03/2017.
 */
public class Controlador implements ActionListener{

    //Atributs
    private VistaServidor vista;
    private ModelUsuari model;
    private Server server;
    private final GestionarPartides gPartides;
    private boolean connectat = false;

    /**
     * Constructor
     * @param vista
     * @param model
     * @throws IOException
     */
    public Controlador(VistaServidor vista, ModelUsuari model) throws IOException{

        this.vista = vista;
        this.model = model;
        gPartides = new GestionarPartides();
        server = new Server(vista.getPort(), gPartides);
        Arxiu arxiu = new Arxiu();
        arxiu = arxiu.llegeixDades();
        vista.actualitzaPort(arxiu.getportClient());

    }

    //Metodes

    /**
     * Mètode que s'activa quan s'ha fet alguna acció en un Listener
     * @param event
     */
    @Override
    public void actionPerformed(ActionEvent event){

        try {
            switch (event.getActionCommand()){
                case "GEST":
                    vista.gsUpdateList(model.recuperaLogins());
                    vista.changePanel(event.getActionCommand());
                    break;

                case "GRAFIC":
                    vista.grUupdateLoginList(model.recuperaLogins());
                    vista.changePanel(event.getActionCommand());
                    break;

                case "RANQUING":
                    vista.rankingUpdateList(model.ompleRanquing());
                    vista.changePanel(event.getActionCommand());
                    break;

                case "REGISTRAR BOTO":
                    vista.showMessage(model.registraUsuari(vista.getLogin(), vista.getMail(), vista.getPassword(), vista.getPassword2()));
                    break;

                case "ELIMINA":
                    model.eliminaUsuari(vista.gsGetSelectedLogin());
                    vista.gsUpdateList(model.recuperaLogins());
                    break;

                case "INICIAR":
                    if (!connectat){
                        server.startServer();
                        connectat = true;
                    }
                    Arxiu arxiu = new Arxiu();
                    arxiu.escriuPort(vista.getPort());
                    break;

                case "ATURAR":
                    if (connectat){

                        connectat = false;
                        server.stopServer();
                        server.getsSocket().close();
                        server = new Server(vista.getPort(), gPartides);
                    }
                    break;

                case "TRIA":
                    vista.gsUpdateInfo(model.recuperaDadesUsuari(vista.gsGetSelectedLogin()));
                    break;

                case "LOGIN":
                    ArrayList<Integer> punts = model.getHistorial(vista.getGraficLogin());

                    if (punts.size() != 1) {
                        vista.creaGrafic(punts);
                    } else if (vista.getGraficLogin()== null) {}
                    else {
                        JOptionPane.showMessageDialog(null, "Aquest jugador no ha jugat cap partida!");
                    }
                    break;

                default:
                    vista.changePanel(event.getActionCommand());
                    break;
            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
