package Game;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

        TicTacToeConnector connector = new TicTacToeConnector();
        TicTacToeData data = new TicTacToeData(connector);
        TicTacToeView view = new TicTacToeView();
        TicTacToeMVC mvc = new TicTacToeMVC(data, view);
        JButton button = new JButton("Reset");

        TicTacToeController controller = new TicTacToeController(mvc, connector, view);

        button.addActionListener(e -> {
            controller.resetGame();
        });

        JFrame frame = new JFrame("5x5 Tic-Tac-Toe");
        frame.add(mvc);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(600, 650);
        frame.add(button, BorderLayout.SOUTH);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}