package de.kleemann.hybrid_chess.api;

import de.kleemann.hybrid_chess.api.models.CreateGameModel;
import de.kleemann.hybrid_chess.api.models.UpdateGameModel;
import de.kleemann.hybrid_chess.core.ChessService;
import de.kleemann.hybrid_chess.core.game.*;
import de.kleemann.hybrid_chess.core.game.utils.*;
import de.kleemann.hybrid_chess.exceptions.ChessGameIllegalArgumentException;
import de.kleemann.hybrid_chess.socket.WebSocketController;
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
    private final WebSocketController webSocketController;

    ChessController(final ChessService chessService, WebSocketController webSocketController) {
        this.chessService = chessService;
        this.webSocketController = webSocketController;
    }

    @GetMapping("/1")
    public ResponseEntity<String> getInfo() {
        makeDummyMove(5, 1, 5, 2);
        return new ResponseEntity<>("makeDummyMove(5, 1, 5, 2);", HttpStatus.OK);
    }

    @GetMapping("/2")
    public ResponseEntity<String> getInfo2() {
        makeDummyMove(6, 1, 6, 3);
        return new ResponseEntity<>("makeDummyMove(6, 1, 6, 3);", HttpStatus.OK);
    }

    @GetMapping("/3")
    public ResponseEntity<String> getInfo3() {
        makeDummyMove(0, 1, 0, 3);
        return new ResponseEntity<>("makeDummyMove(0, 1, 0, 3);", HttpStatus.OK);
    }

    private void makeDummyMove(int fromX, int fromY, int toX, int toY) {
        ChessGame latestChessGame = chessService.findLatestChessGame();
        latestChessGame.loadChessBoard();
        int latestGameId = latestChessGame.getId();
        System.err.println("FOUNT LATEST CHESS GAME WITH ID: " + latestGameId);
        ResponseEntity<ChessGame> updatedChessGame = updateChessGame(latestGameId, new UpdateGameModel(latestGameId, new Player("BACKEND", Color.BLACK),
                new Move(new Position(fromY, fromX, null), new Position(toY, toX, null))));
        if(updatedChessGame.getStatusCode() == HttpStatus.OK) {
            if(updatedChessGame.getBody() != null) {
                System.err.println("SIZE UPDATED CHESS GAME = " + updatedChessGame.getBody().getMoves().size());
                System.err.println("SIZE LATEST CHESS GAME = " + latestChessGame.getMoves().size());
                if(updatedChessGame.getBody().getMoves().size() == latestChessGame.getMoves().size()) {
                    System.err.println("BOARD HAS NOT CHANGED YET!");
                } else {
                    System.err.println("BOARD HAS CHANGED!");
                    webSocketController.sendMessageToFrontend();
                }
            } else {
                System.err.println("Error updating chess game");
            }
        }
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
            throw new ChessGameIllegalArgumentException("createGameModel must not be empty.");
        }
        final ChessGame chessGame = getChessGameFromModel(null, createGameModel);
        return ResponseEntity.ok(chessGame);
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
            throw new ChessGameIllegalArgumentException("updateGameModel must not be null.");
        }
        final ChessGame chessGame = getChessGameFromUpdateModel(chessGameId, updateGameModel);
        return ResponseEntity.ok(chessService.updateChessGame(chessGameId, chessGame));
    }

    @GetMapping("/print/{id}")
    public ResponseEntity<String> printChessGameById(@PathVariable(value = "id") int chessGameId) {
        ChessGame chessGame = chessService.findChessGameById(chessGameId);
        if(chessGame == null) {
            throw new ChessGameIllegalArgumentException("chessGame must not be null. id: " + chessGameId);
        }
        chessGame.loadChessBoard();
        return ResponseEntity.ok(chessGame.toString() + "\n" + chessGame.getBoard().printChessBoard());
    }

    @GetMapping("/moves/{id}")
    public ResponseEntity<List<Move>> getMovesFromChessGameById(@PathVariable(value = "id") int chessGameId) {
        ChessGame chessGame = chessService.findChessGameById(chessGameId);
        if(chessGame == null) {
            throw new ChessGameIllegalArgumentException("chessGame must not be null.");
        }
        if(chessGame.getMoves() == null) {
            return ResponseEntity.ok(new ArrayList<>());
        }
        return ResponseEntity.ok(chessGame.getMoves());
    }

    @GetMapping("/games/{id}")
    public ResponseEntity<ChessGame> getChessGameById(@PathVariable(value = "id") int chessGameId) {
        ChessGame chessGame = chessService.findChessGameById(chessGameId);
        //TODO: null Check of chessGame  Object
        if(chessGame != null) {
            //chessGame.setBoard(new ChessBoard());
            chessGame.loadChessBoard();
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
        chessGame.setMoves(new ArrayList<>(Arrays.asList(model.getMove())));
        return chessGame;
    }


}
