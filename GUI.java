package tictactoe;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GUI extends JFrame {
    Tictactoe tictactoe;

    JButton[][] botones;

    JLabel turno = new JLabel("Turno actual: ");
    JPanel panelTablero = new JPanel();
    JPanel panelInfo = new JPanel();
    JPanel panelControles = new JPanel();

    int tamano;
    char simbolo1, simbolo2;

    public GUI(){
        setTitle("TicTacToe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(20,20);
    }

    public void pedirConfiguracion(){
        try {
            while(tamano < 3) {
                tamano = Integer.parseInt(JOptionPane.showInputDialog(null, "Introduce el tamaño del tablero: "));
            }
            simbolo1 = JOptionPane.showInputDialog(null, "Introduce el simbolo 1: ").charAt(0);
            simbolo2 = JOptionPane.showInputDialog(null, "Introduce el simbolo 2: ").charAt(0);

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error: Error entrada invalida");
            pedirConfiguracion();
        }

    }
}
