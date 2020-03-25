package Vista;

import Model.Serp;
import controlador.ControladorJoc;
import Model.Posicio;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


/**
 * Created by Grup 6 on 26/04/2017. VistaJoc.
 * JFrame que conté les vistes del Client.
 */

public class VistaJoc extends JPanel {

   //Atributs
    private Timer t;
    private boolean cont;
    private boolean fi;
    private int punts;
    private String posicio;
    private int total;
    private boolean abandona;
    private int contador;
    private ArrayList<Serp> serps;

    /**
     * Constructor
     */
    public VistaJoc(){
        this.setSize(350, 350);
        t = new Timer(1000, null);
        cont = true;
        fi = false;
        abandona = false;
    }

    /**
     * Procediment que inicialitza
     */
    public void iniciar(){
        cont = true;
        t.setDelay(1000);
        fi = false;
        t.start();
    }

    /**
     * Mètode utilitzat per abandonar una partida
     */
    public void sortir(){
        t.stop();
        t.setActionCommand("TIMER");
        cont = true;
        fi = false;
    }

    /**
     * Mètode utilitzat per aturar la partida quan s'acaba
     */
    public void aturar(){
        fi = true;
        t.setActionCommand("FI");
        t.setDelay(1000);
    }

    public void addNotify() {
        super.addNotify();
        requestFocus();
    }

    /**
     * Mètode dela clase Graphics que pinta la pantalla de joc quan es crida repaint()
     * @param g
     */
    public void paintComponent(Graphics g) {

        ArrayList<ArrayList<Posicio>> posicions = new ArrayList<>();
        ArrayList<Posicio> caps = new ArrayList<>();
        for(int i = 0; i < serps.size(); i++){
            posicions.add(serps.get(i).getPosicions());
            caps.add(serps.get(i).getCap());
        }

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        if (cont){
            if(contador == 4){
                g2d.drawString("Esperant als altres jugadors...", 70, 150);
            }else {
                g2d.drawString(contador + "", 150, 150);
            }
        }else{
            for (int j = 0; j < serps.size(); j++) {
                ArrayList<Posicio> serp = posicions.get(j);
                Posicio cap = caps.get(j);
                switch (j) {
                    case 0:
                        g2d.setPaint(Color.RED);
                        break;
                    case 1:
                        g2d.setPaint(Color.BLUE);
                        break;
                    case 2:
                        g2d.setPaint(Color.GREEN);
                        break;
                    case 3:
                        g2d.setPaint(Color.YELLOW);
                        break;
                }
                g2d.drawLine(serp.get(serp.size() - 1).getX(), serp.get(serp.size() - 1).getY(), cap.getX(), cap.getY());

                for (int i = 0; i < serp.size() - 1; i++) {

                    g2d.drawLine(serp.get(i).getX(), serp.get(i).getY(), serp.get(i + 1).getX(), serp.get(i + 1).getY());
                }
            }
            if(fi){
                g2d.setPaint(Color.BLACK);
                g2d.drawString("Has quedat " + posicio, 70, 50);
                g2d.drawString("Has guanyat/perdut " + punts, 70, 100);
                g2d.drawString("En total tens " + total, 70, 150);

            }

        }
    }

    /**
     * Registra el controlador al Timer
     * @param cj
     */
    public void registraControlador (ControladorJoc cj){
        t.addActionListener(cj);
        t.setActionCommand("TIMER");
    }

    public void setCont(boolean cont) {
        if(cont){
           t.setDelay(1000);
        }else {
            t.setDelay(50);
        }
        this.cont = cont;
    }

    public boolean isCont() {
        return cont;
    }

    public void setPunts(int punts) {
        this.punts = punts;
    }

    public void setPosicio(String posicio) {
        this.posicio = posicio;
    }

    /**
     * Mètode que reincia els paràmetres de la partida quan es vol iniciar una de nova
     */
    public void reinicia(){
        t.setActionCommand("TIMER");
        cont = true;
        fi = false;
    }

    public void setTotal(int total){
        this.total = total;
    }

    public boolean isFi() {
        return fi;
    }

    public void setAbandona(boolean abandona) {
        this.abandona = abandona;
    }

    public boolean isAbandona() {
        return abandona;
    }

    public void setSerps(ArrayList<Serp> serps) {
        this.serps = serps;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }
}
