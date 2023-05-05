package de.kleemann.hybrid_chess.core.game.pieces;

import de.kleemann.hybrid_chess.core.game.ChessBoard;
import de.kleemann.hybrid_chess.core.game.Color;
import de.kleemann.hybrid_chess.core.game.Position;

import java.util.List;

public class King extends Piece {

    public King(Color color, Position position) {
        super(color, position);
    }

    @Override
    public List<Position> getLegalMoves(ChessBoard chessBoard) {
        return null;
    }
}
