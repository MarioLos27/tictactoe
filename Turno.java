package tictactoe;

public class Turno {
    private int turnoActual = 0;

    public int toca() {
        // Devuelve el índice del jugador actual (0 o 1)
        return turnoActual;
    }

    public void siguiente() {
        // Alterna entre 0 y 1
        turnoActual = (turnoActual + 1) % 2;
    }

    public int anterior() {
        return (turnoActual == 0) ? 1 : 0;
    }
}