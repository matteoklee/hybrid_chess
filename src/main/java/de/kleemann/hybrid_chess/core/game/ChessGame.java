package de.kleemann.hybrid_chess.core.game;

import de.kleemann.hybrid_chess.persistence.entities.ChessGameEntity;
import org.springframework.data.annotation.Id;

import javax.sound.midi.SysexMessage;
import java.util.ArrayList;
import java.util.List;

public class ChessGame {

    private ChessBoard board;
    private final ChessGameEntity chessGameEntity;


    public ChessGame(ChessGameEntity chessGameEntity) {
        if(chessGameEntity == null) {
            throw new IllegalArgumentException("chessGameEntity may not be null.");
        }
        this.chessGameEntity = chessGameEntity;
    }

    public ChessBoard getBoard() {
        if(board == null) {
            board = new ChessBoard();
        }
        return board;
    }

    public ChessBoard updateChessBoard() {
        if(chessGameEntity.getMoves() == null) return board;
        //TODO: Anhand der Moves Positionen der Figuren bestimmen und ChessBoard Objekt erstellen
        for(Move move : chessGameEntity.getMoves()) {
            boolean validMove = getBoard().getBoard()[move.getPreviousPos().getY()][move.getPreviousPos().getX()].getPiece().move(board, move.getNewPos().getY(), move.getNewPos().getX());
            System.err.println("CHESS BOARD HANDLE MOVE: " + validMove);
        }
        return board;
    }

    public void setBoard(ChessBoard board) {
        this.board = board;
    }

    public ChessGameEntity getChessGameEntity() {
        return chessGameEntity;
    }


    public int getId() {
        return this.chessGameEntity.getId();
    }

    public void setId(int id) {
        this.chessGameEntity.setId(id);
    }

    public GameState getGameState() {
        return this.chessGameEntity.getGameState();
    }

    public void setGameState(GameState gameState) {
        this.chessGameEntity.setGameState(gameState);
    }

    public Player getWhoIsPlaying() {
        return this.chessGameEntity.getWhoIsPlaying();
    }

    public void setWhoIsPlaying(Player whoIsPlaying) {
        this.chessGameEntity.setWhoIsPlaying(whoIsPlaying);
    }

    public Player[] getPlayers() {
        return this.chessGameEntity.getPlayers();
    }

    public void setPlayers(Player[] players) {
        this.chessGameEntity.setPlayers(players);
    }

    public List<Move> getMoves() {
        return this.chessGameEntity.getMoves();
    }

    public void setMoves(List<Move> moves) {
        this.chessGameEntity.setMoves(moves);
    }

    @Override
    public String toString() {
        return "ChessGame{\nid: " + getId() + ",\ngamestate: " + getGameState() + ",\nplayer1: " + getPlayers()[0].toString()
                + ",\nplayer2: " + getPlayers()[1].toString() + ",\nwhoIsPlaying: " + getWhoIsPlaying().toString() + "\n}";
    }
}
