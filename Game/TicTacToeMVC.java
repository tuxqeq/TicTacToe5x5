package Game;

import javax.swing.*;
import java.awt.*;

public class TicTacToeMVC extends JPanel {
    protected JTable jTable;
    private final TicTacToeData data;

    public TicTacToeMVC(TicTacToeData data) {
        this.data = data;

        setLayout(new BorderLayout());
        setOpaque(false);

        jTable = new JTable();
        jTable.setOpaque(false);
        jTable.setModel(data);
        jTable.setDefaultRenderer(Integer.class, new TicTacToeView());
        jTable.setRowMargin(0);
        jTable.setRowHeight(60);
        jTable.setPreferredSize(new Dimension(300, 300));
        add(jTable);
        jTable.setGridColor(new Color(0, 0, 0, 255));
        setPreferredSize(new Dimension(300, 300));
        setFocusable(false);
    }
}
