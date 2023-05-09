package de.kleemann.hybrid_chess.api;

import de.kleemann.hybrid_chess.api.models.StartGameModel;
import de.kleemann.hybrid_chess.core.ChessService;
import de.kleemann.hybrid_chess.core.game.ChessGame;
import de.kleemann.hybrid_chess.core.game.Color;
import de.kleemann.hybrid_chess.core.game.GameState;
import de.kleemann.hybrid_chess.core.game.Player;
import de.kleemann.hybrid_chess.persistence.ChessPersistenceService;
import de.kleemann.hybrid_chess.persistence.ChessRepository;
import de.kleemann.hybrid_chess.persistence.documents.ChessGameDocument;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/api/game")
class ChessController {

    private final ChessService chessService;
    private final ChessPersistenceService chessPersistenceService;

    ChessController(ChessService chessService, ChessPersistenceService chessPersistenceService) {
        this.chessService = chessService;
        this.chessPersistenceService = chessPersistenceService;
    }

    @GetMapping("")
    public ResponseEntity<String> getInfo() {
        ChessGameDocument chessGameDocument = new ChessGameDocument(1, GameState.RUNNING, "BLACK");
        chessPersistenceService.createChessGame(chessGameDocument);
        return new ResponseEntity<>("Called API /api/game\n" + chessPersistenceService.test(), HttpStatus.OK);
    }

    /**
     * {
     *   "gameState" : RUNNING,
     *   "whosTurn" : white,
     *   "pieces" : [
     *     {
     *       "piece" : "pawn",
     *       "color" : "white",
     *       "position" : "a2"
     *     },
     *     {
     *       "piece" : "pawn",
     *       "color" : "white",
     *       "position" : "b2"
     *     }
     *   ]
     * }
     * @return
     */
    @GetMapping("/board")
    public ResponseEntity<String> getBoard() {
        return new ResponseEntity<>("Called API /api/game/board", HttpStatus.OK);
    }

    /**
     * Parameter:
     * {
     *   "time" : 10,
     *   "startColor" : white,
     *   "player1" : "Frontend",
     *   "player2" : "Raspberry Pi"
     * }
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity<String> createGame(@RequestBody StartGameModel startGameModel) {
        if(startGameModel == null) {
            throw new IllegalArgumentException("startGameModel must not be empty.");
        }
        ChessGame chessGame = chessService.createGame();
        chessGame.setGameState(GameState.RUNNING);
        Player playerOne = new Player(startGameModel.getPlayerOne(), startGameModel.getColorPlayerOne());
        Player playerTwo = new Player(startGameModel.getPlayerTwo(), startGameModel.getColorPlayerTwo());
        chessGame.setPlayers(new Player[]{playerOne, playerTwo});
        chessGame.setWhoIsPlaying(startGameModel.getStartColor() == Color.BLACK
                ? Arrays.stream(chessGame.getPlayers()).filter(player -> player.getColor() == Color.BLACK).findFirst().get()
                : Arrays.stream(chessGame.getPlayers()).filter(player -> player.getColor() == Color.WHITE).findFirst().get());
        //ChessGame persistedChessGame = chessService.persistGame(chessGame);
        return new ResponseEntity<>("Called API /api/game/create\n" + startGameModel.toString() + "\n" + chessGame.toString(), HttpStatus.OK);
    }

    @PostMapping("/reset") // oder /restart oder /stop
    public ResponseEntity<String> reset() {
        return new ResponseEntity<>("Called API /api/game/reset", HttpStatus.OK);
    }

    /**
     * {
     *     "sourcePiece" :  {
     *       "piece" : "pawn",
     *       "color" : "white",
     *       "position" : "b2"
     *     },
     *     "destPosition" : "c2"
     * }
     * @return
     */
    @PostMapping("/move")
    public ResponseEntity<String> move() {
        return new ResponseEntity<>("Called API /api/game/move", HttpStatus.OK);
    }

    /**
     * [
     *   {
     *     "id" : 1,
     *     "sourcePiece" :  {
     *       "piece" : "pawn",
     *       "color" : "white",
     *       "position" : "b2"
     *     },
     *     "destPosition" : "c2"
     *   },
     *   {
     *     "id" : 2,
     *     "sourcePiece" :  {
     *       "piece" : "pawn",
     *       "color" : "white",
     *       "position" : "c2"
     *     },
     *     "destPosition" : "d2"
     *   }
     * ]
     * @return
     */
    @GetMapping("/history")
    public ResponseEntity<String> getHistory() {
        System.err.println("[HybridChess] Called API /api/game --> greeting()");
        return new ResponseEntity<>("Backend successfully started.", HttpStatus.OK);
    }

    @GetMapping("/history/{gameId}")
    public ResponseEntity<String> getHistory(@PathVariable(value = "gameId") long gameId) {
        System.err.println("[HybridChess] Called API /api/game --> greeting()");
        return new ResponseEntity<>("Backend successfully started.", HttpStatus.OK);
    }

}
