package de.kleemann.hybrid_chess.core.game.pieces;

import de.kleemann.hybrid_chess.core.game.Color;
import de.kleemann.hybrid_chess.core.game.Position;

public abstract class Piece {

    private final Color color;

    public Piece(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public boolean move(Position newPosition) {
        return false;
    }

}
