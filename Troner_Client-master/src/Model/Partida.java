package Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe partida del Model del Client.
 *
 * Gestiona les serps de la partida i comprova que no hi hagi colisions.
 *
 * Created by Grup 6 on 06/04/2017.
 */
public class Partida implements Serializable{

    //Atributs
    private ArrayList<Serp> serps = new ArrayList<>();
    private int serp;
    private int[] rondes;

    //Constructors
    public Partida(){

    }

    //Metodes
    public Partida(int s){
        rondes = new int[s];
        for(int i = 0; i < s; i++){
            serps.add(new Serp(i));
            rondes[i] = 0;
        }
    }


    /**
     * Procediment que comprova tots els cassos possibles de col·lisions.
     * @return boolean que indica si hi ha hagut xoc o no.
     */
    public boolean comprovaCollisio(){

        Model.Posicio cap = serps.get(serp).getCap();
        //Comprova que no surti dels limits de la partida
        if(cap.getX() > 350 || cap.getX() < 0 || cap.getY() > 350 || cap.getY() < 0){
            return true;
        }
        //Comprova que no xoqui amb ella mateixa
        ArrayList<Posicio> posicions = serps.get(serp).getPosicions();
        for(int i = 0; i < posicions.size() - 1; i++){
            if(posicions.get(i).getX() > posicions.get(i+1).getX() && cap.getX() <= posicions.get(i).getX() && cap.getX() >= posicions.get(i + 1).getX() && cap.getY() == posicions.get(i).getY()){
                return true;
            }
            if(posicions.get(i).getX() < posicions.get(i+1).getX() && cap.getX() >= posicions.get(i).getX() && cap.getX() <= posicions.get(i + 1).getX() && cap.getY() == posicions.get(i).getY()){
                return true;
            }
            if(posicions.get(i).getY() > posicions.get(i+1).getY() && cap.getY() <= posicions.get(i).getY() && cap.getY() >= posicions.get(i + 1).getY() && cap.getX() == posicions.get(i).getX()){
                return true;
            }
            if(posicions.get(i).getY() < posicions.get(i+1).getY() && cap.getY() >= posicions.get(i).getY() && cap.getY() <= posicions.get(i + 1).getY() && cap.getX() == posicions.get(i).getX()){
                return true;
            }
        }

        //Comprova que no xoqui amb altres serps
        for(int i = 0; i < serps.size(); i++){
            ArrayList<Posicio> pos = serps.get(i).getPosicions();
            Posicio c = serps.get(i).getCap();
            if(i != serp){
                //OLLA MAXIMA

                if(c.getX() > pos.get(pos.size() - 1).getX() && cap.getX() < c.getX() && cap.getX() > pos.get(pos.size() - 1).getX() && cap.getY() == c.getY()){
                    return true;
                }
                if(c.getX() < pos.get(pos.size() - 1).getX() && cap.getX() > c.getX() && cap.getX() < pos.get(pos.size() - 1).getX() && cap.getY() == c.getY()){
                    return true;
                }
                if(c.getY() > pos.get(pos.size() - 1).getY() && cap.getY() < c.getY() && cap.getY() > pos.get(pos.size() - 1).getY() && cap.getX() == c.getX()){
                    return true;
                }
                if(c.getY() < pos.get(pos.size() - 1).getY() && cap.getY() > c.getY() && cap.getY() < pos.get(pos.size() - 1).getY() && cap.getX() == c.getX()){
                    return true;
                }
                for(int j = 0; j < pos.size() - 1; j++){
                    if(pos.get(j).getX() > pos.get(j+1).getX() && cap.getX() < pos.get(j).getX() && cap.getX() > pos.get(j + 1).getX() && cap.getY() == pos.get(j).getY()){
                        return true;
                    }
                    if(pos.get(j).getX() < pos.get(j+1).getX() && cap.getX() > pos.get(j).getX() && cap.getX() < pos.get(j + 1).getX() && cap.getY() == pos.get(j).getY()){
                        return true;
                    }
                    if(pos.get(j).getY() > pos.get(j+1).getY() && cap.getY() < pos.get(j).getY() && cap.getY() > pos.get(j + 1).getY() && cap.getX() == pos.get(j).getX()){
                        return true;
                    }
                    if(pos.get(j).getY() < pos.get(j+1).getY() && cap.getY() > pos.get(j).getY() && cap.getY() < pos.get(j + 1).getY() && cap.getX() == pos.get(j).getX()){
                        return true;
                    }
                }

            }
        }

        return false;
    }

    /**
     * Getter de serps
     * @return Objecte serp
     */

    public ArrayList<Serp> getSerps() {
        return serps;
    }

    public void setSerp(int serp) {

        this.serp = serp;
    }

    public int getSerp() {
        return serp;
    }

    public void mouSerp(int dir, Posicio cap, int jug){
        serps.get(jug).canviaDireccio(dir, cap);
    }

    /**
     * Inicialitza les serps en el punt de partida.
     */
    public void reinicia(){
        int s = serps.size();
        serps = new ArrayList<>();
        for(int i = 0; i < s; i++){
            serps.add(new Serp(i));
        }
    }

    public int[] getRondes() {
        return rondes;
    }

    public void setRondes(int i) {
        rondes[i]++;
    }


}
