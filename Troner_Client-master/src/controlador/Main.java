package controlador;

import Client_Servidor.Network;
import Vista.*;
import Model.Client;

import javax.swing.*;

/**
 * Main del controlador del Client del Troner
 * Created by Grup 6 on 15/03/2017.
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //Inicialitzar
                Client model = new Client();
                VistaClient vista = new VistaClient();
                Network network = new Network(model, vista);

                Controlador  controlador= new Controlador(vista,model,network);

                ControladorJoc cj = new ControladorJoc(vista, model, network);
                vista.registerController(controlador, cj);
                network.setControlador(cj);

                vista.setVisible(true);
            }
        });
    }
}