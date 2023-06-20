package de.kleemann.hybrid_chess.exceptions;

public class ChessGameNotFoundException extends RuntimeException{

    public ChessGameNotFoundException(int id) {
        super("unknown chessGame with id: " + id);
        this.setStackTrace(new StackTraceElement[0]);
    }

    public ChessGameNotFoundException(String error) {
        super("unknown chessGame: " + error);
        this.setStackTrace(new StackTraceElement[0]);
    }

}
