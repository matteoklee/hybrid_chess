package de.kleemann.hybrid_chess.core.game;

public class Move {

    //private int id;
    private Position previousPos;
    private Position newPos;

    public Move(/*int id,*/ Position previousPos, Position newPos) {
        //this.id = id;
        this.previousPos = previousPos;
        this.newPos = newPos;
    }

    /*
    public int getId() {
        return id;
    }
    */

    public Position getPreviousPos() {
        return previousPos;
    }

    public Position getNewPos() {
        return newPos;
    }
}
