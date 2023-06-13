package de.kleemann.hybrid_chess.core.game;

import de.kleemann.hybrid_chess.core.game.pieces.*;
import de.kleemann.hybrid_chess.core.game.utils.Color;
import de.kleemann.hybrid_chess.core.game.utils.Position;

import java.util.ArrayList;
import java.util.List;

public class ChessBoard {

    private final int rows;
    private final int columns;

    private Position[][] board;
    private ArrayList<Piece> whitePieces;
    private ArrayList<Piece> blackPieces;

    public static final int DEFAULT_ROWS = 8;
    public static final int DEFAULT_COLUMNS = 8;

    public ChessBoard() {
        this(DEFAULT_ROWS, DEFAULT_COLUMNS);
    }

    public ChessBoard(int rows, int columns) {
        if(rows <= 0 ||  columns <= 0) throw new IllegalArgumentException("rows and columns must be greater than 0.");
        this.rows = rows;
        this.columns = columns;
        this.board = new Position[rows][columns];

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                switch (i) {
                    case 0 -> initialSetup(this.board, Color.BLACK, i, j);
                    case 1 -> this.board[i][j] = new Position(i, j, new Pawn(Color.BLACK, j, i));
                    case 6 -> this.board[i][j] = new Position(i, j, new Pawn(Color.WHITE, j, i));
                    case 7 -> initialSetup(this.board, Color.WHITE, i, j);
                    default -> this.board[i][j] = new Position(i, j, null);
                }
            }
        }
        initializeLists();
    }

    private void initialSetup(Position[][] board, Color color, int i, int j) {
        switch (j) {
            case 0, 7 -> board[i][j] = new Position(i, j, new Rook(color, j, i));
            case 1, 6 -> board[i][j] = new Position(i, j, new Knight(color, j, i));
            case 2, 5 -> board[i][j] = new Position(i, j, new Bishop(color, j, i));
            case 3 -> board[i][j] = new Position(i, j, new Queen(color, j, i));
            case 4 -> board[i][j] = new Position(i, j, new King(color, j, i));
            default -> {
            }
        }
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public boolean isValid(Position position) {
        int row = position.getX();
        if(row < 0 || row >= rows) {
            return false;
        }
        int column = position.getY();
        if(column < 0 || column >= columns) {
            return false;
        }

        return true;
    }

    public Position[][] getBoard() {
        return board;
    }

    public String printChessBoard() {
        String result = "\n";

        for(int row = 0; row < rows; row++) {
            result += "\n";
            result += "------------------------------------------\n";
            for(int column = 0; column < columns; column++) {
                result += "| " + board[row][column].getPieceName() + " ";
            }
            result += "|";
        }
        result += "\n";
        result += "------------------------------------------\n";
        return  result;
    }


    public void setBoard(Position[][] board) {
        this.board = board;
    }

    public void unsetAllPawnsEnPassant() {
        Position[][] board = this.getBoard();

        for(Position[] positions : board) {
            for(Position position : positions) {
                if(position.getPiece() instanceof Pawn) {
                    ((Pawn) position.getPiece()).setEnPassant(false);
                }
            }
        }
    }

    // ========== Check Detection ==========

    public void checkForCheckmateOrStalemate(Color color) {
        if(getAllLegalPositions(color).size() > 0) {
            return;
        }

        if(isKingInCheck(getKing(color))) {
            System.err.println("CHECKMATE");
            return;
        }
        System.err.println("STALEMATE");
    }

    public boolean isKingInCheck(Color color) {
        return isKingInCheck(getKing(color));
    }

    public boolean isKingInCheck(King king) {
        Position kingPosition = board[king.getY()][king.getX()];

        if(king.getColor() == Color.WHITE) {
            return arePositionsThreatened(blackPieces, kingPosition);
        }

        if(king.getColor() == Color.BLACK) {
            return arePositionsThreatened(whitePieces, kingPosition);
        }
        return false;
    }

    public boolean isPositionThreatened(Color color, Position ... positions) {
        if(color == Color.WHITE) {
            return arePositionsThreatened(blackPieces, positions);
        }

        if(color == Color.BLACK) {
            return arePositionsThreatened(whitePieces, positions);
        }
        return false;
    }

    private boolean arePositionsThreatened(ArrayList<Piece> pieces, Position ... positions) {
        for(Piece piece : pieces) {
            if(piece instanceof King) continue; // Endlosschleife verhindern

            List<Position> legalMoves = piece.getLegalMoves(this);

            for(Position legal : legalMoves) {
                for(Position position : positions) {
                    if(legal == position) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public ArrayList<Position> getAllLegalPositions(Color color) {
        ArrayList<Position> allLegalMoves = new ArrayList<>();

        if(color == Color.WHITE) {
            for(Piece piece : whitePieces) {
                if(piece instanceof King) {
                    if(!((King) piece).wasMoved()) {
                        continue;
                    }
                }
                List<Position> legalMoves = piece.getLegalMoves(this);
                allLegalMoves.addAll(legalMoves.stream().filter(position -> piece.verifyMove(this, position)).toList());
            }
        }

        if(color == Color.BLACK) {
            for(Piece piece : blackPieces) {
                if(piece instanceof King) {
                    if(!((King) piece).wasMoved()) {
                        continue;
                    }
                }
                List<Position> legalMoves = piece.getLegalMoves(this);
                allLegalMoves.addAll(legalMoves.stream().filter(position -> piece.verifyMove(this, position)).toList());
            }
        }

        return allLegalMoves;
    }

    private King getKing(Color color) {
        if(color == Color.WHITE) {
            return (King) whitePieces.stream().filter(piece -> piece instanceof King).findFirst().get();
        }

        if(color == Color.BLACK) {
            return (King) blackPieces.stream().filter(piece -> piece instanceof King).findFirst().get();
        }
        return null;
    }

    public void removePieceFromList(Piece piece) {
        if(piece == null) return;

        if(whitePieces.contains(piece)) {
            whitePieces.remove(piece);
            return;
        }

        if(blackPieces.contains(piece)) {
            blackPieces.remove(piece);
        }
    }

    public void addPieceToList(Piece piece) {
        if(piece == null) return;

        if(piece.getColor() == Color.WHITE) {
            if(whitePieces.contains(piece)) return;
            whitePieces.add(piece);
            return;
        }

        if(piece.getColor() == Color.BLACK) {
            if(blackPieces.contains(piece)) return;
            blackPieces.add(piece);
        }
    }

    private void initializeLists() {
        whitePieces = new ArrayList<>();
        blackPieces = new ArrayList<>();

        for(Position[] positions : board) {
            for(Position position : positions) {
                if(!position.isOccupied()) continue;

                if(position.getPiece().getColor() == Color.WHITE) {
                    whitePieces.add(position.getPiece());
                    continue;
                }

                if(position.getPiece().getColor() == Color.BLACK) {
                    blackPieces.add(position.getPiece());
                }
            }
        }
    }

}
