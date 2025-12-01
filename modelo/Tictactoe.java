package tictactoe.modelo;

import tictactoe.controlador.ControladorJuego;
import tictactoe.controlador.Tablero;
import tictactoe.vista.GUI;

import javax.swing.*;

public class Tictactoe {

    public Tablero tablero;
    public Jugador[] jugadores;
    public Turno turno;

    public Tictactoe(int tamano, String simbolo1, String simbolo2) {
        this.tablero = new Tablero(tamano, tamano);
        this.jugadores = new Jugador[]{
                new Jugador(simbolo1),
                new Jugador(simbolo2)
        };
        this.turno = new Turno();
    }

    public Tictactoe() {
        this(3, "X", "O");
    }

    public String getSimboloActual() {
        return jugadores[turno.toca()].getSimbolo();
    }

    public static class Main {
        public static void main(String[] args) {
            SwingUtilities.invokeLater(ControladorJuego::new);
        }
    }
}