package de.kleemann.hybrid_chess.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.kleemann.hybrid_chess.core.game.Color;

public class CreateGameModel {

    @JsonProperty
    private Color startColor;
    @JsonProperty
    private String playerOne;
    @JsonProperty
    private Color colorPlayerOne;
    @JsonProperty
    private String playerTwo;
    @JsonProperty
    private Color colorPlayerTwo;

    @Override
    public String toString() {
        return "StartGameModel{\nstartColor: " + startColor + ",\nplayer1: " + playerOne + ",\nplayer1Color: " + colorPlayerOne
                + ",\nplayer2: " + playerTwo + ",\nplayer2Color: " + colorPlayerTwo + "\n}";
    }

    public String getPlayerOne() {
        return playerOne;
    }

    public Color getColorPlayerOne() {
        return colorPlayerOne;
    }

    public String getPlayerTwo() {
        return playerTwo;
    }

    public Color getColorPlayerTwo() {
        return colorPlayerTwo;
    }

    public Color getStartColor() {
        return startColor;
    }
}
