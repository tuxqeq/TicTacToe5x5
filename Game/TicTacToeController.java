package Game;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TicTacToeController {

    private final TicTacToeConnector connector;
    private final TicTacToeMVC view;
    private int currentPlayer = 1;

    public TicTacToeController(TicTacToeMVC view, TicTacToeConnector connector) {
        this.view = view;
        this.connector = connector;
        addListeners();
    }

    private void addListeners() {
        view.jTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = view.jTable.rowAtPoint(e.getPoint());
                int col = view.jTable.columnAtPoint(e.getPoint());

                if (connector.makeMove(row, col, currentPlayer)) {
                    view.jTable.repaint();
                    int winner = connector.checkWin();
                    if (winner == 1) {
                        JOptionPane.showMessageDialog(view, "Player X Wins!");
                        resetGame();
                    } else if (winner == -1) {
                        JOptionPane.showMessageDialog(view, "Player O Wins!");
                        resetGame();
                    } else if (winner == 0) {
                        JOptionPane.showMessageDialog(view, "It's a Draw!");
                        resetGame();
                    } else {
                        currentPlayer *= -1;
                    }
                }
            }
        });
    }

    private void resetGame() {
        connector.resetBoard();
        view.jTable.repaint();
        currentPlayer = 1;
    }
}
