package de.kleemann.hybrid_chess.core.game.pieces;

import de.kleemann.hybrid_chess.core.game.ChessBoard;
import de.kleemann.hybrid_chess.core.game.utils.Color;
import de.kleemann.hybrid_chess.core.game.utils.Position;

import java.util.LinkedList;
import java.util.List;

public abstract class Piece {

    private final Color color;
    private int x;
    private int y;

    public Piece(Color color, int x, int y) {
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public Color getColor() {
        return color;
    }

    public boolean move(ChessBoard chessBoard, int y, int x) {
        Position[][] board = chessBoard.getBoard();
        Position newPosition = board[y][x];

        if(newPosition.isKing()) return false;

        // En Passant
        boolean enPassant = false;
        if(this instanceof Pawn) {
            enPassant = ((Pawn) this).checkAndExecuteEnPassant(chessBoard, newPosition);
        }

        chessBoard.unsetAllPawnsEnPassant();

        if(enPassant) return true;

        List<Position> legalMoves = getLegalMoves(chessBoard);

        for(Position legal : legalMoves) {
            if(legal == newPosition) {
                board[this.y][this.x].removePiece();

                this.x = newPosition.getX();
                this.y = newPosition.getY();
                board[y][x].setPiece(this);
                //this.getPosition().setPiece(this);
                chessBoard.setBoard(board);
                return true;
            }
        }
        return false;
    }

    public List<Position> getDiagonalMoves(ChessBoard chessBoard) {
        LinkedList<Position> legalMoves = new LinkedList<>();
        Position[][] board = chessBoard.getBoard();

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

    public List<Position> getVerticalAndHorizontalMoves(ChessBoard chessBoard) {
        LinkedList<Position> legalMoves = new LinkedList<>();
        Position[][] board = chessBoard.getBoard();

        // oben
        for(int i = -1; y+i >= 0; i--) {
           if(board[y+i][x].isOccupied()) {
               if(board[y+i][x].isOpponent(this)) {
                   legalMoves.add(board[y+i][x]);
                   break;
               }
               break;
           }
            legalMoves.add(board[y+i][x]);
        }

        // unten
        for(int i = 1; y+i < chessBoard.getRows(); i++) {
            if(board[y+i][x].isOccupied()) {
                if(board[y+i][x].isOpponent(this)) {
                    legalMoves.add(board[y+i][x]);
                    break;
                }
                break;
            }
            legalMoves.add(board[y+i][x]);
        }

        // links
        for(int i = -1; x+i >= 0; i--) {
            if(board[y][x+i].isOccupied()) {
                if(board[y][x+i].isOpponent(this)) {
                    legalMoves.add(board[y][x+i]);
                    break;
                }
                break;
            }
            legalMoves.add(board[y][x+i]);
        }

        // rechts
        for(int i = 1; x+i < chessBoard.getColumns(); i++) {
            if(board[y][x+i].isOccupied()) {
                if(board[y][x+i].isOpponent(this)) {
                    legalMoves.add(board[y][x+i]);
                    break;
                }
                break;
            }
            legalMoves.add(board[y][x+i]);
        }

        return legalMoves;
    }

    public abstract List<Position> getLegalMoves(ChessBoard chessBoard);

    public String getPieceName() {
        return getClass().getSimpleName().substring(0, 2);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
