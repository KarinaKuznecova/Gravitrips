class UserPlayer implements Player {
    private Reader reader = new Reader();
    private Piece piece;

    UserPlayer(Piece piece) {
        this.piece = piece;
    }

    public void move(Board board) {
        int column = reader.getUserInput("Please select a column for " + piece);
        if (board.availableMoves().contains(column)) {
            board.placePiece(column, piece);
        } else {
            System.out.println("Wrong column");
            move(board);
        }
    }

    public Piece getPiece() {
        return piece;
    }

    @Override
    public String toString() {
        return "UserPlayer " + piece;
    }
}