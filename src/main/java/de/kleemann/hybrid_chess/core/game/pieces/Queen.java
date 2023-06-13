package de.kleemann.hybrid_chess.core.game.pieces;

import de.kleemann.hybrid_chess.core.game.ChessBoard;
import de.kleemann.hybrid_chess.core.game.utils.Color;
import de.kleemann.hybrid_chess.core.game.utils.Position;

import java.util.List;

public class Queen extends Piece {

    public Queen(Color color, int x, int y) {
        super(color, x, y);
    }

    @Override
    public List<Position> getLegalMoves(ChessBoard chessBoard) {
        List<Position> legalMoves = super.getDiagonalMoves(chessBoard);
        legalMoves.addAll(super.getVerticalAndHorizontalMoves(chessBoard));
        return legalMoves;
    }
}
