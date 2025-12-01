package tictactoe.controlador;

import tictactoe.modelo.Tictactoe;
import tictactoe.vista.ConfiguracionDialog;
import tictactoe.vista.GUI;

public class ControladorJuego {
    private Tictactoe modelo;
    private GUI vista;

    public ControladorJuego() {
        solicitarConfiguracionInicial();
    }

    private void solicitarConfiguracionInicial() {
        ConfiguracionDialog dialog = new ConfiguracionDialog(null);
        dialog.setVisible(true);

        if (dialog.isConfirmado()) {
            inicializarJuego(
                    dialog.getTamano(),
                    dialog.getSimbolo1(),
                    dialog.getSimbolo2(),
                    dialog.getNombreJ1(),
                    dialog.getNombreJ2()
            );
        } else {
            System.exit(0);
        }
    }

    private void inicializarJuego(int tamano, String simbolo1, String simbolo2, String nombreJ1, String nombreJ2) {
        this.modelo = new Tictactoe(tamano, String.valueOf(simbolo1), String.valueOf(simbolo2));
        this.vista = new GUI(this, tamano, String.valueOf(simbolo1), String.valueOf(simbolo2), nombreJ1, nombreJ2);
        actualizarVista();
    }

    public void manejarClick(int fila, int columna) {
        int indice = modelo.turno.toca();
        String simbolo = modelo.jugadores[indice].getSimbolo();

        boolean exito = modelo.tablero.ponerFicha(fila, columna, simbolo);

        if (exito) {
            vista.actualizarCelda(fila, columna, simbolo);

            if (modelo.tablero.terminado()) {
                finalizarJuego(indice);
            } else {
                modelo.turno.siguiente();
                actualizarVista();
            }
        }
    }

    public void reiniciarJuego() {
        int tamano = modelo.tablero.getFilas();
        String simbolo1 = modelo.jugadores[0].getSimbolo();
        String simbolo2 = modelo.jugadores[1].getSimbolo();

        modelo = new Tictactoe(tamano, simbolo1, simbolo2);
        vista.limpiarTablero();
        actualizarVista();
    }

    public void nuevoJuego(int tamano, String simbolo1, String simbolo2, String nombreJ1, String nombreJ2) {
        modelo = new Tictactoe(tamano, simbolo1, simbolo2);
        vista.reconfigurar(tamano, simbolo1, simbolo2, nombreJ1, nombreJ2);
        actualizarVista();
    }

    private void finalizarJuego(int indiceUltimoJugador) {
        String mensaje;

        if (modelo.tablero.hayGanador()) {
            String simboloGanador = modelo.jugadores[indiceUltimoJugador].getSimbolo();
            mensaje = "¡Ha ganado el jugador " + simboloGanador + "!";
        } else {
            mensaje = "¡Empate!";
        }

        vista.mostrarFinJuego(mensaje);
    }

    private void actualizarVista() {
        String simboloActual = modelo.getSimboloActual();
        vista.actualizarTurno(simboloActual);
    }

    public String getSimboloActual() {
        return modelo.getSimboloActual();
    }
}