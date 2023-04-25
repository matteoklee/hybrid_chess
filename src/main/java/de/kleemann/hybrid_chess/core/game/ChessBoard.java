package de.kleemann.hybrid_chess.core.game;

public class ChessBoard {

    private int rows;
    private int columns;

    private Position[][] board;


    public static int DEFAULT_ROWS = 8;
    public static int DEFAULT_COLUMNS = 8;

    public ChessBoard() {
        this(DEFAULT_ROWS, DEFAULT_COLUMNS);
    }

    public ChessBoard(int rows, int columns) {
        if(rows <= 0 ||  columns <= 0) throw new IllegalArgumentException("rows and columns must be greater than 0.");
        this.rows = rows;
        this.columns = columns;
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

    //Test
    public Position[][] getBoard() {
        return board;
    }
}
