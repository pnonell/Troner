package Model;

import java.io.Serializable;

/**
 * Classe que conté les dades per iniciar sessió
 * Created by Grup 6 on 05/04/2017.
 */
public class Inicia implements Serializable{

    private String nom;
    private String password;
    private int opcio;

    public Inicia(String nom, String password){

        this.nom = nom;
        this.password = password;

        if (nom.indexOf('@') < 0){
            opcio = 1;
        }else{
            opcio = 2;
        }

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getOpcio() {
        return opcio;
    }

    public void setOpcio(int opcio) {
        this.opcio = opcio;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }
}
