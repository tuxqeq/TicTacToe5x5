package Game;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TicTacToeController {

    private final TicTacToeConnector connector;
    private final TicTacToeMVC mvc;
    private TicTacToeView view;
    private int selectedRow = 0;
    private int selectedCol = 0;

    public TicTacToeController(TicTacToeMVC mvc, TicTacToeConnector connector, TicTacToeView view) {
        this.mvc = mvc;
        this.connector = connector;
        this.view = view;
        addListeners();
    }

    private void addListeners() {
        mvc.jTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!mvc.isKeyboardMode()) {
                    int row = mvc.jTable.rowAtPoint(e.getPoint());
                    int col = mvc.jTable.columnAtPoint(e.getPoint());
                    makeMove(row, col);
                }
            }
        });

        mvc.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (mvc.isKeyboardMode()) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_UP:
                            selectedRow = Math.max(0, selectedRow - 1);
                            break;
                        case KeyEvent.VK_DOWN:
                            selectedRow = Math.min(4, selectedRow + 1);
                            break;
                        case KeyEvent.VK_LEFT:
                            selectedCol = Math.max(0, selectedCol - 1);
                            break;
                        case KeyEvent.VK_RIGHT:
                            selectedCol = Math.min(4, selectedCol + 1);
                            break;
                        case KeyEvent.VK_ENTER:
                            makeMove(selectedRow, selectedCol);
                            break;
                    }
                    view.setSelected(selectedRow, selectedCol);
                    mvc.jTable.repaint(); // Update view with the new selection
                }
            }
        });
    }

    private void makeMove(int row, int col) {
        if (connector.makeMove(row, col)) {
            mvc.jTable.repaint();
            int winner = connector.checkWin();
            if (winner == 1) {
                JOptionPane.showMessageDialog(mvc, "Player X Wins!");
                resetGame();
            } else if (winner == -1) {
                JOptionPane.showMessageDialog(mvc, "Player O Wins!");
                resetGame();
            } else if (winner == 0) {
                JOptionPane.showMessageDialog(mvc, "It's a Draw!");
                resetGame();
            }
        }
    }

    public void resetGame() {
        connector.resetBoard();
        mvc.jTable.repaint();
        selectedRow = 0;
        selectedCol = 0;
    }
}