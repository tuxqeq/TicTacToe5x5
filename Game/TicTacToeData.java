package Game;

import javax.swing.table.AbstractTableModel;

public class TicTacToeData extends AbstractTableModel {

    private final TicTacToeConnector connector;

    public TicTacToeData(TicTacToeConnector connector) {
        this.connector = connector;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return Integer.class;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return connector.getCellValue(rowIndex, columnIndex);
    }

    @Override
    public int getRowCount() {
        return 5;
    }

    @Override
    public int getColumnCount() {
        return 5;
    }
}