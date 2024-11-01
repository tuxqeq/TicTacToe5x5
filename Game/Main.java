package Game;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        TicTacToeConnector connector = new TicTacToeConnector();
        TicTacToeData data = new TicTacToeData(connector);
        TicTacToeView view = new TicTacToeView();
        TicTacToeMVC mvc = new TicTacToeMVC(data, view);

        TicTacToeController controller = new TicTacToeController(mvc, connector, view);

        JFrame frame = new JFrame("5x5 Tic-Tac-Toe");
        frame.add(mvc);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}