package Client_Servidor;

import Controlador.GestionarPartides;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Classe del thread general per establir connexió
 * Created by Grup 6 on 27/05/2017.
 */
public class Server extends Thread{


    //Atributs
    private ServerSocket  sSocket;
    private final ArrayList<DedicatedServer> dedicatedServers;

    private GestionarPartides gestionarPartides;

    private boolean running;
    private int port;


    //Mètodes

    /**
     * Constructor
     * @param port
     * @param gestionarPartides
     */
    public Server (int port, GestionarPartides gestionarPartides){
        this.port = port;
        this.gestionarPartides = gestionarPartides;
        dedicatedServers = new ArrayList<>();
    }

    /**
     * Inicialitza el socket
     * @throws IOException
     */
    public void startServer () throws IOException{

        sSocket = new ServerSocket(11111);
        running = true;
        start();
    }

    /**
     * Mètode que atura el servidor
     */
    public void stopServer(){
        running = false;
    }

    public ServerSocket getsSocket(){
        return sSocket;
    }

    /**
     * Mètode que executa el Thread
     */
    @Override
    public void run(){
        while (running) try{
            Socket socket = sSocket.accept();
            DedicatedServer dServer = new DedicatedServer(socket, gestionarPartides, dedicatedServers);
            dServer.start();
            dedicatedServers.add(dServer);

        }catch (IOException e){
        }
    }

}
