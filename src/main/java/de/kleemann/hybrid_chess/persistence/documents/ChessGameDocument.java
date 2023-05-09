package de.kleemann.hybrid_chess.persistence.documents;

import de.kleemann.hybrid_chess.core.game.GameState;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class ChessGameDocument {

    @Id
    private Integer gameId;
    @Field
    private GameState gameState;
    @Field
    private String whosTurn;

    public ChessGameDocument(Integer gameId, GameState gameState, String whosTurn) {
        this.gameId = gameId;
        this.gameState = gameState;
        this.whosTurn = whosTurn;
    }

    public Integer getGameId() {
        return gameId;
    }

    public GameState getGameState() {
        return gameState;
    }

    public String getWhosTurn() {
        return whosTurn;
    }
}
