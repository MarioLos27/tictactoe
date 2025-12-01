package tictactoe.modelo;

import tictactoe.controlador.Tablero;

import java.util.Scanner;

public class Jugador {
    private String simbolo;
    private static Scanner scanner = new Scanner(System.in);

    public Jugador(String simbolo) {
        this.simbolo = simbolo;
    }

    public void ponerFicha(Tablero tablero) {
        boolean fichaColocada = false;

        while (!fichaColocada) {
            try {
                System.out.println("Turno del jugador " + simbolo);
                System.out.print("Introduce fila: ");
                int fila = scanner.nextInt();
                System.out.print("Introduce columna: ");
                int columna = scanner.nextInt();

                fichaColocada = tablero.ponerFicha(fila, columna, simbolo);

                if (!fichaColocada) {
                    System.out.println("Movimiento inválido. Intenta de nuevo.");
                }
            } catch (Exception e) {
                System.out.println("Entrada inválida. Intenta de nuevo.");
                scanner.nextLine();
            }
        }
    }

    public String getSimbolo() {
        return simbolo;
    }
}