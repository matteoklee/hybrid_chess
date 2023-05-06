package de.kleemann.hybrid_chess.core.game.pieces;

import de.kleemann.hybrid_chess.core.game.ChessBoard;
import de.kleemann.hybrid_chess.core.game.Color;
import de.kleemann.hybrid_chess.core.game.Position;

import java.util.LinkedList;
import java.util.List;

public class Knight extends Piece {

    public Knight(Color color, Position position) {
        super(color, position);
    }

    @Override
    public List<Position> getLegalMoves(ChessBoard chessBoard) {
        LinkedList<Position> legalMoves = new LinkedList<>();
        Position[][] board = chessBoard.getBoard();

        int[] a = {1, 2, 2, 1, -1, -2, -2, -1};
        int[] o = {2, 1, -1, -2, -2, -1, 1, 2};

        int y = this.getPosition().getY();
        int x = this.getPosition().getX();

        int num = 0;
        while(num < 8) {
            int u = x+o[num], v = y+a[num];

            if(u < 0 || u >= chessBoard.getColumns() || v < 0 || v >= chessBoard.getRows()) {
                num++;
                continue;
            }

            if(board[v][u].isOccupied() && !board[v][u].isOpponent(this)) {
                num++;
                continue;
            }

            legalMoves.add(board[v][u]);
            num++;
        }

        return legalMoves;
    }
}
