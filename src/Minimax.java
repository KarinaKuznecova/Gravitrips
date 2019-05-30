class Minimax {

    int minimax(Chip player, Board board, int depth, int alpha, int beta) {
        if (board.isGameOver() || depth++ > 6) {
            return score(player, depth, board);
        }

        if (board.getTurn() == player) {
            return getMax(player, board, depth, alpha, beta);
        } else {
            return getMin(player, board, depth, alpha, beta);
        }

    }

    private int getMax(Chip player, Board board, int depth, int alpha, int beta) {
        int bestMove = 0;

        for (Integer i : board.availableMoves()) {
            Board possibleBoard = (Board) DeepCopy.deepCopy(board);
            possibleBoard.placePiece(i);
            int score = minimax(player, possibleBoard, depth, alpha, beta);

            if (score > alpha) {
                alpha = score;
                bestMove = i;
            }
            if (alpha >= beta) {
                break;
            }

        }
        if (bestMove != 0) {
            board.placePiece(bestMove);
        }
        return alpha;
    }

    private int getMin(Chip player, Board board, int depth, int alpha, int beta) {
        int bestMove = 0;

        for (Integer i : board.availableMoves()) {
            Board possibleBoard = (Board) DeepCopy.deepCopy(board);
            possibleBoard.placePiece(i);

            int score = minimax(player, possibleBoard, depth, alpha, beta);

            if (score < beta) {
                beta = score;
                bestMove = i;
            }
            if (alpha >= beta) {
                break;
            }
        }
        if (bestMove != 0) {
            board.placePiece(bestMove);
        }
        return beta;
    }

    private int score(Chip player, int depth, Board newBoard) {
        Chip opponent = (player == Chip.X) ? Chip.O : Chip.X;
        if (newBoard.isGameOver() && newBoard.getWinner() == player) {
            return (100 - depth);
        } else if (newBoard.isGameOver() && newBoard.getWinner() == opponent) {
            return (-100 + depth);
        }
        return 0;
    }
}