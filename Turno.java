package tictactoe;

public class Turno {
    private int turnoActual = 0;

    public int toca() {
        return turnoActual;
    }

    public void siguiente() {
        turnoActual = (turnoActual + 1) % 2;
    }

}