package de.kleemann.hybrid_chess.core;

import de.kleemann.hybrid_chess.core.game.*;
import de.kleemann.hybrid_chess.persistence.ChessPersistenceService;
import de.kleemann.hybrid_chess.persistence.entities.ChessGameEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChessService {

    private final ChessPersistenceService chessPersistenceService;


    public ChessService(final ChessPersistenceService chessPersistenceService) {
        this.chessPersistenceService = chessPersistenceService;
    }

    public ChessGame createChessGameEntity() {
        return new ChessGame(chessPersistenceService.createChessGameEntity());
    }

    public ChessGame createChessGameEntity(int id) {
        return new ChessGame(chessPersistenceService.createChessGameEntity(id));
    }

    public ChessGame updateChessGame(int chessGameId, ChessGame chessGame) {
        if(chessGame == null) {
            throw new IllegalArgumentException("chessGame must not be null!");
        }
        final ChessGameEntity updatedChessGameEntity;
        try {
            updatedChessGameEntity = chessPersistenceService.updateChessGame(chessGameId, chessGame.getChessGameEntity());
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
        return new ChessGame(updatedChessGameEntity);
    }

    public ChessGame persistChessGame(ChessGame chessGame) {
        if(chessGame == null) {
            throw new IllegalArgumentException("chessGame must not be null.");
        }
        final ChessGameEntity persistedChessGame;
        persistedChessGame = chessPersistenceService.persistChessGame(chessGame.getChessGameEntity());
        return new ChessGame(persistedChessGame);
    }

    public ChessGame findChessGameById(int id) {
        try {
            return new ChessGame(chessPersistenceService.findChessGameById(id));
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public List<ChessGame> findAllChessGames() {
        return chessPersistenceService.findAll().stream().map(ChessGame::new).collect(Collectors.toList());
    }

}
