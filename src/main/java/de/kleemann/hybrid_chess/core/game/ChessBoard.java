package de.kleemann.hybrid_chess.core.game;

import de.kleemann.hybrid_chess.core.game.pieces.*;

public class ChessBoard {

    private int rows;
    private int columns;

    private Position[][] board;

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
                switch(i) {
                    case 0:
                        initialSetup(this.board, Color.WHITE, i, j);
                        break;
                    case 1:
                        this.board[i][j] = new Position(i, j, new Pawn(Color.BLACK, j, i));
                        break;
                    case 6:
                        this.board[i][j] = new Position(i, j, new Pawn(Color.WHITE, j, i));
                        break;
                    case 7:
                        initialSetup(this.board, Color.WHITE, i, j);
                        break;
                    default:
                        this.board[i][j] = new Position(i, j, null);
                        break;
                }
            }
        }
    }

    private void initialSetup(Position[][] board, Color color, int i, int j) {
        switch(j) {
            case 0, 7:
                board[i][j] = new Position(i, j, new Rook(color, j, i));
                break;
            case 1, 6:
                board[i][j] = new Position(i, j, new Knight(color, j, i));
                break;
            case 2, 5:
                board[i][j] = new Position(i, j, new Bishop(color, j, i));
                break;
            case 3:
                board[i][j] = new Position(i, j, new Queen(color, j, i));
                break;
            case 4:
                board[i][j] = new Position(i, j, new King(color, j, i));
                break;
            default:
                break;
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
}
