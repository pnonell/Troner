package Model;

import java.io.Serializable;

/**
 * Created by Grup 6 on 10/05/17. Client.
 *  Classe que s'ecarrega de Iniciar Sessio
 */
public class Inicia implements Serializable{

    //Atributs
    private String nom;
    private String password;
    private int opcio;

    //Constructors
    public Inicia (String nom, String password){

        this.nom = nom;
        this.password = password;

        if (nom.indexOf('@') < 0){
            opcio = 1;
        }else{
            opcio = 2;
        }

    }

    //Metodes
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
