package Game;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TicTacToeConnector {

    static {
        System.loadLibrary("TicTacToeLogic");
    }

    public native int getCellValue(int row, int col);
    public native boolean makeMove(int row, int col, int player);
    public native int checkWin();
    public native void resetBoard();
}