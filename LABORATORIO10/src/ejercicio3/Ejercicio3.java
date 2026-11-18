package ejercicio3;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Ejercicio3 extends JFrame {

    private JButton btnAplausos = new JButton("Aplausos");
    private JButton btnCampana = new JButton("Campana");
    private JButton btnExplosion = new JButton("Explosion");

    public Ejercicio3() {
        setTitle("Efectos de sonido");
        setSize(350, 120);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        add(btnAplausos);
        add(btnCampana);
        add(btnExplosion);

        btnAplausos.addActionListener(e -> reproducir("aplausos.wav"));
        btnCampana.addActionListener(e -> reproducir("campana.wav"));
        btnExplosion.addActionListener(e -> reproducir("explosion.wav"));
    }

    private void reproducir(String archivo) {
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(new File(archivo));
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "No se pudo reproducir: " + archivo);
        }
    }

    public static void main(String[] args) {
        new Ejercicio3().setVisible(true);
    }
}