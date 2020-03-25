package controlador;

import Client_Servidor.Network;
import Model.Client;
import Model.Partida;
import Vista.VistaClient;
import Model.Serp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;



/**
 * Created by Grup 6 on 03/05/2017 ControladorJoc.
 * Controlador del Joc
 */
public class ControladorJoc implements ActionListener {

    //Atributs
    private Client model;
    private Network network;
    private VistaClient vista;
    private int contador;
    private int fi;

    /**
     * Constructor
     * @param vista Vista
     * @param model Model
     * @param network Network
     */
    public ControladorJoc(VistaClient vista, Client model, Network network){
        this.vista = vista;
        this.model = model;
        contador = 4;
        this.network = network;
        fi = 5;
        vista.getJoc().setContador(contador);
        vista.getJoc().setSerps(getSerps());
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        try {

            switch (e.getActionCommand()){
                case "ABANDONA":    //Al clicar abandona
                    model.abandonaPartida();
                    fi = 5;
                    contador = 4;
                    vista.reinicia();
                    vista.changePanel("RANQUING");
                    network.avisaServer("ABANDONA");
                    break;

                case "TIMER":

                    if(vista.getJoc().isCont()){
                        contador--;

                        if(contador <= 0){
                            vista.getJoc().setCont(false);
                        }
                    }else{

                   for(int i = 0; i< model.getPartida().getSerps().size(); i++) {
                       if(model.getPartida().getSerps().get(i).isViu() && !model.getEliminats()[i]){
                           model.getPartida().getSerps().get(i).mouSerp();
                       }
                   }
                   if(model.getPartida().getSerps().get(model.getPartida().getSerp()).isViu() && !model.getEliminats()[model.getPartida().getSerp()]){
                       if(model.getPartida().comprovaCollisio()){
                           model.getPartida().getSerps().get(model.getPartida().getSerp()).setViu(false);
                           try{
                               network.avisaServer("MORT");
                           }catch(IOException ex){
                               ex.printStackTrace();
                           }
                   }

                        }
                    }
                    vista.getJoc().setContador(contador);
                    vista.getJoc().setSerps(getSerps());
                    vista.getJoc().repaint();
                    break;
                case "FI":
                    if(fi <= 0){
                        vista.getJoc().reinicia();
                        model.getPartida().reinicia();
                        fi = 5;
                    }else{
                        fi--;
                    }
                    if(vista.getJoc().isAbandona()){
                        vista.getJoc().reinicia();
                        vista.getJoc().sortir();
                        vista.getJoc().setAbandona(false);
                    }
                    contador = 4;
                    vista.getJoc().setContador(contador);
                    vista.getJoc().setSerps(getSerps());
                    vista.getJoc().repaint();
                    break;
            }

        }
        catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    public Client getModel() {
        return model;
    }

    public void setModel(Client model) {
        this.model = model;
    }


    public int getContador() {
        return contador;
    }

    public ArrayList<Serp> getSerps(){
        return model.getPartida().getSerps();
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    /**Mètode que avisa al model per moure la serp i també avisa al servidor
     * @param d int que indica la Direcció
     */
    public void moureSerp(int d){

        if(vista.getJoc().isCont() == false && !vista.getJoc().isFi()) {
            try{
                model.getPartida().getSerps().get(model.getPartida().getSerp()).canviaDireccio(d, model.getPartida().getSerps().get(model.getPartida().getSerp()).getCap());
                network.avisaServer("MOVIMENT");
                network.getDoStreamO().writeObject(d);
                network.getDoStreamO().writeObject(model.getPartida().getSerps().get(model.getPartida().getSerp()).getUltim());
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }

    }

    /**
     * Mètode implementat al abandonar la partida
     */
    public void reinicia(){
        fi = 5;
        contador = 4;
    }
}
