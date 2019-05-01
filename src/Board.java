import java.util.ArrayList;
import java.util.List;

class Board {
    private final int ROWS = 6;
    private final int COLUMNS = 7;

    private Piece[][] board;

    Board() {
        board = new Piece[ROWS][COLUMNS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                board[i][j] = new Piece();
            }
        }
    }

    int getCOLUMNS() {
        return COLUMNS;
    }

    void printBoard() {
        printNumbers();
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                System.out.print(board[i][j] + " ");
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

    void placePiece(int column, Piece piece) {
        for (int i = ROWS - 1; i >= 0; i--) {
            if (board[i][column - 1].isEmpty()) {
                board[i][column - 1] = piece;
                break;
            }
        }
    }

    boolean check(Piece piece) {
        return checkHorizontal(piece) || checkVertical(piece) || checkDiagonalUp(piece) || checkDiagonalDown(piece);
    }

    private boolean checkHorizontal(Piece piece) {
        int counter = 0;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (board[i][j].equals(piece)) {
                    counter++;
                } else {
                    counter = 0;
                }
                if (counter == 4) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkVertical(Piece piece) {
        int counter = 0;
        for (int j = 0; j < COLUMNS; j++) {
            for (int i = 0; i < ROWS; i++) {
                if (board[i][j].equals(piece)) {
                    counter++;
                } else {
                    counter = 0;
                }
                if (counter == 4) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkDiagonalUp(Piece piece) {
        for (int i = 3; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS - 3; j++) {
                if (board[i][j].equals(piece))
                    if (checkDiagonalUpStep2(i, j, piece)) {
                        return true;
                    }
            }
        }
        return false;
    }

    private boolean checkDiagonalDown(Piece piece) {
        for (int i = 0; i < ROWS - 3; i++) {
            for (int j = 0; j < COLUMNS - 3; j++) {
                if (board[i][j].equals(piece))
                    if (checkDiagonalDownStep2(i, j, piece)) {
                        return true;
                    }
            }
        }
        return false;
    }

    private boolean checkDiagonalUpStep2(int row, int column, Piece piece) {
        int j = column + 1;
        int i = row - 1;
        int counter = 0;
        for (int k = 0; k < 3; i--, j++, k++) {
            if (board[i][j].equals(piece)) {
                counter++;
            } else {
                counter = 0;
            }
        }
        return counter == 3;
    }

    private boolean checkDiagonalDownStep2(int row, int column, Piece piece) {
        int i = row + 1;
        int j = column + 1;
        int counter = 0;
        for (int k = 0; k < 3; i++, j++, k++) {
            if (board[i][j].equals(piece)) {
                counter++;
            } else {
                counter = 0;
            }
        }
        return counter == 3;
    }

    boolean isColumnFull(int column) {
        return !board[0][column].isEmpty();
    }

    List<Integer> availableMoves() {
        List<Integer> moves = new ArrayList<>();
        for (int i = 0; i < COLUMNS; i++) {
            if (!isColumnFull(i)) {
                moves.add(i+1);
            }
        }
        return moves;
    }
}