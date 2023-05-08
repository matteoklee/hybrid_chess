package de.kleemann.hybrid_chess.core.game.pieces;

import de.kleemann.hybrid_chess.core.game.ChessBoard;
import de.kleemann.hybrid_chess.core.game.Color;
import de.kleemann.hybrid_chess.core.game.Position;

import java.util.LinkedList;
import java.util.List;

public class King extends Piece {

    public King(Color color, Position position) {
        super(color, position);
    }

    @Override
    public List<Position> getLegalMoves() {
        LinkedList<Position> legalMoves = new LinkedList<>();
        ChessBoard chessBoard = this.getPosition().getChessBoard();
        Position[][] board = chessBoard.getBoard();

        int y = this.getPosition().getY();
        int x = this.getPosition().getX();

        for(int i = -1; i <= 1; i++) {
            for(int j = -1; j <= 1; j++) {
                if(y+i < 0 || y+i >= chessBoard.getRows() || x+j < 0 || x+j >= chessBoard.getColumns()) continue;
                if(i == 0 && j == 0) continue;
                if(board[y+i][x+j].isOccupied() && !board[y+i][x+j].isOpponent(this)) continue;

                legalMoves.add(board[y+i][x+j]);
            }
        }

        return legalMoves;
    }
}
