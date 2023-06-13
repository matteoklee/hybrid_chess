package de.kleemann.hybrid_chess.core.game.pieces;

import de.kleemann.hybrid_chess.core.game.ChessBoard;
import de.kleemann.hybrid_chess.core.game.utils.Color;
import de.kleemann.hybrid_chess.core.game.utils.Position;

import java.util.LinkedList;
import java.util.List;

public class Pawn extends Piece {

    private boolean wasMoved;
    private boolean enPassant;

    public Pawn(Color color, int x, int y) {
        super(color, x, y);
        wasMoved = false;
        enPassant = false;
    }

    @Override
    public boolean move(ChessBoard chessBoard, int y, int x) {
        int oldY = this.getY();
        boolean moved = super.move(chessBoard, y, x);

        if(moved) {
            wasMoved = true;

            if(this.getY() == 7 || this.getY() == 0) {
                promote(chessBoard);
            }

            if(Math.abs(this.getY() - oldY) == 2) { // Bauer hat sich 2 Felder bewegt
                Position[][] board = chessBoard.getBoard();

                if(this.getX()-1 >= 0 && board[this.getY()][this.getX()-1].isOccupiedAndOpponent(this)) {
                    if(board[this.getY()][this.getX()-1].getPiece() instanceof Pawn) {
                        ((Pawn) board[this.getY()][this.getX()-1].getPiece()).setEnPassant(true);
                    }
                }

                if(this.getX()+1 < chessBoard.getColumns() && board[this.getY()][this.getX()+1].isOccupiedAndOpponent(this)) {
                    if(board[this.getY()][this.getX()+1].getPiece() instanceof Pawn) {
                        ((Pawn) board[this.getY()][this.getX()+1].getPiece()).setEnPassant(true);
                    }
                }
            }
        }

        return moved;
    }

    /**
     * Pawn will automatically promote to a Queen
     *
     * @param chessBoard
     */
    private void promote(ChessBoard chessBoard) {
        Position[][] board = chessBoard.getBoard();

        board[this.getY()][this.getX()].removePiece();
        board[this.getY()][this.getX()].setPiece(new Queen(this.getColor(), this.getX(), this.getY()));
    }

    // Weiß unten; schwarz oben
    @Override
    public List<Position> getLegalMoves(ChessBoard chessBoard) {
        LinkedList<Position> legalMoves = new LinkedList<>();
        Position[][] board = chessBoard.getBoard();

        int y = this.getY();
        int x = this.getX();

        if(this.getColor() == Color.WHITE) {
            if(y-1 >= 0 && !board[y-1][x].isOccupied()) {
                legalMoves.add(board[y-1][x]); // Weißer Bauer ein Feld nach oben
            }

            if(!wasMoved) {
                if(!board[y-1][x].isOccupied() && !board[y-2][x].isOccupied()) {
                    legalMoves.add(board[y-2][x]); // Erster Zug 2 Felder nach oben
                }
            }

            if(y-1 >= 0 && x+1 < chessBoard.getColumns()) {
                if(board[y-1][x+1].isOccupiedAndOpponent(this)) {
                    legalMoves.add(board[y-1][x+1]); // Über Kreuz nach rechts schlagen
                }
            }

            if(y-1 >= 0 && x-1 >= 0) {
                if(board[y-1][x-1].isOccupiedAndOpponent(this)) {
                    legalMoves.add(board[y-1][x-1]); // Über Kreuz nach links schlagen
                }
            }
        }

        if(this.getColor() == Color.BLACK) {
            if(y+1 < chessBoard.getColumns() && !board[y+1][x].isOccupied()) {
                legalMoves.add(board[y+1][x]); // Schwarzer Bauer ein Feld nach unten
            }

            if(!wasMoved) {
                if(!board[y+1][x].isOccupied() && !board[y+2][x].isOccupied()) {
                    legalMoves.add(board[y+2][x]); // Erster Zug 2 Felder nach unten
                }
            }

            if(y+1 < chessBoard.getRows() && x+1 < chessBoard.getColumns()) {
                if(board[y+1][x+1].isOccupiedAndOpponent(this)) {
                    legalMoves.add(board[y+1][x+1]); // Über Kreuz nach rechts schlagen
                }
            }

            if(y+1 < chessBoard.getRows() && x-1 >= 0) {
                if(board[y+1][x-1].isOccupiedAndOpponent(this)) {
                    legalMoves.add(board[y+1][x-1]); // Über Kreuz nach links schlagen
                }
            }
        }

        return legalMoves;
    }

    public boolean checkAndExecuteEnPassant(ChessBoard chessBoard, Position newPosition) {
        if(!this.getEnPassant()) return false;

        if(Math.abs(this.getX() - newPosition.getX()) != 1 || Math.abs(this.getY() - newPosition.getY()) != 1) {
            return false;
        }

        Position[][] board = chessBoard.getBoard();

        if(this.getColor() == Color.WHITE) {
            if(newPosition.getY() + 1 < chessBoard.getRows() && board[newPosition.getY() + 1][newPosition.getX()].isOccupiedAndOpponent(this)) { // Hinter dem weißen Bauer ist ein Gegner
                if(board[newPosition.getY() + 1][newPosition.getX()].getPiece() instanceof Pawn) { // Gegner ist ein Bauer
                    board[newPosition.getY() + 1][newPosition.getX()].removePiece(); // Gegner schlagen

                    board[this.getY()][this.getX()].removePiece();
                    this.setX(newPosition.getX());
                    this.setY(newPosition.getY());
                    board[newPosition.getY()][newPosition.getX()].setPiece(this);

                    return true;
                }
            }
        }

        if(this.getColor() == Color.BLACK) {
            if(newPosition.getY() - 1 >= 0 && board[newPosition.getY() - 1][newPosition.getX()].isOccupiedAndOpponent(this)) { // Hinter dem schwarzen Bauer ist ein Gegner
                if(board[newPosition.getY() - 1][newPosition.getX()].getPiece() instanceof Pawn) { // Gegner ist ein Bauer
                    board[newPosition.getY() - 1][newPosition.getX()].removePiece(); // Gegner schlagen

                    board[this.getY()][this.getX()].removePiece();
                    this.setX(newPosition.getX());
                    this.setY(newPosition.getY());
                    board[newPosition.getY()][newPosition.getX()].setPiece(this);

                    return true;
                }
            }
        }
        return false;
    }

    public boolean getEnPassant() {
        return enPassant;
    }

    public void setEnPassant(boolean enPassant) {
        this.enPassant = enPassant;
    }
}
