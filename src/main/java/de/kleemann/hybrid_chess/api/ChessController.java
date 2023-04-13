package de.kleemann.hybrid_chess.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/game")
public class ChessController {

    @GetMapping("/")
    public ResponseEntity<String> getInfo() {
        System.err.println("[HybridChess] Called API /api/game --> greeting()");
        return new ResponseEntity<>("Backend successfully started.", HttpStatus.OK);
    }

    @GetMapping("/board")
    public ResponseEntity<String> getBoard() {
        System.err.println("[HybridChess] Called API /api/game --> greeting()");
        return new ResponseEntity<>("Backend successfully started.", HttpStatus.OK);
    }

    @PostMapping("/create") // oder /start
    public ResponseEntity<String> createGame() {
        System.err.println("[HybridChess] Called API /api/game --> greeting()");
        return new ResponseEntity<>("Backend successfully started.", HttpStatus.OK);
    }

    @PostMapping("/reset") // oder /restart oder /stop
    public ResponseEntity<String> reset() {
        System.err.println("[HybridChess] Called API /api/game --> greeting()");
        return new ResponseEntity<>("Backend successfully started.", HttpStatus.OK);
    }

    @PostMapping("/move")
    public ResponseEntity<String> move() {
        System.err.println("[HybridChess] Called API /api/game --> greeting()");
        return new ResponseEntity<>("Backend successfully started.", HttpStatus.OK);
    }

    @GetMapping("/history")
    public ResponseEntity<String> getHistory() {
        System.err.println("[HybridChess] Called API /api/game --> greeting()");
        return new ResponseEntity<>("Backend successfully started.", HttpStatus.OK);
    }

}
