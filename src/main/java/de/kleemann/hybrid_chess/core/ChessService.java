package de.kleemann.hybrid_chess.core;

import de.kleemann.hybrid_chess.api.models.CreateGameModel;
import de.kleemann.hybrid_chess.core.game.*;
import de.kleemann.hybrid_chess.persistence.ChessPersistenceService;
import de.kleemann.hybrid_chess.persistence.entities.ChessGameEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChessService {

    private final ChessPersistenceService chessPersistenceService;


    public ChessService(ChessPersistenceService chessPersistenceService) {
        this.chessPersistenceService = chessPersistenceService;
    }

    public List<Move> getAllMovesFromGame(long gameId) {
        return null;
    }
    public Move findMoveFromGame(long gameId, long moveId) {
        return null;
    }

    //**********************************************************************

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

    //**********************************************************************

    public ChessGame updateGame(int gameId, Move move) {
        return null;
    }

    public ChessGame updateGame(ChessGame chessGame) {
        return null;
    }

    public ChessGame createGame(long id) {
        return null;
    }
    public ChessGame persistGame(ChessGame game) {
        return null;
    }
    public ChessGame findGameById(Long id) {
        return null;
    }
    public List<ChessGame> findAllGames() {
        return null;
    }

    //**********************************************************************

    public Move createMove() {
        return null;
    }
    public Move createMove(long id) {
        return null;
    }
    public Move persistMove(Move move) {
        return null;
    }

}
