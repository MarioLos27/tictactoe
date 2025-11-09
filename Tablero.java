package tictactoe;

public class Tablero {
    private int filas;
    private int columnas;
    private char[][] tablero;

    public Tablero(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.tablero = new char[filas][columnas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                tablero[i][j] = ' ';
            }
        }
    }

    public void mostrar() {
        // Mostrar números de columna
        System.out.print("\n  ");
        for (int j = 0; j < columnas; j++) {
            System.out.print(j);
            if (j < columnas - 1) System.out.print("   ");
        }
        System.out.println();

        for (int i = 0; i < filas; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < columnas; j++) {
                System.out.print(" " + tablero[i][j] + " ");
                if (j < columnas - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();

            if (i < filas - 1) {
                System.out.print("  ");
                for (int j = 0; j < columnas; j++) {
                    System.out.print("---");
                    if (j < columnas - 1) System.out.print("+");
                }
                System.out.println();
            }
        }
        System.out.println();
    }

    public boolean ponerFicha(int fila, int columna, char simbolo) {
        if (fila < 0 || fila >= filas || columna < 0 || columna >= columnas) {
            return false;
        }
        
        if (tablero[fila][columna] != ' ') {
            return false;
        }

        tablero[fila][columna] = simbolo;
        return true;
    }

    public boolean terminado() {
        return hayGanador() || tableroLleno();
    }

    public boolean hayGanador() {
        int tamaño = filas; // Asumiendo tablero cuadrado

        // Verificar filas
        for (int i = 0; i < filas; i++) {
            boolean ganador = true;
            for (int j = 1; j < columnas; j++) {
                if (tablero[i][0] == ' ' || tablero[i][j] != tablero[i][0]) {
                    ganador = false;
                    break;
                }
            }
            if (ganador) return true;
        }

        // Verificar columnas
        for (int j = 0; j < columnas; j++) {
            boolean ganador = true;
            for (int i = 1; i < filas; i++) {
                if (tablero[0][j] == ' ' || tablero[i][j] != tablero[0][j]) {
                    ganador = false;
                    break;
                }
            }
            if (ganador) return true;
        }

        // Verificar diagonal principal
        boolean ganadorDiagPrincipal = true;
        for (int i = 1; i < tamaño; i++) {
            if (tablero[0][0] == ' ' || tablero[i][i] != tablero[0][0]) {
                ganadorDiagPrincipal = false;
                break;
            }
        }
        if (ganadorDiagPrincipal) return true;

        // Verificar diagonal secundaria
        boolean ganadorDiagSecundaria = true;
        for (int i = 1; i < tamaño; i++) {
            if (tablero[0][tamaño-1] == ' ' ||
                    tablero[i][tamaño-1-i] != tablero[0][tamaño-1]) {
                ganadorDiagSecundaria = false;
                break;
            }
        }

        return ganadorDiagSecundaria;
    }

    private boolean tableroLleno() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (tablero[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

}