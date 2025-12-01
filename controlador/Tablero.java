package tictactoe.controlador;

public class Tablero {
    private int filas;
    private int columnas;
    private String[][] tablero;

    public Tablero(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.tablero = new String[filas][columnas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                tablero[i][j] = "";
            }
        }
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public String getCelda(int fila, int columna) {
        return tablero[fila][columna];
    }

    public void mostrar() {
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

    public boolean ponerFicha(int fila, int columna, String simbolo) {
        if (fila < 0 || fila >= filas || columna < 0 || columna >= columnas) {
            return false;
        }

        if (!tablero[fila][columna].isEmpty()) {
            return false;
        }

        tablero[fila][columna] = simbolo;
        return true;
    }

    public boolean terminado() {
        return hayGanador() || tableroLleno();
    }

    public boolean hayGanador() {
        int tamaño = filas;

        // Verificar filas
        for (int i = 0; i < filas; i++) {
            boolean ganador = true;
            if (tablero[i][0].isEmpty()) continue;

            for (int j = 1; j < columnas; j++) {
                if (!tablero[i][j].equals(tablero[i][0])) {
                    ganador = false;
                    break;
                }
            }
            if (ganador) return true;
        }

        // Verificar columnas
        for (int j = 0; j < columnas; j++) {
            boolean ganador = true;
            if (tablero[0][j].isEmpty()) continue;

            for (int i = 1; i < filas; i++) {
                if (!tablero[i][j].equals(tablero[0][j])) {
                    ganador = false;
                    break;
                }
            }
            if (ganador) return true;
        }

        // Verificar diagonal principal
        if (!tablero[0][0].isEmpty()) {
            boolean ganadorDiagPrincipal = true;
            for (int i = 1; i < tamaño; i++) {
                if (!tablero[i][i].equals(tablero[0][0])) {
                    ganadorDiagPrincipal = false;
                    break;
                }
            }
            if (ganadorDiagPrincipal) return true;
        }

        // Verificar diagonal secundaria
        if (!tablero[0][tamaño-1].isEmpty()) {
            boolean ganadorDiagSecundaria = true;
            for (int i = 1; i < tamaño; i++) {
                if (!tablero[i][tamaño-1-i].equals(tablero[0][tamaño-1])) {
                    ganadorDiagSecundaria = false;
                    break;
                }
            }
            if (ganadorDiagSecundaria) return true;
        }

        return false;
    }

    private boolean tableroLleno() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (tablero[i][j].isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }
}