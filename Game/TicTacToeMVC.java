package Game;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TicTacToeMVC extends JPanel {
    public JTable jTable;
    private final TicTacToeData data;
    public boolean keyboardMode = false;

    public TicTacToeMVC(TicTacToeData data, TicTacToeView view) {
        this.data = data;

        setLayout(new BorderLayout());
        setOpaque(false);

        jTable = new JTable();
        jTable.setOpaque(false);
        jTable.setModel(data);
        jTable.setDefaultRenderer(Integer.class, view);
        jTable.setRowMargin(0);
        jTable.setRowHeight(120);
        add(jTable);
        jTable.setFocusable(false);
        jTable.setGridColor(new Color(0, 0, 0, 255));
        setPreferredSize(new Dimension(600, 600));
        setFocusable(true);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    keyboardMode = !keyboardMode; // Toggle keyboard mode on spacebar press
                    if (keyboardMode) {
                        System.out.println("Keyboard mode activated.");
                    } else {
                        view.setSelected(-1, -1);
                        keyboardMode = false;
                        jTable.repaint();
                        System.out.println("Keyboard mode deactivated.");
                    }
                }
            }
        });
    }

    public boolean isKeyboardMode() {
        return keyboardMode;
    }
}
