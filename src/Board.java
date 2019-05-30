import java.io.Serializable;
import java.util.HashSet;

class Board implements Serializable {
    private final int ROWS = 6;
    private final int COLUMNS = 7;

    private Chip[][] board;

    private Chip playersTurn;
    private Chip winner;
    private HashSet<Integer> movesAvailable;

    private boolean gameOver;

    Board() {
        board = new Chip[ROWS][COLUMNS];
        movesAvailable = new HashSet<>();
        reset();
    }

    private void reset() {
        gameOver = false;
        playersTurn = Chip.X;
        winner = Chip.Blank;
        initialize();
    }

    private void initialize() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                board[i][j] = Chip.Blank;
            }
        }
        movesAvailable.clear();
        movesAvailable = availableMoves();
    }

    int getCOLUMNS() {
        return COLUMNS;
    }

    void printBoard() {
        printNumbers();
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (board[i][j] == Chip.Blank) {
                    System.out.print("* ");
                } else {
                    System.out.print(board[i][j].name() + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private void printNumbers() {
        for (int i = 1; i <= COLUMNS; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    void placePiece(int column) {
        for (int i = ROWS - 1; i >= 0; i--) {
            if (board[i][column - 1] == Chip.Blank) {
                board[i][column - 1] = playersTurn;
                break;
            }
        }
        movesAvailable.clear();
        movesAvailable = availableMoves();

        if (movesAvailable.isEmpty()) {
            gameOver = true;
        }
        check();
        playersTurn = (playersTurn == Chip.X) ? Chip.O : Chip.X;
    }

    Chip getTurn() {
        return playersTurn;
    }

    private boolean check() {
        return checkHorizontal() ||
                checkVertical() ||
                checkDiagonalUp() ||
                checkDiagonalDown();
    }

    private boolean checkHorizontal() {
        for (int i = 0; i < ROWS; i++) {
            int counter = 0;
            for (int j = 0; j < COLUMNS; j++) {
                if (board[i][j] == playersTurn) {
                    counter++;
                } else {
                    counter = 0;
                }
                if (counter == 4) {
                    winner = playersTurn;
                    gameOver = true;
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkVertical() {
        for (int j = 0; j < COLUMNS; j++) {
            int counter = 0;
            for (int i = 0; i < ROWS; i++) {
                if (board[i][j] == playersTurn) {
                    counter++;
                } else {
                    counter = 0;
                }
                if (counter == 4) {
                    winner = playersTurn;
                    gameOver = true;
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkDiagonalUp() {
        for (int i = 3; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS - 3; j++) {
                if (board[i][j] == playersTurn) {
                    if (checkDiagonalUpStep2(i, j)) {
                        winner = playersTurn;
                        gameOver = true;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean checkDiagonalDown() {
        for (int i = 0; i < ROWS - 3; i++) {
            for (int j = 0; j < COLUMNS - 3; j++) {
                if (board[i][j] == playersTurn)
                    if (checkDiagonalDownStep2(i, j)) {
                        winner = playersTurn;
                        gameOver = true;
                        return true;
                    }
            }
        }
        return false;
    }

    private boolean checkDiagonalUpStep2(int row, int column) {
        int j = column + 1;
        int i = row - 1;
        int counter = 0;
        for (int k = 0; k < 3; i--, j++, k++) {
            if (board[i][j] == playersTurn) {
                counter++;
            } else {
                counter = 0;
            }
        }
        return counter == 3;
    }

    private boolean checkDiagonalDownStep2(int row, int column) {
        int i = row + 1;
        int j = column + 1;
        int counter = 0;
        for (int k = 0; k < 3; i++, j++, k++) {
            if (board[i][j] == playersTurn) {
                counter++;
            } else {
                counter = 0;
            }
        }
        return counter == 3;
    }

    private boolean isColumnFull(int column) {
        return board[0][column] != Chip.Blank;
    }

    boolean isGameOver() {
        return gameOver;
    }

    Chip getWinner() {
        return winner;
    }

    HashSet<Integer> availableMoves() {
        HashSet<Integer> moves = new HashSet<>();
        for (int i = 0; i < COLUMNS; i++) {
            if (!isColumnFull(i)) {
                moves.add(i + 1);
            }
        }
        return moves;
    }
}