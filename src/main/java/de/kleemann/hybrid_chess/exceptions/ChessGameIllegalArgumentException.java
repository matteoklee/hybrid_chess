package de.kleemann.hybrid_chess.exceptions;

public class ChessGameIllegalArgumentException extends RuntimeException {

    public ChessGameIllegalArgumentException(String message) {
        super(message);
        this.setStackTrace(new StackTraceElement[0]);
    }

}
