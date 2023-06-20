package de.kleemann.hybrid_chess.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.kleemann.hybrid_chess.core.game.utils.Move;
import de.kleemann.hybrid_chess.core.game.utils.Player;

public class UpdateGameModel {

    @JsonProperty
    private int id;
    @JsonProperty
    private Player whoIsPlaying;
    @JsonProperty
    private Move move;

    public UpdateGameModel() {

    }
    public UpdateGameModel(int id, Player whoIsPlaying, Move move) {
        this.id = id;
        this.whoIsPlaying = whoIsPlaying;
        this.move = move;
    }

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
