package Client_Servidor;

import Controlador.GestionarPartides;
import Controlador.Partida2;
import Controlador.Partida4;
import Controlador.PartidaTorneig;
import Model.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;



/**
 * Classe del thread dedicat per a cada usuari
 * Created by Grup 6 on 27/06/2017.
 */
public class DedicatedServer extends Thread{
    private Socket sClient;
    private String login = "";
    private GestionarPartides gPartides;
    private Partida2 partida2;
    private Partida4 partida4;
    private PartidaTorneig partidaTorneig;
    private boolean running;
    private ObjectInputStream diStreamO;
    private ObjectOutputStream doStreamO;
    private ArrayList<DedicatedServer> dedicatedServers;
    private int num;
    private Inicia inicia;
    private int tipus;
    private boolean juga;

    /**
     * Constructor
     * @param sClient
     * @param gPartides
     * @param dedicatedServers
     * @throws IOException
     */
    public DedicatedServer(Socket sClient, GestionarPartides gPartides, ArrayList<DedicatedServer> dedicatedServers) throws IOException{
        this.sClient = sClient;
        this.gPartides = gPartides;
        this.dedicatedServers = dedicatedServers;
        partida2 = null;
        partida4 = null;
        partidaTorneig = null;
        doStreamO = new ObjectOutputStream(sClient.getOutputStream());
        diStreamO = new ObjectInputStream(sClient.getInputStream());
        juga = false;
    }

