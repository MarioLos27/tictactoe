package tictactoe;

public class Tictactoe {
    
    private Tablero tablero;
    private Jugador[] jugadores;
    private Turno turno = new Turno();

    public Tictactoe(int tamano, char simbolo1, char simbolo2){
        this.tablero = new Tablero(tamano, tamano);
        this.jugadores = new Jugador[]{
                new Jugador(simbolo1),
                new Jugador(simbolo2)
        };
    }

    public void Jugar() {
        do {
            tablero.mostrar();
            jugadores[turno.toca()].ponerFicha(tablero);
            turno.siguiente();
        } while (!tablero.terminado());

        tablero.mostrar();
        ganador();
        System.out.println("Juego terminado.");
    }

    public void ganador(){
        if(tablero.hayGanador()){
            System.out.println("¡Ha ganado el jugador " +
                    jugadores[turno.anterior()].getSimbolo() + "!");
        } else {
            System.out.println("¡Empate!");
        }
    }
}