package Game;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

class TicTacToeView extends JPanel implements TableCellRenderer {
    public int cellValue;
    public boolean selectedCell;
    int selectedRow = -1;
    int selectedCol = -1;

    public TicTacToeView() {
        setOpaque(false);
    }

    public void setSelected(int row, int column) {
        selectedRow = row;
        selectedCol = column;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        this.cellValue = (int) value;
        if(selectedRow == row && selectedCol == column) {
            selectedCell = true;
        }else{
            selectedCell = false;
        }
        return this;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (selectedCell) {
            g.setColor(new Color(30, 150, 150, 100));
            g.fillRect(0, 0, getWidth(), getHeight());
        }
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
