import java.util.ArrayList;
import java.util.List;

class Game {
    private Board board;
    private List<Player> players;
    private boolean gameOver;

    Game() {
        board = new Board();
        board.printBoard();

        Player player1 = new UserPlayer(new Piece('X'));
        Player player2 = new UserPlayer(new Piece('O'));

        players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
    }

    void play() {
        while (!gameOver) {
            for (Player i : players) {
                i.move(board);
                board.printBoard();
                if (playerWon(i)) {
                    gameOver = true;
                    System.out.println(i + " won!");
                    break;
                }
            }
        }
    }

    boolean playerWon(Player player) {
        return board.check(player.getPiece());
    }

}