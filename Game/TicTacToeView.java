package Game;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

class TicTacToeView extends JPanel implements TableCellRenderer {
    public int cellValue;

    public TicTacToeView() {
        setOpaque(false);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        this.cellValue = (int) value;
        return this;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (cellValue == 1) {
            drawX(g);
        } else if (cellValue == -1) {
            drawO(g);
        }
    }

    private void drawX(Graphics g) {
        g.setColor(Color.RED);
        g.drawLine(10, 10, getWidth() - 10, getHeight() - 10);
        g.drawLine(10, getHeight() - 10, getWidth() - 10, 10);
    }

    private void drawO(Graphics g) {
        g.setColor(Color.BLUE);
        g.drawOval(10, 10, getWidth() - 20, getHeight() - 20);
    }
}
