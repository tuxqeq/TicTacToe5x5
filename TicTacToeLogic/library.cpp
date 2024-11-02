#include <jni.h>
#include "Game_TicTacToeConnector.h"

const int SIZE = 5;
int board[SIZE][SIZE] = {0};// 0 = empty, 1 = X, -1 = O
static int player = 1;

// Check if there are four in a row horizontally
static inline int checkRows() {
    for (int i = 0; i < SIZE; i++) {
        for (int j = 0; j <= SIZE - 4; j++) {
            if (board[i][j] != 0 && board[i][j] == board[i][j+1] &&
                board[i][j+1] == board[i][j+2] && board[i][j+2] == board[i][j+3]) {
                return board[i][j];
            }
        }
    }
    return 0; // No winner in rows
}

// Check if there are four in a row vertically
static inline int checkColumns() {
    for (int j = 0; j < SIZE; j++) {
        for (int i = 0; i <= SIZE - 4; i++) {
            if (board[i][j] != 0 && board[i][j] == board[i+1][j] &&
                board[i+1][j] == board[i+2][j] && board[i+2][j] == board[i+3][j]) {
                return board[i][j];
            }
        }
    }
    return 0; // No winner in columns
}

// Check if there are four in a row in diagonal (top-left to bottom-right)
static inline int checkDiagonalsTLBR() {
    for (int i = 0; i <= SIZE - 4; i++) {
        for (int j = 0; j <= SIZE - 4; j++) {
            if (board[i][j] != 0 && board[i][j] == board[i+1][j+1] &&
                board[i+1][j+1] == board[i+2][j+2] && board[i+2][j+2] == board[i+3][j+3]) {
                return board[i][j];
            }
        }
    }
    return 0; // No winner in TL-BR diagonal
}

// Check if there are four in a row in diagonal (top-right to bottom-left)
static inline int checkDiagonalsTRBL() {
    for (int i = 0; i <= SIZE - 4; i++) {
        for (int j = 3; j < SIZE; j++) {
            if (board[i][j] != 0 && board[i][j] == board[i+1][j-1] &&
                board[i+1][j-1] == board[i+2][j-2] && board[i+2][j-2] == board[i+3][j-3]) {
                return board[i][j];
            }
        }
    }
    return 0; // No winner in TR-BL diagonal
}

// Check for a draw
static inline bool isDraw() {
    for (int i = 0; i < SIZE; i++) {
        for (int j = 0; j < SIZE; j++) {
            if (board[i][j] == 0) return false;
        }
    }
    return true;
}

// Consolidated function to check if there’s a win or draw
static inline int checkWinCondition() {
    int winner = checkRows();
    if (winner != 0) return winner;

    winner = checkColumns();
    if (winner != 0) return winner;

    winner = checkDiagonalsTLBR();
    if (winner != 0) return winner;

    winner = checkDiagonalsTRBL();
    if (winner != 0) return winner;

    return isDraw() ? 0 : -2; // Return 0 for draw, -2 for ongoing game
}

// JNI method to get cell value
JNIEXPORT jint JNICALL Java_Game_TicTacToeConnector_getCellValue(JNIEnv *env, jobject obj, jint row, jint col) {
    return board[row][col];
}

// JNI method to make a move
JNIEXPORT jboolean JNICALL Java_Game_TicTacToeConnector_makeMove(JNIEnv *env, jobject obj, jint row, jint col) {
    if (board[row][col] == 0) { // Ensure cell is empty
        board[row][col] = player;
        player = -player;
        return JNI_TRUE;
    }
    return JNI_FALSE;
}

// JNI method to check for win or draw
JNIEXPORT jint JNICALL Java_Game_TicTacToeConnector_checkWin(JNIEnv *env, jobject obj) {
    return checkWinCondition();
}

// JNI method to reset the board
JNIEXPORT void JNICALL Java_Game_TicTacToeConnector_resetBoard(JNIEnv *env, jobject obj) {
    for (int i = 0; i < SIZE; i++) {
        for (int j = 0; j < SIZE; j++) {
            board[i][j] = 0;
        }
    }
    player = 1;
}

