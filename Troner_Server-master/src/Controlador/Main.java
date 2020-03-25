package Controlador;

import Model.ModelUsuari;

import java.net.InetAddress;

import Vista.*;

import java.io.IOException;
import javax.swing.*;
import java.net.Socket;
import java.util.LinkedList;

/**
 * Main del Servidor
 * Created by Grup 6 on 30/03/2017.
 */
public class Main {
    private static LinkedList<Socket> sockets;

    public static void main (String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

               try {

                   VistaServidor vista = new VistaServidor();
                   System.out.println(InetAddress.getLocalHost());
                   ModelUsuari model = new ModelUsuari();
                   Controlador controlador = new Controlador(vista, model);


                   vista.registerController(controlador);

                   vista.setVisible(true);
               }catch (IOException e){
                   e.printStackTrace();
               }
            }

        });

    }

}
