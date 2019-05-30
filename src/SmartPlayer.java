class SmartPlayer implements Player {

    private Chip piece;

    SmartPlayer(Chip piece) {
        this.piece = piece;
    }

    @Override
    public void move(Board board) {
        Minimax minimax = new Minimax();
        minimax.minimax(board.getTurn(), board, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    @Override
    public Chip getPiece() {
        return piece;
    }

    @Override
    public String toString() {
        return "SmartPlayer{" +
                "piece=" + piece +
                '}';
    }
}