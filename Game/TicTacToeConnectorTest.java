package Game;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TicTacToeConnectorTest {

    static {
        System.loadLibrary("TicTacToeLogic");
    }

    private TicTacToeConnector connector;

    @BeforeEach
    public void setUp() {
        connector = new TicTacToeConnector();
        connector.resetBoard();
    }

    // Test the initial state of the board
    @Test
    public void testBoardInitialization() {
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                assertEquals(0, connector.getCellValue(row, col), "Cell should be empty initially.");
            }
        }
    }

    // Horizontal wins for Player 1
    @Test
    public void testHorizontalWinPlayer1() {
        for (int col = 0; col < 4; col++) {
            connector.makeMove(0, col); // Player 1
            connector.makeMove(1, col); // Player 2 (blocking)
        }
        connector.makeMove(0, 3); // Player 1 should win with four in a row
        assertEquals(1, connector.checkWin(), "Player 1 should win horizontally in row 0.");
    }

    // Horizontal wins for Player 2
    @Test
    public void testHorizontalWinPlayer2() {
        connector.makeMove(4, 4); // Player 1
        for (int col = 0; col < 3; col++) {
            connector.makeMove(1, col); // Player 2
            connector.makeMove(0, col); // Player 1
        }
        connector.makeMove(1, 3); // Player 2 should win with four in a row
        assertEquals(-1, connector.checkWin(), "Player 2 should win horizontally in row 1.");
    }

    // Vertical wins for Player 1
    @Test
    public void testVerticalWinPlayer1() {
        for (int row = 0; row < 4; row++) {
            connector.makeMove(row, 0); // Player 1
            connector.makeMove(row, 1); // Player 2 (blocking)
        }
        connector.makeMove(4, 0); // Player 1 should win with four in a row
        assertEquals(1, connector.checkWin(), "Player 1 should win vertically in column 0.");
    }

    // Vertical wins for Player 2
    @Test
    public void testVerticalWinPlayer2() {
        connector.makeMove(4, 4); // Player 1
        for (int row = 0; row < 4; row++) {
            connector.makeMove(row, 0); // Player 2
            connector.makeMove(row, 1); // Player 1
        }
        //connector.makeMove(1, 0); // Player 2 should win with four in a row
        assertEquals(-1, connector.checkWin(), "Player 2 should win vertically in column 1.");
    }

    // Diagonal wins for Player 1 (top-left to bottom-right)
    @Test
    public void testDiagonal1WinPlayer1() {
        connector.makeMove(0, 0); // Player 1
        connector.makeMove(0, 1); // Player 2
        connector.makeMove(1, 1); // Player 1
        connector.makeMove(1, 0); // Player 2
        connector.makeMove(2, 2); // Player 1
        connector.makeMove(0, 2); // Player 2
        connector.makeMove(3, 3); // Player 1 should win with four in a row
        assertEquals(1, connector.checkWin(), "Player 1 should win diagonally from top-left to bottom-right.");
    }

    @Test
    public void testDiagonal2WinPlayer1() {
        connector.makeMove(0, 1); // Player 1
        connector.makeMove(0, 0); // Player 2
        connector.makeMove(1, 2); // Player 1
        connector.makeMove(1, 1); // Player 2
        connector.makeMove(2, 3); // Player 1
        connector.makeMove(2, 2); // Player 2
        connector.makeMove(3, 4); // Player 1 should win with four in a row
        assertEquals(1, connector.checkWin(), "Player 1 should win diagonally from top-left to bottom-right.");
    }

    @Test
    public void testDiagonal3WinPlayer1() {
        connector.makeMove(1, 0); // Player 1
        connector.makeMove(0, 0); // Player 2
        connector.makeMove(2, 1); // Player 1
        connector.makeMove(1, 1); // Player 2
        connector.makeMove(3, 2); // Player 1
        connector.makeMove(2, 2); // Player 2
        connector.makeMove(4, 3); // Player 1 should win with four in a row
        assertEquals(1, connector.checkWin(), "Player 1 should win diagonally from top-left to bottom-right.");
    }

    // Diagonal wins for Player 2 (top-left to bottom-right)
    @Test
    public void testDiagonal1WinPlayer2() {
        connector.makeMove(0, 1); // Player 1
        connector.makeMove(0, 0); // Player 2
        connector.makeMove(1, 0); // Player 1
        connector.makeMove(1, 1); // Player 2
        connector.makeMove(2, 0); // Player 1
        connector.makeMove(2, 2); // Player 2
        connector.makeMove(0, 2); // Player 2
        connector.makeMove(3, 3); // Player 2 should win with four in a row
        assertEquals(-1, connector.checkWin(), "Player 2 should win diagonally from top-left to bottom-right.");
    }

    @Test
    public void testDiagonal2WinPlayer2() {
        connector.makeMove(0, 4); // Player 1
        connector.makeMove(0, 1); // Player 2
        connector.makeMove(0, 0); // Player 1
        connector.makeMove(1, 2); // Player 2
        connector.makeMove(1, 1); // Player 1
        connector.makeMove(2, 3); // Player 2
        connector.makeMove(2, 2); // Player 1
        connector.makeMove(3, 4); // Player 2 should win with four in a row
        assertEquals(-1, connector.checkWin(), "Player 1 should win diagonally from top-left to bottom-right.");
    }

    @Test
    public void testDiagonal3WinPlayer2() {
        connector.makeMove(0, 4); // Player 1
        connector.makeMove(1, 0); // Player 2
        connector.makeMove(0, 0); // Player 1
        connector.makeMove(2, 1); // Player 2
        connector.makeMove(1, 1); // Player 1
        connector.makeMove(3, 2); // Player 2
        connector.makeMove(2, 2); // Player 1
        connector.makeMove(4, 3); // Player 2 should win with four in a row
        assertEquals(-1, connector.checkWin(), "Player 1 should win diagonally from top-left to bottom-right.");
    }

    // Anti-diagonal wins for Player 1 (top-right to bottom-left)
    @Test
    public void testAntiDiagonal1WinPlayer1() { //rewrite no winner
        connector.makeMove(0, 4); // Player 1
        connector.makeMove(1, 1); // Player 2
        connector.makeMove(1, 3); // Player 1
        connector.makeMove(4, 4); // Player 2
        connector.makeMove(2, 2); // Player 1
        connector.makeMove(3, 4); // Player 2
        connector.makeMove(3, 1); // Player 1 should win with four in a row
        assertEquals(1, connector.checkWin(), "Player 1 should win anti-diagonally from top-right to bottom-left.");
    }

    @Test
    public void testAntiDiagonal2WinPlayer1() { //rewrite no winner
        connector.makeMove(0, 3); // Player 1
        connector.makeMove(0, 4); // Player 2
        connector.makeMove(1, 2); // Player 1
        connector.makeMove(1, 3); // Player 2
        connector.makeMove(2, 1); // Player 1
        connector.makeMove(2, 2); // Player 2
        connector.makeMove(3, 0); // Player 1 should win with four in a row
        assertEquals(1, connector.checkWin(), "Player 1 should win anti-diagonally from top-right to bottom-left.");
    }
    @Test
    public void testAntiDiagonal3WinPlayer1() { //rewrite no winner
        connector.makeMove(1, 4); // Player 1
        connector.makeMove(0, 4); // Player 2
        connector.makeMove(2, 3); // Player 1
        connector.makeMove(1, 3); // Player 2
        connector.makeMove(3, 2); // Player 1
        connector.makeMove(2, 2); // Player 2
        connector.makeMove(4, 1); // Player 1 should win with four in a row
        assertEquals(1, connector.checkWin(), "Player 1 should win anti-diagonally from top-right to bottom-left.");
    }

    // Anti-diagonal wins for Player 2 (top-right to bottom-left)
    @Test
    public void testAntiDiagonal1WinPlayer2() { //rewrite no winner
        connector.makeMove(4, 4); // Player 1
        connector.makeMove(0, 4); // Player 2
        connector.makeMove(3, 4); // Player 1
        connector.makeMove(1, 3); // Player 2
        connector.makeMove(2, 3); // Player 1
        connector.makeMove(2, 2); // Player 2
        connector.makeMove(3, 3); // Player 1
        connector.makeMove(3, 1); // Player 2 should win with four in a row
        assertEquals(-1, connector.checkWin(), "Player 2 should win anti-diagonally from top-right to bottom-left.");
    }

    @Test
    public void testAntiDiagonal2WinPlayer2() {
        connector.makeMove(4, 4); // Player 1
        connector.makeMove(0, 3); // Player 2
        connector.makeMove(0, 4); // Player 1
        connector.makeMove(1, 2); // Player 2
        connector.makeMove(1, 3); // Player 1
        connector.makeMove(2, 1); // Player 2
        connector.makeMove(2, 2); // Player 1
        connector.makeMove(3, 0); // Player 2 should win with four in a row
        assertEquals(-1, connector.checkWin(), "Player 1 should win anti-diagonally from top-right to bottom-left.");
    }
    @Test
    public void testAntiDiagonal3WinPlayer2() {
        connector.makeMove(4, 4); // Player 1
        connector.makeMove(1, 4); // Player 2
        connector.makeMove(0, 4); // Player 1
        connector.makeMove(2, 3); // Player 2
        connector.makeMove(1, 3); // Player 1
        connector.makeMove(3, 2); // Player 2
        connector.makeMove(2, 2); // Player 1
        connector.makeMove(4, 1); // Player 2 should win with four in a row
        assertEquals(-1, connector.checkWin(), "Player 1 should win anti-diagonally from top-right to bottom-left.");
    }
    // Test for a draw condition
    @Test
    public void testDrawCondition() {
        // Fill the board to force a draw without any win condition
        int[][] moves  = {
                {0, 0}, {0, 1}, {0, 2}, {0, 3}, {0, 4},
                {1, 0}, {1, 1}, {1, 2}, {1, 3}, {1, 4},
                {2, 0}, {2, 2}, {2, 3}, {2, 1}, {2, 4},
                {3, 1}, {3, 0}, {3, 3}, {3, 2}, {3, 4},
                {4, 0}, {4, 1}, {4, 2}, {4, 3}, {4, 4},
        };
        for (int[] move : moves) {
            connector.makeMove(move[0], move[1]);
        }
        assertEquals(0, connector.checkWin(), "There should be no winner - game should be a draw.");
    }

    // Test reset board functionality
    @Test
    public void testResetBoard() {
        connector.makeMove(0, 0); // Player 1
        connector.resetBoard(); // Reset the board
        assertEquals(0, connector.getCellValue(0, 0), "Cell (0, 0) should be empty after reset.");
        assertEquals(-2, connector.checkWin(), "Game is still going, so the output is -2");
    }

    // Test making a move in an already occupied cell
    @Test
    public void testMakeMoveInOccupiedCell() {
        connector.makeMove(0, 0); // Player 1
        assertFalse(connector.makeMove(0, 0), "Should not be able to make a move in an occupied cell.");
    }

    // Test making moves alternately
    @Test
    public void testAlternatingMoves() {
        connector.makeMove(0, 0); // Player 1
        connector.makeMove(0, 1); // Player 2
        connector.makeMove(1, 0); // Player 1
        connector.makeMove(1, 1); // Player 2
        assertEquals(1, connector.getCellValue(0, 0), "Cell (0, 0) should be occupied by Player 1.");
        assertEquals(-1, connector.getCellValue(0, 1), "Cell (0, 1) should be occupied by Player 2.");
    }

}