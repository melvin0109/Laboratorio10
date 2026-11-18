package ejercicio4;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Ejercicio4 extends JFrame {

    private JButton btnReproducir = new JButton("Reproducir");
    private JButton btnPausar = new JButton("Pausar");
    private JButton btnReanudar = new JButton("Reanudar");

    private Clip clip;
    private long posicion = 0; // donde se pauso (en microsegundos)

    public Ejercicio4() {
        setTitle("Reproductor de musica");
        setSize(350, 120);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        add(btnReproducir);
        add(btnPausar);
        add(btnReanudar);

        cargarAudio("musica.wav");

        btnReproducir.addActionListener(e -> {
            if (clip != null) {
                clip.setMicrosecondPosition(0); // desde el inicio
                clip.start();
            }
        });

        btnPausar.addActionListener(e -> {
            if (clip != null && clip.isRunning()) {
                posicion = clip.getMicrosecondPosition(); // guardar posicion
                clip.stop();
            }
        });

        btnReanudar.addActionListener(e -> {
            if (clip != null) {
                clip.setMicrosecondPosition(posicion); // continuar
                clip.start();
            }
        });
    }

    private void cargarAudio(String archivo) {
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(new File(archivo));
            clip = AudioSystem.getClip();
            clip.open(audio);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "No se pudo cargar: " + archivo);
        }
    }

    public static void main(String[] args) {
        new Ejercicio4().setVisible(true);
    }
}