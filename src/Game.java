import java.util.ArrayList;
import java.util.List;

class Game {
    private Board board;
    private List<Player> players;

    Game(Board board) {
        this.board = board;
        board.printBoard();

        Player player1 = new UserPlayer(Chip.X);
        Player player2 = new SmartPlayer(Chip.O);
        Player player3 = new DummyPlayer(Chip.O);

        players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
    }

    void play() {
        while (!board.isGameOver()) {
            for (Player i : players) {
                i.move(board);
                board.printBoard();
                if (board.isGameOver()){
                    System.out.println(i + " won!");
                }
            }
        }
    }

}