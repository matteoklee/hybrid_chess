package de.kleemann.hybrid_chess.core.game.pieces;

import de.kleemann.hybrid_chess.core.game.ChessBoard;
import de.kleemann.hybrid_chess.core.game.Color;
import de.kleemann.hybrid_chess.core.game.Position;

import java.util.LinkedList;
import java.util.List;

public class Pawn extends Piece {

    private boolean wasMoved;

    public Pawn(Color color, Position position) {
        super(color, position);
        wasMoved = false;
    }

    @Override
    public boolean move(ChessBoard chessBoard, int y, int x) {
        boolean moved = super.move(chessBoard, y, x);
        if(moved) wasMoved = true;

        return moved;
    }

    // Weiß unten; schwarz oben
    @Override
    public List<Position> getLegalMoves(ChessBoard chessBoard) {
        LinkedList<Position> legalMoves = new LinkedList<>();
        Position[][] board = chessBoard.getBoard();

        int y = this.getPosition().getY();
        int x = this.getPosition().getX();

        if(this.getColor() == Color.WHITE) {
            if(y-1 >= 0 && !board[y-1][x].isOccupied()) {
                legalMoves.add(board[y-1][x]); // Weißer Bauer ein Feld nach oben
            }

            if(!wasMoved) {
                if(!board[y-2][x].isOccupied()) {
                    legalMoves.add(board[y-2][x]); // Erster Zug 2 Felder nach oben
                }
            }

            if(y-1 >= 0 && x+1 < chessBoard.getColumns()) {
                if(board[y-1][x+1].isOccupiedAndOpponent(this)) {
                    legalMoves.add(board[y-1][x+1]); // Über Kreuz nach rechts schlagen
                }
            }

            if(y-1 >= 0 && x-1 < chessBoard.getColumns()) {
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
                if(!board[y+2][x].isOccupied()) {
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
}
