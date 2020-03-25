
package Vista;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import controlador.ControladorJoc;
import controlador.Move;

/**
 * Created by Grup 6 on 20/04/2017. IniciarSessio.
 * Vista que conté els formularis de Iniciar Sessio o Registre.
 */
public class Joc extends JPanel {

    //Atributs
    private JLabel label1;
    private JLabel rondes1;
    private JLabel label2;
    private JLabel rondes2;
    private JLabel label3;
    private JLabel rondes3;
    private JLabel label4;
    private JLabel rondes4;
    private VistaJoc panel1;
    private JButton button1;
    private int teclaUp;
    private int teclaDown;
    private int teclaLeft;
    private int teclaRight;

    //Constructors

    /**
     * Constructor
     */
    public Joc() {
        label1 = new JLabel();
        rondes1 = new JLabel();
        label2 = new JLabel();
        rondes2 = new JLabel();
        label3 = new JLabel();
        rondes3 = new JLabel();
        label4 = new JLabel();
        rondes4 = new JLabel();
        panel1 = new VistaJoc();
        button1 = new JButton();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel jpsuperior = new JPanel();
        jpsuperior.setLayout(new GridLayout(1, 8));
        jpsuperior.add(label1);
        jpsuperior.add(rondes1);
        jpsuperior.add(label2);
        jpsuperior.add(rondes2);
        jpsuperior.add(label3);
        jpsuperior.add(rondes3);
        jpsuperior.add(label4);
        jpsuperior.add(rondes4);
        jpsuperior.setMinimumSize(new Dimension(1000, 100));
        jpsuperior.setMaximumSize(new Dimension(1000, 100));
        add(jpsuperior);

        panel1.setMaximumSize(new Dimension(350, 350));
        panel1.setMinimumSize(new Dimension(350, 350));
        panel1.setBackground(new Color(255, 255, 255));
        panel1.setBorder(LineBorder.createBlackLineBorder());
        add(panel1);

        button1.setText("Abandonar");
        add(button1);
    }

    //Metodes

    /**
     * Registra el controlador
     * @param cj ControladorJoc
     * @return void
     */
    public void registraControlador (ControladorJoc cj){

        panel1.registraControlador(cj);
        button1.addActionListener(cj);
        button1.setActionCommand("ABANDONA");

        panel1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(87,0), "up");
        panel1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(83,0), "down");
        panel1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(65,0), "left");
        panel1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(68,0), "right");
        panel1.getActionMap().put("up", new Move(1, cj));
        panel1.getActionMap().put("down", new Move(2, cj));
        panel1.getActionMap().put("left", new Move(3, cj));
        panel1.getActionMap().put("right", new Move(4, cj));

    }

    public VistaJoc getPanel1() {
        return panel1;
    }

    /**
     * Inicia el funcionament del Joc
     * @return void
     */
    public void iniciaJoc(){
        panel1.iniciar();
    }

    /**
     * Mètode que col·loca els noms dels jugadors a les vistes del joc
     * @param logins
     */
    public void insereixJugador(String[] logins){
        switch (logins.length){
            case 2:
                label1.setText(logins[0]);
                label1.setForeground(Color.RED);
                label4.setText(logins[1]);
                label4.setForeground(Color.BLUE);
                break;
            case 3:
                label1.setText(logins[0]);
                label1.setForeground(Color.RED);
                label2.setText(logins[1]);
                label2.setForeground(Color.BLUE);
                label3.setText(logins[2]);
                label3.setForeground(Color.GREEN);
                break;
            case 4:
                label1.setText(logins[0]);
                label1.setForeground(Color.RED);
                label2.setText(logins[1]);
                label2.setForeground(Color.BLUE);
                label3.setText(logins[2]);
                label3.setForeground(Color.GREEN);
                label4.setText(logins[3]);
                label4.setForeground(Color.YELLOW);
                break;

        }
    }


    /**
     * Setter que estableix les rondes de Joc
     * @param rondes
     */
    public void setRondes(int[] rondes){
        switch (rondes.length){
            case 2:
                rondes1.setText(rondes[0] + "");
                rondes4.setText(rondes[1] + "");
                break;
            case 3:
                rondes1.setText(rondes[0] + "");
                rondes2.setText(rondes[1] + "");
                rondes3.setText(rondes[2] + "");
                break;
            case 4:
                rondes1.setText(rondes[0] + "");
                rondes2.setText(rondes[1] + "");
                rondes3.setText(rondes[2] + "");
                rondes4.setText(rondes[3] + "");
                break;
        }
    }

    /**
     * Atura el Joc
     * @return void
     */
    public void aturar() {
        panel1.aturar();

    }

    /**
     * Reinicia Joc
     */
    public void reinicia() {

        label1.setText("");
        label2.setText("");
        label3.setText("");
        label4.setText("");
        rondes1.setText("");
        rondes2.setText("");
        rondes3.setText("");
        rondes4.setText("");
        panel1.sortir();
        panel1.repaint();
    }


    /**
     * Procediment que serveix per assignar els controls durant la partida, segons cada usuari.
     * @param tecla array de ints que conté cada Int assignat a la teclada guardada per l'usuari.
     */
    public void actualitzaTecles(int[] tecla){

        System.out.println(tecla[0]);
        teclaUp = tecla[0];
        teclaDown = tecla[1];
        teclaLeft = tecla[2];
        teclaRight = tecla[3];
        panel1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(teclaUp,0), "up");
        panel1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(teclaDown,0), "down");
        panel1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(teclaLeft,0), "left");
        panel1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(teclaRight,0), "right");

    }

}
