package de.kleemann.hybrid_chess.core.game.pieces;

import de.kleemann.hybrid_chess.core.game.Color;
import de.kleemann.hybrid_chess.core.game.Position;

import java.util.List;

public class Queen extends Piece {

    public Queen(Color color, Position position) {
        super(color, position);
    }

    @Override
    public List<Position> getLegalMoves() {
        List<Position> legalMoves = super.getDiagonalMoves();
        legalMoves.addAll(super.getVerticalAndHorizontalMoves());
        return legalMoves;
    }
}
