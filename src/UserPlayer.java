class UserPlayer implements Player {
    private Reader reader = new Reader();
    private Chip piece;

    UserPlayer(Chip piece) {
        this.piece = piece;
    }

    public void move(Board board) {
        int column = reader.getUserInput("Please select a column for " + piece);
        if (board.availableMoves().contains(column)) {
            board.placePiece(column);
        } else {
            System.out.println("Wrong column");
            move(board);
        }
    }

    public Chip getPiece() {
        return piece;
    }

    @Override
    public String toString() {
        return "UserPlayer " + piece;
    }
}