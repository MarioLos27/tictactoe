package tictactoe.vista;

import tictactoe.controlador.ControladorJuego;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    private ControladorJuego controlador;
    private JButton[][] botones;
    private JLabel turnoLabel;
    private JLabel j1Label;
    private JLabel j2Label;
    private JPanel panelTablero;
    private JPanel panelInfo;
    private JPanel panelControles;

    private int tamano;
    private String simbolo1, simbolo2;
    private String nombreJ1, nombreJ2;

    public GUI(ControladorJuego controlador, int tamano, String simbolo1, String simbolo2, String nombreJ1, String nombreJ2) {
        this.controlador = controlador;
        this.tamano = tamano;
        this.simbolo1 = simbolo1;
        this.simbolo2 = simbolo2;
        this.nombreJ1 = nombreJ1;
        this.nombreJ2 = nombreJ2;
        this.botones = new JButton[tamano][tamano];

        configurarVentana();
        crearInterfaz();
    }

    private void configurarVentana() {
        setTitle("TicTacToe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(600, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void crearInterfaz() {
        getContentPane().removeAll();

        crearPanelInfo();
        crearPanelTablero();
        crearPanelControles();

        revalidate();
        repaint();
    }

    private void crearPanelInfo() {
        panelInfo = new JPanel();
        j1Label = new JLabel();
        turnoLabel = new JLabel();
        j2Label = new JLabel();

        j1Label.setForeground(new Color(255, 0, 0));
        j2Label.setForeground(new Color(104, 45, 240));

        panelInfo.add(j1Label);
        panelInfo.add(turnoLabel);
        panelInfo.add(j2Label);

        this.add(panelInfo, BorderLayout.NORTH);
    }

    private void crearPanelTablero() {
        panelTablero = new JPanel(new GridLayout(tamano, tamano, 5, 5));

        for (int i = 0; i < tamano; i++) {
            for (int j = 0; j < tamano; j++) {
                final int fila = i;
                final int columna = j;

                botones[i][j] = new JButton();
                botones[i][j].setFont(new Font("Segoe UI Emoji", Font.PLAIN, 48));
                botones[i][j].setPreferredSize(new Dimension(80, 80));
                botones[i][j].addActionListener(e -> controlador.manejarClick(fila, columna));

                panelTablero.add(botones[i][j]);
            }
        }

        this.add(panelTablero, BorderLayout.CENTER);
    }

    private void crearPanelControles() {
        panelControles = new JPanel();

        JButton reiniciarBtn = new JButton("Reiniciar");
        reiniciarBtn.addActionListener(e -> controlador.reiniciarJuego());

        JButton nuevoJuegoBtn = new JButton("Nuevo juego");
        nuevoJuegoBtn.addActionListener(e -> solicitarNuevaConfiguracion());

        panelControles.add(reiniciarBtn);
        panelControles.add(nuevoJuegoBtn);

        this.add(panelControles, BorderLayout.SOUTH);
    }

    public void actualizarCelda(int fila, int columna, String simbolo) {
        botones[fila][columna].setText(simbolo);
        botones[fila][columna].setEnabled(false);
    }

    public void actualizarTurno(String simbolo) {
        turnoLabel.setText("Turno del jugador: " + simbolo);
        j1Label.setText("Jugador 1: " + nombreJ1 + " (" + simbolo1 + ")");
        j2Label.setText("Jugador 2: " + nombreJ2 + " (" + simbolo2 + ")");
    }

    public void limpiarTablero() {
        for (int i = 0; i < tamano; i++) {
            for (int j = 0; j < tamano; j++) {
                botones[i][j].setText("");
                botones[i][j].setEnabled(true);
            }
        }
    }

    public void mostrarFinJuego(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);

        int opcion = JOptionPane.showConfirmDialog(
                this,
                "¿Quieres jugar de nuevo?",
                "Fin del juego",
                JOptionPane.YES_NO_OPTION
        );

        if (opcion == JOptionPane.YES_OPTION) {
            controlador.reiniciarJuego();
        }
    }

    public void reconfigurar(int tamano, String simbolo1, String simbolo2, String nombreJ1, String nombreJ2) {
        this.tamano = tamano;
        this.simbolo1 = simbolo1;
        this.simbolo2 = simbolo2;
        this.nombreJ1 = nombreJ1;
        this.nombreJ2 = nombreJ2;
        this.botones = new JButton[tamano][tamano];

        crearInterfaz();
    }

    private void solicitarNuevaConfiguracion() {
        ConfiguracionDialog dialog = new ConfiguracionDialog(this);
        dialog.setVisible(true);

        if (dialog.isConfirmado()) {
            controlador.nuevoJuego(
                    dialog.getTamano(),
                    dialog.getSimbolo1(),
                    dialog.getSimbolo2(),
                    dialog.getNombreJ1(),
                    dialog.getNombreJ2()
            );
        }
    }
}