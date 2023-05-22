package de.kleemann.hybrid_chess.core.game.pieces;

import de.kleemann.hybrid_chess.core.game.ChessBoard;
import de.kleemann.hybrid_chess.core.game.Color;
import de.kleemann.hybrid_chess.core.game.Position;

import java.util.List;

public class Rook extends Piece {

    private boolean wasMoved;

    public Rook(Color color, int x, int y) {
        super(color, x, y);
        wasMoved = false;
    }

    @Override
    public boolean move(ChessBoard chessBoard, int y, int x) {
        boolean moved = super.move(chessBoard, y, x);

        if(moved) {
            wasMoved = true;
        }
        return moved;
    }

    @Override
    public List<Position> getLegalMoves(ChessBoard chessBoard) {
        return super.getVerticalAndHorizontalMoves(chessBoard);
    }

    public boolean wasMoved() {
        return wasMoved;
    }
}
