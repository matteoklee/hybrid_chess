package de.kleemann.hybrid_chess.core.game.pieces;

import de.kleemann.hybrid_chess.core.game.ChessBoard;
import de.kleemann.hybrid_chess.core.game.Color;
import de.kleemann.hybrid_chess.core.game.Position;

import java.util.LinkedList;
import java.util.List;

public class King extends Piece {

    private boolean wasMoved;

    public King(Color color, int x, int y) {
        super(color, x, y);
        wasMoved = false;
    }

    @Override
    public boolean move(ChessBoard chessBoard, int y, int x) {
        boolean moved = super.move(chessBoard, y, x);

        if(moved) {
            wasMoved = true;
        }
        return moved;
    }

    @Override
    public List<Position> getLegalMoves(ChessBoard chessBoard) {
        LinkedList<Position> legalMoves = new LinkedList<>();
        Position[][] board = chessBoard.getBoard();

        int y = this.getY();
        int x = this.getX();

        for(int i = -1; i <= 1; i++) {
            for(int j = -1; j <= 1; j++) {
                if(y+i < 0 || y+i >= chessBoard.getRows() || x+j < 0 || x+j >= chessBoard.getColumns()) continue;
                if(i == 0 && j == 0) continue;
                if(board[y+i][x+j].isOccupied() && !board[y+i][x+j].isOpponent(this)) continue;

                legalMoves.add(board[y+i][x+j]);
            }
        }

        if(this.canCastleKingSide(chessBoard)) {
            if(this.getColor() == Color.WHITE) {
                legalMoves.add(board[7][6]);
            }
            else {
                legalMoves.add(board[0][6]);
            }
        }

        if(this.canCastleQueenSide(chessBoard)) {
            if(this.getColor() == Color.WHITE) {
                legalMoves.add(board[7][2]);
            }
            else {
                legalMoves.add(board[0][2]);
            }
        }

        return legalMoves;
    }

    /**
     * Checks all requirements whether the King can castle kingside
     *
     * @return
     */
    private boolean canCastleKingSide(ChessBoard chessBoard) {
        if(this.wasMoved()) return false;

        Position[][] board = chessBoard.getBoard();

        if(this.getColor() == Color.WHITE) {
            if(!board[7][7].isOccupied()) {
                return false;
            }

            if(!(board[7][7].getPiece() instanceof Rook)) {
                return false;
            }

            if(((Rook) board[7][7].getPiece()).wasMoved()) {
                return false;
            }

            if(board[7][5].isOccupied() || board[7][6].isOccupied()) {
                return false;
            }

            // TODO: Check whether King is currently in check

            // TODO: Check whether King does not pass through or finish on a square that is attacked by an enemy piece
            return true;
        }

        if(this.getColor() == Color.BLACK) {
            if(!board[0][7].isOccupied()) {
                return false;
            }

            if(!(board[0][7].getPiece() instanceof Rook)) {
                return false;
            }

            if(((Rook) board[0][7].getPiece()).wasMoved()) {
                return false;
            }

            if(board[0][5].isOccupied() || board[0][6].isOccupied()) {
                return false;
            }

            // TODO: Check whether King is currently in check

            // TODO: Check whether King does not pass through or finish on a square that is attacked by an enemy piece
            return true;
        }
        return false;
    }

    /**
     * Checks all requirements whether the King can castle queenside
     *
     * @return
     */
    private boolean canCastleQueenSide(ChessBoard chessBoard) {
        if(this.wasMoved()) return false;

        Position[][] board = chessBoard.getBoard();

        if(this.getColor() == Color.WHITE) {
            if(!board[7][0].isOccupied()) {
                return false;
            }

            if(!(board[7][0].getPiece() instanceof Rook)) {
                return false;
            }

            if(((Rook) board[7][0].getPiece()).wasMoved()) {
                return false;
            }

            if(board[7][3].isOccupied() || board[7][2].isOccupied() || board[7][1].isOccupied()) {
                return false;
            }

            // TODO: Check whether King is currently in check

            // TODO: Check whether King does not pass through or finish on a square that is attacked by an enemy piece
            return true;
        }

        if(this.getColor() == Color.BLACK) {
            if(!board[0][0].isOccupied()) {
                return false;
            }

            if(!(board[0][0].getPiece() instanceof Rook)) {
                return false;
            }

            if(((Rook) board[0][0].getPiece()).wasMoved()) {
                return false;
            }

            if(board[0][3].isOccupied() || board[0][2].isOccupied() || board[0][1].isOccupied()) {
                return false;
            }

            // TODO: Check whether King is currently in check

            // TODO: Check whether King does not pass through or finish on a square that is attacked by an enemy piece
            return true;
        }
        return false;
    }

    public boolean wasMoved() {
        return wasMoved;
    }
}
