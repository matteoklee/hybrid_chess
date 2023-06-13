package de.kleemann.hybrid_chess.core.game;

import de.kleemann.hybrid_chess.core.game.pieces.*;
import de.kleemann.hybrid_chess.core.game.utils.Color;
import de.kleemann.hybrid_chess.core.game.utils.Position;

public class ChessBoard {

    private final int rows;
    private final int columns;

    private Position[][] board;
    private CheckDetector checkDetector;

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
        //this.checkDetector = new CheckDetector(this);
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

    public CheckDetector getCheckDetector() {
        if(this.checkDetector == null) {
           this.checkDetector = new CheckDetector(this);
        }
        return this.checkDetector;
    }

    public void setCheckDetector(CheckDetector checkDetector) {
        this.checkDetector = checkDetector;
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


}
