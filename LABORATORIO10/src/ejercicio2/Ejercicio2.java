package ejercicio2;

import javax.swing.*;
import java.awt.*;

public class Ejercicio2 extends JFrame {

    private String[] dias = {"Lun", "Mar", "Mie", "Jue", "Vie", "Sab", "Dom"};
    private JTextField[] campos = new JTextField[7];
    private JButton btnMostrar = new JButton("Mostrar Grafico");
    private PanelGrafico panel = new PanelGrafico();

    public Ejercicio2() {
        setTitle("Temperatura semanal");
        setSize(600, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel superior con los 7 campos
        JPanel arriba = new JPanel();
        for (int i = 0; i < 7; i++) {
            campos[i] = new JTextField(4);
            arriba.add(new JLabel(dias[i] + ":"));
            arriba.add(campos[i]);
        }
        arriba.add(btnMostrar);

        add(arriba, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);

        btnMostrar.addActionListener(e -> mostrar());
    }

    private void mostrar() {
        try {
            double[] temps = new double[7];
            for (int i = 0; i < 7; i++) {
                temps[i] = Double.parseDouble(campos[i].getText());
            }
            panel.setTemperaturas(temps);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingresa 7 temperaturas validas");
        }
    }

    // Panel donde se dibuja el grafico
    class PanelGrafico extends JPanel {
        private double[] temps = null;

        public void setTemperaturas(double[] temps) {
            this.temps = temps;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (temps == null) return;

            int margen = 50;
            int ancho = getWidth() - 2 * margen;
            int alto = getHeight() - 2 * margen;

            // Buscar minimo y maximo para escalar
            double min = temps[0], max = temps[0];
            for (double t : temps) {
                if (t < min) min = t;
                if (t > max) max = t;
            }
            if (max == min) max = min + 1;

            // Ejes
            g.drawLine(margen, margen, margen, margen + alto);
            g.drawLine(margen, margen + alto, margen + ancho, margen + alto);

            int[] xs = new int[7];
            int[] ys = new int[7];
            for (int i = 0; i < 7; i++) {
                xs[i] = margen + i * ancho / 6;
                ys[i] = margen + alto - (int) ((temps[i] - min) / (max - min) * alto);
            }

            // Lineas
            g.setColor(Color.BLUE);
            for (int i = 0; i < 6; i++) {
                g.drawLine(xs[i], ys[i], xs[i + 1], ys[i + 1]);
            }

            // Puntos y etiquetas
            for (int i = 0; i < 7; i++) {
                g.setColor(Color.RED);
                g.fillOval(xs[i] - 4, ys[i] - 4, 8, 8);
                g.setColor(Color.BLACK);
                g.drawString(dias[i], xs[i] - 10, margen + alto + 20);
                g.drawString(String.valueOf(temps[i]), xs[i] - 10, ys[i] - 8);
            }
        }
    }

    public static void main(String[] args) {
        new Ejercicio2().setVisible(true);
    }
}