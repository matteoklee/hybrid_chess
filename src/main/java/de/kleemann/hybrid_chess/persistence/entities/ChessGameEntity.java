package de.kleemann.hybrid_chess.persistence.entities;

import de.kleemann.hybrid_chess.core.game.utils.GameState;
import de.kleemann.hybrid_chess.core.game.utils.Move;
import de.kleemann.hybrid_chess.core.game.utils.Player;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
public class ChessGameEntity {

    @Transient
    public static final String SEQUENCE_NAME = "chessGame_sequence";
    @Id
    private Integer id;
    private GameState gameState;
    private Player[] players;
    private Player whoIsPlaying;
    private ArrayList<Move> moves;

    public ChessGameEntity() {

    }

    public ChessGameEntity(int id) {
        setId(id);
    }

    public Integer getId() {
        return id;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public Player getWhoIsPlaying() {
        return whoIsPlaying;
    }

    public void setWhoIsPlaying(Player whoIsPlaying) {
        this.whoIsPlaying = whoIsPlaying;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Move> getMoves() {
        return moves;
    }

    public void setMoves(ArrayList<Move> moves) {
        this.moves = moves;
    }

    @Override
    public String toString() {
        return "ChessGame{\nid: " + id + ",\ngamestate: " + gameState + ",\nplayer1: " + players[0].toString()
                + ",\nplayer2: " + players[1].toString() + ",\nwhoIsPlaying: " + whoIsPlaying.toString() + "\n}";
    }

}
