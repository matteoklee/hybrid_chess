package de.kleemann.hybrid_chess;

import de.kleemann.hybrid_chess.persistence.ChessPersistenceService;
import de.kleemann.hybrid_chess.persistence.ChessRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class HybridChessApplication {

    public static void main(String[] args) {
        SpringApplication.run(HybridChessApplication.class, args);
        System.err.println("[HybridChess] Backend successfully started.");
    }

    @RequestMapping("/")
    public String greeting() {
        return "Backend successfully started.";
    }

}