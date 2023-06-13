package de.kleemann.hybrid_chess.exceptions;

public class ChessGameNotFoundException extends RuntimeException{

    public ChessGameNotFoundException(int id) {
        super("unknown chessGame with id: " + id);
        this.setStackTrace(new StackTraceElement[0]);
    }

}