    /**
     * Mètode que executa el Thread
     */
    @Override
    public void run() {
       try {

           running = true;
           while (running){


               String opcio = (String) diStreamO.readObject();
               switch (opcio){

                   case "INICIARSESSIO": //Intenta iniciar sessió i avisa al Client si s'ha pogut fer

                       String aux;
                       inicia = (Inicia) diStreamO.readObject();

                       aux = new ModelUsuari().comprovaInicia(inicia);

                       if (aux.equals("error a ModelUsuari.comprovaInicia") || estaIniciat(aux)){
                           doStreamO.writeObject(false);
                       }
                       else {

                           doStreamO.writeObject(true);
                           new ModelUsuari().actualitzaData(aux);
                           this.login = aux;

                           doStreamO.writeObject("RANQUING");
                           doStreamO.writeObject(new ModelUsuari().getRanquing());

                           doStreamO.writeObject("ENVIACONTROLS");
                           int[] controls = (new ModelUsuari().getControls(login));
                           doStreamO.writeObject(controls[0]);
                           doStreamO.writeObject(controls[1]);
                           doStreamO.writeObject(controls[2]);
                           doStreamO.writeObject(controls[3]);
                       }
                       break;

                   case "REGISTRAR": //Intenta registrar un nou usuari i avisa al Client si s'ha pogut fer

                       Usuari usuari = (Usuari) diStreamO.readObject();

                       if (new ModelUsuari().registraUsuari(usuari.getLogin(), usuari.getMail(), usuari.getPassword(), usuari.getPassword())){
                           doStreamO.writeObject(true);
                           this.login = usuari.getLogin();

                           doStreamO.writeObject("RANQUING");
                           doStreamO.writeObject(new ModelUsuari().getRanquing());

                           doStreamO.writeObject("ENVIACONTROLS");
                           int[] controls = (new ModelUsuari().getControls(login));
                           doStreamO.writeObject(controls[0]);
                           doStreamO.writeObject(controls[1]);
                           doStreamO.writeObject(controls[2]);
                           doStreamO.writeObject(controls[3]);
                       }
                       else {
                           doStreamO.writeObject(false);
                       }
                       break;


                   case "JOC2": //Afegeix un jugador a partida 2
                        tipus = 2;
                       gPartides.addJoc2(this);
                       break;

                   case "JOC4": //Afegeix un jugador a partida 4
                        tipus = 4;
                       gPartides.addJoc4(this);
                       break;

                   case "CAMPEONAT": //Afegeix un jugador a campeonat
                        tipus = 5;
                       gPartides.addCampeonat(this);
                       break;

                   case "MOVIMENT": //Avisa als altres jugadors que s'ha realitzat un moviment


                       if(partida2 != null){
                           partida2.enviaSerp((int)diStreamO.readObject(), (Posicio)diStreamO.readObject(), sClient);
                       }else{
                           if(partida4 != null){
                               partida4.enviaSerp((int)diStreamO.readObject(), (Posicio)diStreamO.readObject(), sClient);
                           }else {
                               partidaTorneig.enviaSerp((int)diStreamO.readObject(), (Posicio)diStreamO.readObject(), sClient);
                           }
                        }
                       break;

                   case "ENVIACONTROLS": //Canvia els controls que el client ha establert
                       int [] controls = new ModelUsuari().getControls(login);
                       doStreamO.writeObject(controls[0]);
                       doStreamO.writeObject(controls [1]);
                       doStreamO.writeObject(controls [2]);
                       doStreamO.writeObject(controls [3]);


                   case "CONTROLS": //Rep els controls

                       int up = (Integer) diStreamO.readObject();
                       int down = (Integer) diStreamO.readObject();
                       int left = (Integer) diStreamO.readObject();
                       int right = (Integer) diStreamO.readObject();

                       new ModelUsuari().actualitzaControls(login,up,down,left,right);
                       break;

                   case "MORT": //Avisa que un jugador ha mort

                       if(partida2 != null){
                           partida2.haMort(sClient);
                       }else {
                           if(partida4 != null){
                               partida4.haMort(num);
                           }else{
                               partidaTorneig.haMort(sClient);
                           }
                       }
                       break;

                   case "ABANDONA": //Avisa que un jugador ha abandonat
                       ModelUsuari model = new ModelUsuari();
                        //Segons el tipus de partida i en quina fase d'aquesta esta, tindra un comportament o altre
                       switch (tipus) {
                           case 2:
                               model.updatePuntuacio(login, -10);
                               gPartides.gestionaAbandona(this, 2);
                               partida2 = null;

                               break;
                           case 4:
                               gPartides.gestionaAbandona(this, 4);
                               if (juga) {
                                   partida4.setAbandona(true);
                                   if(partida4.getPosicions()[num].equals("1r")){
                                       partida4.haMort(num);
                                   }
                                   switch (partida4.getMorts()) {
                                       case 0:
                                           model.updatePuntuacio(login, -20);
                                           break;
                                       case 1:
                                           model.updatePuntuacio(login, -10);
                                           break;
                                       case 2:
                                           model.updatePuntuacio(login, 10);
                                           break;
                                   }
                               }else{
                                   gPartides.gestionaAbandona(this, 4);

                               }
                               partida4 = null;

                               break;
                           case 5:
                          //Abandona torneig
                               if(juga) {
                                    switch (partidaTorneig.getRonda()){
                                        case 1:
                                            model.updatePuntuacio(login, -20);
                                            break;
                                        case 2:
                                            model.updatePuntuacio(login, -10);
                                            break;
                                        case 3:
                                            model.updatePuntuacio(login, -10);
                                            break;
                                    }
                                   if(partidaTorneig.getEliminats()[num]){
                                       partidaTorneig.setAbandona(true, num);
                                       gPartides.gestionaAbandona(this, 0);
                                       partidaTorneig = null;

                                   }else {
                                       partidaTorneig.setAbandona(true, num);
                                       partidaTorneig.haMort(sClient);
                                       gPartides.gestionaAbandona(this, 0);
                                       partidaTorneig.fiRonda();
                                       partidaTorneig = null;
                                   }
                               }else {
                                   gPartides.novaCuaTorneig(this);
                               }
                               break;

                       }

                       doStreamO.writeObject("RANQUING");
                       doStreamO.writeObject(new ModelUsuari().getRanquing());
                       juga = false;
                       break;

                   case "TANCARSESSIO": //Tanca la sessió d'un client
                       this.login="";
                       dedicatedServers.remove(this);
                       break;
               }
           }

       }catch (IOException e){
            //En cas que el jugador tanqui la finestra, el programa es comportarà com si haguès abandonat
               ModelUsuari model = new ModelUsuari();
               switch (tipus){
                   case 2:
                       model.updatePuntuacio(login, -10);
                       gPartides.gestionaAbandona(this,2);
                       partida2 = null;
                       break;
                   case 4:
                       gPartides.gestionaAbandona(this, 4);
                       if (juga) {
                           partida4.setAbandona(true);
                           if(partida4.getPosicions()[num].equals("1r")){
                               partida4.haMort(num);
                           }
                           switch (partida4.getMorts()) {
                               case 0:
                                   model.updatePuntuacio(login, -20);
                                   break;
                               case 1:
                                   model.updatePuntuacio(login, -10);
                                   break;
                               case 2:
                                   model.updatePuntuacio(login, 10);
                                   break;
                           }
                       }else{
                           gPartides.gestionaAbandona(this, 4);

                       }
                       partida4 = null;

                       break;
                   case 5:
                       //Abandona torneig
                       if(juga) {
                           switch (partidaTorneig.getRonda()){
                               case 1:
                                   model.updatePuntuacio(login, -20);
                                   break;
                               case 2:
                                   model.updatePuntuacio(login, -10);
                                   break;
                               case 3:
                                   model.updatePuntuacio(login, -10);
                                   break;
                           }
                           if(partidaTorneig.getEliminats()[num]){
                               partidaTorneig.setAbandona(true, num);
                               gPartides.gestionaAbandona(this, 0);
                               partidaTorneig = null;

                           }else {
                               partidaTorneig.setAbandona(true, num);
                               partidaTorneig.haMort(sClient);
                               gPartides.gestionaAbandona(this, 0);
                               partidaTorneig.fiRonda();
                               partidaTorneig = null;
                           }
                       }else {
                           try{
                               gPartides.novaCuaTorneig(this);
                           }catch (IOException ex){

                           }
                       }
                       break;
               }
           dedicatedServers.remove(this);

       }catch (SQLException e){
           e.printStackTrace();
       }catch (ClassNotFoundException e){
           e.printStackTrace();
       }

    }

