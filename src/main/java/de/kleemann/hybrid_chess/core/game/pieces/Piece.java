package de.kleemann.hybrid_chess.core.game.pieces;

import de.kleemann.hybrid_chess.core.game.ChessBoard;
import de.kleemann.hybrid_chess.core.game.Color;
import de.kleemann.hybrid_chess.core.game.Position;

import java.util.LinkedList;
import java.util.List;

public abstract class Piece {

    private final Color color;
    private Position position;

    public Piece(Color color, Position position) {
        this.color = color;
        this.position = position;
        this.position.setPiece(this);
    }

    public Color getColor() {
        return color;
    }

    public boolean move(ChessBoard chessBoard, int y, int x) {
        List<Position> legalMoves = getLegalMoves(chessBoard);
        Position[][] board = chessBoard.getBoard();
        Position newPosition = board[y][x];

        for(Position legal : legalMoves) {
            if(legal == newPosition) {
                board[this.getPosition().getY()][this.getPosition().getX()].removePiece();

                this.setPosition(newPosition);
                this.getPosition().setPiece(this);
                return true;
            }
        }
        return false;
    }

    public List<Position> getDiagonalMoves(ChessBoard chessBoard) {
        LinkedList<Position> legalMoves = new LinkedList<>();
        Position[][] board = chessBoard.getBoard();

        int y = this.getPosition().getY();
        int x = this.getPosition().getX();

        // Nord-West-Richtung
        for(int i = -1; y+i >= 0 && x+i >= 0; i--) {
            if(board[y+i][x+i].isOccupied()) {
                if(board[y+i][x+i].isOpponent(this)) {
                    legalMoves.add(board[y+i][x+i]);
                    break;
                }
                break;
            }
            legalMoves.add(board[y+i][x+i]);
        }

        // Nord-Ost-Richtung
        for(int i = -1, j = 1; y+i >= 0 && x+j < chessBoard.getColumns(); i--, j++) {
            if(board[y+i][x+j].isOccupied()) {
                if(board[y+i][x+j].isOpponent(this)) {
                    legalMoves.add(board[y+i][x+j]);
                    break;
                }
                break;
            }
            legalMoves.add(board[y+i][x+j]);
        }

        // Süd-West-Richtung
        for(int i = 1, j = -1; y+i < chessBoard.getRows() && x+j >= 0; i++, j--) {
            if(board[y+i][x+j].isOccupied()) {
                if(board[y+i][x+j].isOpponent(this)) {
                    legalMoves.add(board[y+i][x+j]);
                    break;
                }
                break;
            }
            legalMoves.add(board[y+i][x+j]);
        }

        // Süd-Ost-Richtung
        for(int i = 1; y+i < chessBoard.getRows() && x+i < chessBoard.getColumns(); i++) {
            if(board[y+i][x+i].isOccupied()) {
                if(board[y+i][x+i].isOpponent(this)) {
                    legalMoves.add(board[y+i][x+i]);
                    break;
                }
                break;
            }
            legalMoves.add(board[y+i][x+i]);
        }

        return legalMoves;
    }

    public Position getPosition() {
        return position;
    }

    private void setPosition(Position position) {
        this.position = position;
    }

    public abstract List<Position> getLegalMoves(ChessBoard chessBoard);
}
