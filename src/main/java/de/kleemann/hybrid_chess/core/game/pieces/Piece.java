package de.kleemann.hybrid_chess.core.game.pieces;

import de.kleemann.hybrid_chess.core.game.ChessBoard;
import de.kleemann.hybrid_chess.core.game.Color;
import de.kleemann.hybrid_chess.core.game.Position;

import java.util.List;

public abstract class Piece {

    private final Color color;
    private Position position;

    public Piece(Color color, Position position) {
        this.color = color;
        this.position = position;
    }

    public Color getColor() {
        return color;
    }

    public boolean move(ChessBoard chessBoard, int y, int x) {
        List<Position> legalMoves = getLegalMoves(chessBoard);
        Position[][] board = chessBoard.getBoard();
        Position newPosition = board[y][x];

        for(Position legal : legalMoves) {
            if(legal.getX() == newPosition.getX() && legal.getY() == newPosition.getY()) {
                board[this.getPosition().getY()][this.getPosition().getX()].removePiece();

                this.setPosition(newPosition);
                this.getPosition().setPiece(this);
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

    public abstract List<Position> getLegalMoves(ChessBoard chessBoard);
}
