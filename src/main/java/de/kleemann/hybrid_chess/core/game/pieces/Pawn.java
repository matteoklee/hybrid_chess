package de.kleemann.hybrid_chess.core.game.pieces;

import de.kleemann.hybrid_chess.core.game.ChessBoard;
import de.kleemann.hybrid_chess.core.game.Color;
import de.kleemann.hybrid_chess.core.game.Position;

import java.util.LinkedList;
import java.util.List;

public class Pawn extends Piece {

    private boolean wasMoved = false;

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public boolean move(Position newPosition) {
        wasMoved = true;
        // TODO
        return false;
    }

    public List<Position> getLegalMoves(ChessBoard chessBoard) {
        LinkedList<Position> legalMoves = new LinkedList<>();
        Position[][] board = chessBoard.getBoard();
        // TODO
        return legalMoves;
    }
}
