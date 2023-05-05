package de.kleemann.hybrid_chess;

import de.kleemann.hybrid_chess.core.game.ChessBoard;
import de.kleemann.hybrid_chess.core.game.Color;
import de.kleemann.hybrid_chess.core.game.Position;
import de.kleemann.hybrid_chess.core.game.pieces.Pawn;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@SpringBootApplication
public class HybridChessApplication {

    public static void main(String[] args) {
        //SpringApplication.run(HybridChessApplication.class, args);
        runTest();
        System.err.println("[HybridChess] Backend successfully started.");
    }

    @RequestMapping("/")
    public String greeting() {
        return "Backend successfully started.";
    }

    private static void runTest() {
        ChessBoard chessBoard = new ChessBoard();
        Position[][] board = chessBoard.getBoard();

        Pawn pawn = new Pawn(Color.WHITE, board[7][2]);
        pawn.getPosition().setPiece(pawn);

        List<Position> legalMoves = pawn.getLegalMoves(chessBoard);

        for(Position legal : legalMoves) {
            System.out.println("y: " + legal.getY() + " x: " + legal.getX());
        }

        boolean b = pawn.move(chessBoard, 6, 3);

        System.out.println(b);
    }

}

