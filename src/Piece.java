import java.util.Objects;

class Piece {
    private char symbol;

    Piece() {
        symbol = '*';
    }

    Piece(char symbol) {
        this.symbol = symbol;
    }

    boolean isEmpty() {
        return symbol == '*';
    }

    @Override
    public String toString() {
        return "" + symbol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return symbol == piece.symbol;
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol);
    }
}