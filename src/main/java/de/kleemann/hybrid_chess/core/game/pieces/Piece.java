package de.kleemann.hybrid_chess.core.game.pieces;

import de.kleemann.hybrid_chess.core.game.ChessBoard;
import de.kleemann.hybrid_chess.core.game.Color;
import de.kleemann.hybrid_chess.core.game.Position;

import java.util.List;

public abstract class Piece {

    private final Color color;
    private Position position;

    public Piece(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public boolean move(ChessBoard chessBoard, Position newPosition) {
        List<Position> legalMoves = getLegalMoves(chessBoard);
        Position[][] board = chessBoard.getBoard();

        for(Position legal : legalMoves) {
            if(legal.getX() == newPosition.getX() && legal.getY() == newPosition.getY()) {
                this.setPosition(newPosition);
                board[newPosition.getY()][newPosition.getX()].setPiece(this);
                return true;
            }
        }
        return false;
    }

    public Position getPosition() {
        return position;
    }

    private void setPosition(Position position) {
        this.position = position;
    }

    public List<Position> getLegalMoves(ChessBoard chessBoard) {
        return null;
    }
}