    public String getLogin() {
        return login;
    }

    /**
     * Avisa si l'usuari que es pasa per paràmetres ha iniciat sessió
     * @param login
     * @return si l'usuari està iniciat
     */
    public boolean estaIniciat(String login){

        for(int i = 0; i < dedicatedServers.size(); i++){

            if (dedicatedServers == null){
                return false;
            }

            if(dedicatedServers.get(i).getLogin().equals(login)){
                return true;
            }
        }
        return false;
    }

    public Socket getsClient() {
        return sClient;
    }

    public ObjectOutputStream getDoStreamO() {
        return doStreamO;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void setPartida2(Partida2 partida2) {
        this.partida2 = partida2;
    }

    public void setPartida4(Partida4 partida4) {
        this.partida4 = partida4;
    }

    public void setPartidaTorneig(PartidaTorneig partidaTorneig) {
        this.partidaTorneig = partidaTorneig;
    }

    /**
     * Mètode que s'activa quan s'ha d'acabar la partida 4 perquè anteriorment algú havia abandonat
     */
    public void acabaPartida4(){
        try{
            gPartides.acabaPartida4(this);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Mètode que s'activa quan s'ha d'acabar la partida torneig perquè anteriorment algú havia abandonat
     */
    public void acabaPartidaTorneig(){
        try{
            gPartides.acabaPartidaTorneig(this);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void setJuga(boolean juga) {
        this.juga = juga;
    }

    public boolean isJuga() {
        return juga;
    }
}
