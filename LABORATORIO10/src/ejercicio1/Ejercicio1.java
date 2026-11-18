package ejercicio1;

import javax.swing.*;
import java.awt.*;

public class Ejercicio1 extends JFrame {

    private JTextField txtNombre = new JTextField(15);
    private JTextField txtPrecio = new JTextField(15);
    private JTextField txtStock = new JTextField(15);
    private JTextField txtCategoria = new JTextField(15);
    private JButton btnActualizar = new JButton("Actualizar Producto");
    private JLabel lblInfo = new JLabel("Sin datos");

    private Producto producto = new Producto();

    public Ejercicio1() {
        setTitle("Gestion de Producto");
        setSize(350, 330);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        add(new JLabel("Nombre:"));
        add(txtNombre);
        add(new JLabel("Precio:"));
        add(txtPrecio);
        add(new JLabel("Stock:"));
        add(txtStock);
        add(new JLabel("Categoria:"));
        add(txtCategoria);
        add(btnActualizar);
        add(lblInfo);

        btnActualizar.addActionListener(e -> actualizar());
    }

    private void actualizar() {
        try {
            // 1. Actualizar el modelo
            producto.setNombre(txtNombre.getText());
            producto.setPrecio(Double.parseDouble(txtPrecio.getText()));
            producto.setCantidadStock(Integer.parseInt(txtStock.getText()));
            producto.setCategoria(txtCategoria.getText());

            // 2. Mostrar en la etiqueta
            lblInfo.setText("<html>Nombre: " + producto.getNombre()
                    + "<br>Precio: S/ " + producto.getPrecio()
                    + "<br>Stock: " + producto.getCantidadStock()
                    + "<br>Categoria: " + producto.getCategoria() + "</html>");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Precio y stock deben ser numeros");
        }
    }

    public static void main(String[] args) {
        new Ejercicio1().setVisible(true);
    }
}
