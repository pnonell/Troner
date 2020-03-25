package Vista;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 * Created by Grup 6. GraficUsuari.
 * Vista on apareixen els camps de port, per tal de que un usuari es connecti un cop hagi clicat el botó Iniciar.
 */

public class GraficUsuari extends JPanel {

    //Atributs
    private static final Stroke GRAPH_STROKE = new BasicStroke(2f);
    private int padding = 25; //Relleno
    private int labelPadding = 25;
    private Color lineColor2 = new Color (115, 103, 231, 209);
    private Color gridColor = new Color(200, 200, 200, 200); //Color de la graella
    private int pointWidth = 4;
    private int divisions = 0;
    private List<Integer> punts;

    /**
     * Constructor
     * @param punts
     */
    public GraficUsuari(List<Integer> punts) {
        this.punts = punts;
    }

    //Metodes


    /**
     * Pinta per pantalla els components del gràfic del jugador seleccionat.
     * @param g Graphics de Swing.
     */
    @Override
    protected void paintComponent(Graphics g) {

        divisions = getPunts().size() - 1;
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int amplada = getWidth() - 2 * padding - labelPadding;
        int alcada = getHeight() - 2 * padding - labelPadding;
        double xScale = amplada / (punts.size() - 1);
        double yScale = alcada / (getMaxPunts() - getMinPunts());

        List<Point> graphPoints = new ArrayList<>();

        for (int i = 0; i < punts.size(); i++) {
            int x1 = (int) (i * xScale + padding + labelPadding);
            int y1 = (int) ((getMaxPunts() - punts.get(i)) * yScale + padding);
            graphPoints.add(new Point(x1, y1));
        }

        // Pintar el fons blanc
        g2.setColor(Color.WHITE);
        g2.fillRect(padding + labelPadding, padding, getWidth() - (2 * padding) - labelPadding, getHeight() - 2 * padding - labelPadding);
        g2.setColor(Color.BLACK);
        g2.drawString("Punts", padding,   padding - 10); //Printem punts

        //Creacio de la grid i càlcul de l'eix de les y
        for (int i = 0; i < divisions + 1; i++) {
            int x0 = padding + labelPadding;
            int x1 = pointWidth + padding + labelPadding;
            int y0 = getHeight() - ((i * (getHeight() - padding * 2 - labelPadding)) / divisions + padding + labelPadding);
            int y1 = y0;
            if (punts.size() > 0) {
                g2.setColor(gridColor);
                g2.drawLine(padding + labelPadding + 1 + pointWidth, y0, getWidth() - padding, y1);
                g2.setColor(Color.BLACK);
                String yLabel = ((int) ((getMinPunts() + (getMaxPunts() - getMinPunts()) * ((i * 1.0) / divisions)) * 100)) / 100 + "";
                FontMetrics metrics = g2.getFontMetrics();  //Medim el text
                int labelWidth = metrics.stringWidth(yLabel);
                g2.drawString(yLabel, x0 - labelWidth - 5, y0 + (metrics.getHeight() / 2) - 3); //Printem els numeros de les y
            }
            g2.drawLine(x0, y0, x1, y1);
        }

        g2.drawString("Partida", getWidth() - padding - labelPadding, getHeight() - labelPadding + 10); //Mostrar label partida

        //Creacio de la grid i càlcul de l'eix de les x
        for (int i = 0; i < punts.size(); i++) {
            if (punts.size() > 1) {
                int x0 = i * (getWidth() - padding * 2 - labelPadding) / (punts.size() - 1) + padding + labelPadding;
                int x1 = x0;
                int y0 = getHeight() - padding - labelPadding;
                int y1 = y0 - pointWidth;
                if ((i % ((int) ((punts.size() / 20.0)) + 1)) == 0) {
                    g2.setColor(gridColor);
                    g2.drawLine(x0, getHeight() - padding - labelPadding - 1 - pointWidth, x1, padding);
                    g2.setColor(Color.BLACK);
                    String xLabel = i + "";
                    FontMetrics metrics = g2.getFontMetrics();
                    int labelWidth = metrics.stringWidth(xLabel);
                    g2.drawString(xLabel, x0 - labelWidth / 2, y0 + metrics.getHeight() + 3);
                }
                g2.drawLine(x0, y0, x1, y1);
            }
        }

        // Crear els eixos
        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, padding + labelPadding, padding);
        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, getWidth() - padding, getHeight() - padding - labelPadding);

       g2.setColor(lineColor2);

        for (int i = 0; i < graphPoints.size() - 1; i++) {
            int x1 = graphPoints.get(i).x;
            int y1 = graphPoints.get(i).y;
            int x2 = graphPoints.get(i + 1).x;
            int y2 = graphPoints.get(i + 1).y;
            g2.drawLine(x1, y1, x2, y2);
        }

    }

    /**
     * @return double que indica els punts minims del jugador.
     */
    private double getMinPunts() {

        double minPunts = Double.MAX_VALUE;
        for (Integer score : punts) {
            minPunts = Math.min(minPunts, score);
        }
        return minPunts;
    }

    private double getMaxPunts() {
        double maxPunts = Double.MIN_VALUE;
        for (Integer score : punts) {
            maxPunts = Math.max(maxPunts, score);
        }
        return maxPunts;
    }

    public void setPunts(List<Integer> punts) {
        this.punts = punts;
        invalidate();
        this.repaint();
    }

    public List<Integer> getPunts() {
        return punts;
    }

}