package tictactoe.vista;

import javax.swing.*;
import java.awt.*;

public class ConfiguracionDialog extends JDialog {
    private JTextField tamanoField;
    private JTextField simbolo1Field;
    private JTextField simbolo2Field;
    private JTextField nombreJ1Field;
    private JTextField nombreJ2Field;

    private int tamano;
    private String simbolo1;
    private String simbolo2;
    private String nombreJ1;
    private String nombreJ2;
    private boolean confirmado = false;

    public ConfiguracionDialog(JFrame parent) {
        super(parent, "Configuración del Juego", true);
        setLayout(new GridLayout(6, 2, 10, 10));
        setSize(400, 300);
        setLocationRelativeTo(parent);

        add(new JLabel("Tamaño del tablero (mínimo 3):"));
        tamanoField = new JTextField("3");
        add(tamanoField);

        add(new JLabel("Símbolo Jugador 1:"));
        simbolo1Field = new JTextField("X");
        add(simbolo1Field);

        add(new JLabel("Símbolo Jugador 2:"));
        simbolo2Field = new JTextField("O");
        add(simbolo2Field);

        add(new JLabel("Nombre Jugador 1:"));
        nombreJ1Field = new JTextField("Jugador 1");
        add(nombreJ1Field);

        add(new JLabel("Nombre Jugador 2:"));
        nombreJ2Field = new JTextField("Jugador 2");
        add(nombreJ2Field);

        JButton aceptarBtn = new JButton("Aceptar");
        aceptarBtn.addActionListener(e -> validarYAceptar());
        add(aceptarBtn);

        JButton cancelarBtn = new JButton("Cancelar");
        cancelarBtn.addActionListener(e -> dispose());
        add(cancelarBtn);
    }

    private void validarYAceptar() {
        try {
            tamano = Integer.parseInt(tamanoField.getText());
            if (tamano < 3) {
                JOptionPane.showMessageDialog(this, "El tamaño debe ser al menos 3");
                return;
            }

            simbolo1 = simbolo1Field.getText().trim();
            simbolo2 = simbolo2Field.getText().trim();

            if (simbolo1.isEmpty() || simbolo2.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Los símbolos no pueden estar vacíos");
                return;
            }

            if (simbolo1.equals(simbolo2)) {
                JOptionPane.showMessageDialog(this, "Los símbolos deben ser diferentes");
                return;
            }

            nombreJ1 = nombreJ1Field.getText();
            nombreJ2 = nombreJ2Field.getText();

            if (nombreJ1.isEmpty()) nombreJ1 = "Jugador 1";
            if (nombreJ2.isEmpty()) nombreJ2 = "Jugador 2";

            confirmado = true;
            dispose();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El tamaño debe ser un número válido");
        }
    }

    public boolean isConfirmado() {
        return confirmado;
    }

    public int getTamano() {
        return tamano;
    }

    public String getSimbolo1() {
        return simbolo1;
    }

    public String getSimbolo2() {
        return simbolo2;
    }

    public String getNombreJ1() {
        return nombreJ1;
    }

    public String getNombreJ2() {
        return nombreJ2;
    }
}