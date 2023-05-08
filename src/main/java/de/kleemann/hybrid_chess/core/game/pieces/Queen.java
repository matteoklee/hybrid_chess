package de.kleemann.hybrid_chess.core.game.pieces;

import de.kleemann.hybrid_chess.core.game.ChessBoard;
import de.kleemann.hybrid_chess.core.game.Color;
import de.kleemann.hybrid_chess.core.game.Position;

import java.util.LinkedList;
import java.util.List;

public class Queen extends Piece {

    public Queen(Color color, Position position) {
        super(color, position);
    }

    @Override
    public List<Position> getLegalMoves(ChessBoard chessBoard) {
        List<Position> legalMoves = super.getDiagonalMoves(chessBoard);
        legalMoves.addAll(super.getVerticalAndHorizontalMoves(chessBoard));
        return legalMoves;
    }
}
