package de.kleemann.hybrid_chess.core.game;

import de.kleemann.hybrid_chess.core.game.pieces.Piece;

public class Position {

    private Piece piece;
    private final int x;
    private final int y;

    /**
     * note: first parameter is y (row) NOT x (column)!
     *
     * @param y
     * @param x
     */
    public Position(int y, int x) {
        this.x = x;
        this.y = y;
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

    public void removePiece() {
        this.piece = null;
    }

    public boolean isOccupied() {
        return this.getPiece() != null;
    }

    public boolean isOccupiedAndOpponent(Piece piece) {
        return this.getPiece() != null && this.getPiece().getColor() != piece.getColor();
    }

    public boolean isOpponent(Piece piece) {
        return this.getPiece().getColor() != piece.getColor();
    }

}
