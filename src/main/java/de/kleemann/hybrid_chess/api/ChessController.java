package de.kleemann.hybrid_chess.api;

import de.kleemann.hybrid_chess.api.models.CreateGameModel;
import de.kleemann.hybrid_chess.api.models.UpdateGameModel;
import de.kleemann.hybrid_chess.core.ChessService;
import de.kleemann.hybrid_chess.core.game.*;
import de.kleemann.hybrid_chess.core.game.utils.Color;
import de.kleemann.hybrid_chess.core.game.utils.GameState;
import de.kleemann.hybrid_chess.core.game.utils.Move;
import de.kleemann.hybrid_chess.core.game.utils.Player;
import de.kleemann.hybrid_chess.persistence.ChessPersistenceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
class ChessController {

    private final ChessService chessService;
    private final ChessPersistenceService chessPersistenceService;

    ChessController(ChessService chessService, ChessPersistenceService chessPersistenceService) {
        this.chessService = chessService;
        this.chessPersistenceService = chessPersistenceService;
    }

    @GetMapping("")
    public ResponseEntity<String> getInfo() {
        return new ResponseEntity<>("Called API /api/game", HttpStatus.OK);
    }

    /**
     * Parameter:
     * {
     *     "startColor" : "BLACK",
     *     "playerOne" : "testPlayerOne",
     *     "colorPlayerOne": "WHITE",
     *     "playerTwo" : "testPlayerTwo",
     *     "colorPlayerTwo": "BLACK"
     * }
     * @return
     */
    @PostMapping("/games/create")
    public ResponseEntity<ChessGame> createGame(@RequestBody CreateGameModel createGameModel) {
        if(createGameModel == null) {
            throw new IllegalArgumentException("createGameModel must not be empty.");
        }
        final ChessGame chessGame = getChessGameFromModel(null, createGameModel);
        return ResponseEntity.ok(chessGame);
    }

    private ChessGame getChessGameFromModel(Integer chessGameId, CreateGameModel model) {
        final ChessGame chessGame = chessService.createChessGameEntity();
        boolean persist = false;
        if(chessGameId == null) {
            persist = true;
        } else {
            chessGame.setId(chessGameId);
        }
        chessGame.setGameState(GameState.RUNNING);
        Player playerOne = new Player(model.getPlayerOne(), model.getColorPlayerOne());
        Player playerTwo = new Player(model.getPlayerTwo(), model.getColorPlayerTwo());
        chessGame.setPlayers(new Player[]{playerOne, playerTwo});
        chessGame.setWhoIsPlaying(model.getStartColor() == Color.BLACK
                ? Arrays.stream(chessGame.getPlayers()).filter(player -> player.getColor() == Color.BLACK).findFirst().get()
                : Arrays.stream(chessGame.getPlayers()).filter(player -> player.getColor() == Color.WHITE).findFirst().get());
        ChessBoard chessBoard = new ChessBoard();
        chessGame.setBoard(chessBoard);
        //boolean testMove = chessGame.getBoard().getBoard()[1][4].getPiece().move(chessBoard, 2, 4);
        if(persist) {
            final ChessGame persistedChessGame = chessService.persistChessGame(chessGame);
            return persistedChessGame;
        }
        return chessGame;
    }

    private ChessGame getChessGameFromUpdateModel(Integer chessGameId, UpdateGameModel model) {
        final ChessGame chessGame = chessService.createChessGameEntity();
        chessGame.setId(chessGameId);
        chessGame.setGameState(GameState.RUNNING);
        chessGame.setWhoIsPlaying(model.getWhoIsPlaying());
        //ChessGame test = getChessGameById(2).getBody();
        //.setMoves(Arrays.asList(new Move(test.getBoard().getBoard()[1][4], test.getBoard().getBoard()[2][4]), new Move(test.getBoard().getBoard()[1][3], test.getBoard().getBoard()[2][3])));
        //chessGame.updateChessBoard();
        chessGame.setMoves(Arrays.asList(model.getMove()));

        //boolean testMove = chessGame.getBoard().getBoard()[1][4].getPiece().move(chessBoard, 2, 4);
        //final ChessGame persistedChessGame = chessService.persistChessGame(chessGame);
        //return persistedChessGame;
        return chessGame;
    }

    /**
     * Parameter:
     * {
     *     "id" : 2,
     *     "whoIsPlaying" : {
     *         "name": "One"
     *       },
     *       "move" : {
     *         "previousPos": {
     *           "x": 4,
     *           "y": 6
     *         },
     *         "newPos": {
     *           "x": 4,
     *           "y": 3
     *         }
     *       }
     * }
     * @param chessGameId
     * @param updateGameModel
     * @return
     */
    @PutMapping("/games/{id}")
    public ResponseEntity<ChessGame> updateChessGame(@PathVariable(value = "id") int chessGameId,
                                                     @RequestBody UpdateGameModel updateGameModel) {
        if(updateGameModel == null) {
            throw new IllegalArgumentException("updateGameModel must not be null.");
        }
        final ChessGame chessGame = getChessGameFromUpdateModel(chessGameId, updateGameModel);
        return ResponseEntity.ok(chessService.updateChessGame(chessGameId, chessGame));
    }

    @GetMapping("/print/{id}")
    public ResponseEntity<String> printChessGameById(@PathVariable(value = "id") int chessGameId) {
        ChessGame chessGame = chessService.findChessGameById(chessGameId);
        if(chessGame == null) {
            return ResponseEntity.ok("Game not found: " + chessGameId);
        }
        /*if(chessGame.getBoard() == null) {
            chessGame.setBoard(new ChessBoard());
        }*/
        chessGame.updateChessBoard();
        return ResponseEntity.ok(chessGame.toString() + "\n" + chessGame.getBoard().printChessBoard());
    }

    @GetMapping("/moves/{id}")
    public ResponseEntity<List<Move>> getMovesFromChessGameById(@PathVariable(value = "id") int chessGameId) {
        ChessGame chessGame = chessService.findChessGameById(chessGameId);
        if(chessGame == null) {
            throw new IllegalArgumentException("chessGame must not be null.");
        }
        if(chessGame.getMoves() == null) {
            return ResponseEntity.ok(new ArrayList<>());
        }
        return ResponseEntity.ok(chessGame.getMoves());
    }

    @GetMapping("/games/{id}")
    public ResponseEntity<ChessGame> getChessGameById(@PathVariable(value = "id") int chessGameId) {
        ChessGame chessGame = chessService.findChessGameById(chessGameId);
       if(chessGame.getBoard() == null) {
            chessGame.setBoard(new ChessBoard());
        }
        return ResponseEntity.ok(chessGame);
    }

    @GetMapping("/games")
    public ResponseEntity<List<ChessGame>> getAllChessGames() {
        final List<ChessGame> chessGames = chessService.findAllChessGames();
        return ResponseEntity.ok(chessGames);
    }


    @PostMapping("/reset") // oder /restart oder /stop
    public ResponseEntity<String> reset() {
        return new ResponseEntity<>("Called API /api/game/reset", HttpStatus.OK);
    }

}
