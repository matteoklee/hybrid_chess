package de.kleemann.hybrid_chess.core.game;

import de.kleemann.hybrid_chess.core.game.pieces.Piece;

public class Position {

    private Piece piece;
    private final int x;
    private final int y;

    public Position(int x, int y, Piece piece) {
        this.x = x;
        this.y = y;
        this.piece = piece;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public boolean isOccupied() {
        return this.getPiece() != null;
    }

    public boolean isOccupiedAndOpponent(Piece piece) {
        return this.getPiece() != null && this.getPiece().getColor() != piece.getColor();
    }

}
