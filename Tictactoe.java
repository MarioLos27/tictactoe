package tictactoe;

public class Tictactoe {

    public Tablero tablero;
    public Jugador[] jugadores;
    public Turno turno;

    public Tictactoe(int tamano, char simbolo1, char simbolo2) {
        this.tablero = new Tablero(tamano, tamano);
        this.jugadores = new Jugador[]{
                new Jugador(simbolo1),
                new Jugador(simbolo2)
        };
        this.turno = new Turno();
    }

    public Tictactoe() {
        this(3, 'X', 'O');
    }

    public char getSimboloActual() {
        return jugadores[turno.toca()].getSimbolo();
    }
}