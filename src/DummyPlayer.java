import java.util.Random;

class DummyPlayer implements Player {

    private Random random = new Random();
    private Piece piece;

    DummyPlayer(Piece piece) {
        this.piece = piece;
    }

    @Override
    public void move(Board board) {
        int column = getRandomColumn(board.getCOLUMNS());
        if (board.availableMoves().contains(column)) {
            board.placePiece(column, piece);
        } else {
            move(board);
        }
    }

    private int getRandomColumn(int maxColumn) {
        return random.nextInt(maxColumn) + 1;
    }

    @Override
    public Piece getPiece() {
        return piece;
    }

    @Override
    public String toString() {
        return "DummyPlayer " + piece;
    }
}