package Client_Servidor;

import Model.*;
import Model.Client;
import Model.Usuari;
import Vista.VistaClient;
import controlador.ControladorJoc;

import java.io.*;
import java.net.Socket;

/**
 * Created by Grup 6 on 24/04/17. Client.
 * Classe que s'encarrega de comunicar-se amb el servidor
 */
public class Network{

    //Atributs
    private Client model;

    private Socket sServer;
    private ObjectOutputStream doStreamO;
    private ObjectInputStream diStreamO;

    private ThreadRebre tr;
    private VistaClient vista;
    private ControladorJoc cj;

    /**
     * Constructor
     * @param model Model
     * @param vista Vista
     */
    public Network(Client model, VistaClient vista){
        this.model = model;
        this.vista = vista;
    }

    /**
     * Classe que connecta el Socket amb els paràmetres establerts a la vista
     * @param port
     * @param IP
     * @return si s'ha conectat
     */
    public boolean connect(int port, String IP){

        try{

            sServer = new Socket(IP, port);

            doStreamO = new ObjectOutputStream(sServer.getOutputStream());
            diStreamO = new ObjectInputStream(sServer.getInputStream());

            return true;

        }catch (Exception e){
            return false;
        }
    }

    /**
     * Classe per enviar un usuari que s'acaba de registrar
     * @param usuari
     * @return si s'ha enviat bé o no
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public boolean registraUsuari(Usuari usuari) throws IOException, ClassNotFoundException{

        doStreamO.writeObject(usuari);
        return  (Boolean) diStreamO.readObject();    }

    /**
     * Classe que envia els paràmetres d'un usuari per iniciar sessió
     * @param inicia    login i password
     * @return si s'ha pogut iniciar sessió
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public boolean iniciaSessio(Inicia inicia) throws IOException, ClassNotFoundException{

        doStreamO.writeObject(inicia);
        return  (Boolean) diStreamO.readObject();
    }

    /**
     * Classe que envia els controls que s'hancanviat en el client
     * @param controls Array d'ints que conté els codis dels controls
     */
    public void passaControls(int[] controls){

        try {
            doStreamO.writeObject(controls[0]);
            doStreamO.writeObject(controls[1]);
            doStreamO.writeObject(controls[2]);
            doStreamO.writeObject(controls[3]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Mètode per rebre els controls enviats pel client
     * @return
     */
    public int[] rebreControls(){

        int[] controls = new int[4];
        try {
            controls[0]= (Integer) diStreamO.readObject();
            controls[1]= (Integer) diStreamO.readObject();
            controls[2]= (Integer) diStreamO.readObject();
            controls[3]= (Integer) diStreamO.readObject();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return controls;
    }

    /** Mètode que avisa al servidor amb un text
     * @param which text que rebrà el servidor
     * @throws IOException
     */
    public void avisaServer(String which) throws IOException{
        doStreamO.writeObject(which);
    }


    public ObjectOutputStream getDoStreamO() {
        return doStreamO;
    }

    /**
     * Mètode que inicia un ThreadRebre
     */
    public void iniciaRebre(){
        tr = new ThreadRebre(diStreamO, model, vista, this, cj);
        tr.start();
    }

    /**
     * Classe que tanca la comunicació al tancar sessió
     * @throws IOException
     */
    public void tancarSessio() throws IOException{

        doStreamO.close();
        diStreamO.close();
        sServer.close();
    }

    public void setControlador(ControladorJoc cj){
        this.cj = cj;
    }

}
