package de.kleemann.hybrid_chess.core.game.pieces;

import de.kleemann.hybrid_chess.core.game.ChessBoard;
import de.kleemann.hybrid_chess.core.game.Color;
import de.kleemann.hybrid_chess.core.game.Position;

import java.util.List;

public class Rook extends Piece {

    public Rook(Color color, int x, int y) {
        super(color, x, y);
    }

    @Override
    public List<Position> getLegalMoves(ChessBoard chessBoard) {
        return super.getVerticalAndHorizontalMoves(chessBoard);
    }
}
