package tictactoe;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    Tictactoe tictactoe;
    JButton[][] botones;
    JLabel turno = new JLabel("Turno actual: ");
    JPanel panelTablero;
    JPanel panelInfo;
    JPanel panelControles;

    int tamano;
    char simbolo1, simbolo2;

    public GUI(){
        setTitle("TicTacToe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(600,600);

        pedirConfiguracion();
        crearInterfaz();
        actualizarTurno();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void pedirConfiguracion(){
        try {
            tamano = 0;
            while(tamano < 3) {
                String input = JOptionPane.showInputDialog(null, "Introduce el tamaño del tablero (mínimo 3):");
                if (input == null) { // Usuario canceló
                    System.exit(0);
                }
                this.tamano = Integer.parseInt(input);
                if (tamano < 3) {
                    JOptionPane.showMessageDialog(null, "El tamaño debe ser al menos 3");
                }
            }

            String Simbolo1 = JOptionPane.showInputDialog(null, "Símbolo del Jugador 1:");
            if (Simbolo1 == null || Simbolo1.isEmpty()) {
                this.simbolo1 = 'X'; // Por defecto
            } else {
                this.simbolo1 = Simbolo1.charAt(0);
            }

            do {
                String Simbolo2 = JOptionPane.showInputDialog(null, "Símbolo del Jugador 2:");
                if (Simbolo2 == null || Simbolo2.isEmpty()) {
                    this.simbolo2 = 'O'; // Por defecto
                } else {
                    this.simbolo2 = Simbolo2.charAt(0);
                }

                if (this.simbolo1 == this.simbolo2) {
                    JOptionPane.showMessageDialog(null, "Los símbolos deben ser diferentes");
                }
            } while (this.simbolo1 == this.simbolo2);

            this.tictactoe = new Tictactoe(tamano, simbolo1, simbolo2);
            this.botones = new JButton[tamano][tamano];

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: Entrada inválida");
            pedirConfiguracion();
        }
    }

    public void crearInterfaz(){
        getContentPane().removeAll();

        panelInfo = new JPanel();
        panelInfo.add(turno);
        this.add(panelInfo, BorderLayout.NORTH);

        panelTablero = new JPanel(new GridLayout(tamano, tamano, 5, 5));

        for (int i = 0; i < tamano; i++) {
            for (int j = 0; j < tamano; j++) {
                final int fila = i;
                final int columna = j;

                botones[i][j] = new JButton();
                botones[i][j].setFont(new Font("Arial", Font.BOLD, 40));
                botones[i][j].setPreferredSize(new Dimension(80, 80));

                botones[i][j].addActionListener(e -> manejarClick(fila, columna));

                panelTablero.add(botones[i][j]);
            }
        }

        this.add(panelTablero, BorderLayout.CENTER);

        panelControles = new JPanel();
        JButton reiniciarBtn = new JButton("Reiniciar");
        reiniciarBtn.addActionListener(e -> reiniciarJuego());

        JButton nuevoJuegoBtn = new JButton("Nuevo juego");
        nuevoJuegoBtn.addActionListener(e -> nuevoJuego());

        panelControles.add(reiniciarBtn);
        panelControles.add(nuevoJuegoBtn);
        this.add(panelControles, BorderLayout.SOUTH);

        revalidate();
        repaint();
    }

    public void manejarClick(int fila, int columna){
        int indice = tictactoe.turno.toca();
        char simbolo = tictactoe.jugadores[indice].getSimbolo();

        boolean exito = tictactoe.tablero.ponerFicha(fila, columna, simbolo);

        if (exito){
            botones[fila][columna].setText(String.valueOf(simbolo));
            botones[fila][columna].setEnabled(false);

            if (tictactoe.tablero.terminado()){
                mostrarFin(indice);
            } else {
                tictactoe.turno.siguiente();
                actualizarTurno();
            }
        }
    }

    public void reiniciarJuego(){
        tictactoe = new Tictactoe(tamano, simbolo1, simbolo2);

        for (int i = 0; i < tamano; i++) {
            for (int j = 0; j < tamano; j++) {
                botones[i][j].setText("");
                botones[i][j].setEnabled(true);
            }
        }

        actualizarTurno();
    }

    public void nuevoJuego(){
        pedirConfiguracion();
        crearInterfaz();
        actualizarTurno();
    }

    public void mostrarFin(int indiceUltimoJugador){
        String mensaje;

        if (tictactoe.tablero.hayGanador()){
            char simboloGanador = tictactoe.jugadores[indiceUltimoJugador].getSimbolo();
            mensaje = "¡Ha ganado el jugador " + simboloGanador + "!";
        } else {
            mensaje = "¡Empate!";
        }

        JOptionPane.showMessageDialog(null, mensaje);

        // Preguntar si quiere jugar de nuevo
        int opcion = JOptionPane.showConfirmDialog(
                null,
                "¿Quieres jugar de nuevo?",
                "Fin del juego",
                JOptionPane.YES_NO_OPTION
        );

        if (opcion == JOptionPane.YES_OPTION) {
            reiniciarJuego();
        }
    }

    public void actualizarTurno(){
        char simbolo = tictactoe.getSimboloActual();
        turno.setText("Turno del jugador: " + simbolo);
    }
}