package de.kleemann.hybrid_chess.core;

import de.kleemann.hybrid_chess.core.game.ChessGame;
import de.kleemann.hybrid_chess.core.game.Move;
import de.kleemann.hybrid_chess.persistence.ChessPersistenceService;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public ChessGame createGame() {
        return new ChessGame();
        //return null;
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
