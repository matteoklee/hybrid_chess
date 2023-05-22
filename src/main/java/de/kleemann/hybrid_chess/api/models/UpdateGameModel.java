package de.kleemann.hybrid_chess.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.kleemann.hybrid_chess.core.game.Move;
import de.kleemann.hybrid_chess.core.game.Player;

import java.util.List;

public class UpdateGameModel {

    @JsonProperty
    private int id;
    @JsonProperty
    private Player whoIsPlaying;
    @JsonProperty
    private Move move;


    public int getId() {
        return id;
    }

    public Player getWhoIsPlaying() {
        return whoIsPlaying;
    }

    public Move getMove() {
        return move;
    }
}
