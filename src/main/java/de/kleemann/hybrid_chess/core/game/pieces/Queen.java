package de.kleemann.hybrid_chess.core.game.pieces;

import de.kleemann.hybrid_chess.core.game.Color;
import de.kleemann.hybrid_chess.core.game.Position;

import java.util.List;

public class Queen extends Piece {

    public Queen(Color color, int x, int y) {
        super(color, x, y);
    }

    @Override
    public List<Position> getLegalMoves() {
        List<Position> legalMoves = super.getDiagonalMoves();
        legalMoves.addAll(super.getVerticalAndHorizontalMoves());
        return legalMoves;
    }
}
