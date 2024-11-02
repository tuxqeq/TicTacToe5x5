package Game;

public class TicTacToeConnector {

    static {
        System.loadLibrary("TicTacToeLogic");
    }

    public native int getCellValue(int row, int col);
    public native boolean makeMove(int row, int col);
    public native int checkWin();
    public native void resetBoard();
}