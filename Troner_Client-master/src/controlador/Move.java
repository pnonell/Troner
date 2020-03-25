package controlador;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Grup 6 on 03/05/2017 Move.
 * Classe que hereda de AbstractAction i s'encarrega de moure la serp
 */
public class Move extends AbstractAction{
    private ControladorJoc cj;
    private int i;

    public Move(int i, ControladorJoc cj){
        this.i = i;
        this.cj = cj;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        cj.moureSerp(i);
    }

}
