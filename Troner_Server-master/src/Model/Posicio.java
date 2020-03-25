package Model;

import java.io.Serializable;

/**
 * Classe que s'encarrega de guardar les posicions X i Y de la serp.
 *
 * Created by Grup 6 on 06/04/2017.
 */
public class Posicio implements Serializable {
    private int x;
    private int y;

    /**
     * Constructor
     * @param x
     * @param y
     */
    public Posicio(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * Mètode que mou el cap segons la direcció de laserp
     * @param dir
     */
    public void mouCap(int dir){
        switch (dir) {
            case 1:
                x++;
                break;
            case 2:
                x--;
                break;
            case 3:
                y++;
                break;
            case 4:
                y--;
                break;
        }
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
