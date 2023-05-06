package de.kleemann.hybrid_chess;

import de.kleemann.hybrid_chess.core.game.ChessBoard;
import de.kleemann.hybrid_chess.core.game.Color;
import de.kleemann.hybrid_chess.core.game.Position;
import de.kleemann.hybrid_chess.core.game.pieces.*;
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

        Pawn pawn = new Pawn(Color.BLACK, board[5][5]);
        King king = new King(Color.WHITE, board[0][0]);
        Knight knight = new Knight(Color.WHITE, board[6][4]);
        Bishop bishop = new Bishop(Color.BLACK, board[3][5]);
        Rook rook = new Rook(Color.WHITE, board[3][3]);
        Queen queen = new Queen(Color.WHITE, board[4][3]);

        printBoard(chessBoard);

        List<Position> legalMoves = queen.getLegalMoves(chessBoard);

        for(Position legal : legalMoves) {
            System.out.println("(" + legal.getY() + ", " + legal.getX() + ")");
        }

        /*boolean b = pawn.move(chessBoard, 5, 2);

        System.out.println(b);

        printBoard(chessBoard);*/
    }

    private static void printBoard(ChessBoard chessBoard) {
        Position[][] board = chessBoard.getBoard();

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                String print = board[i][j].isOccupied() ? "B " : ". ";
                System.out.print(print);
            }
            System.out.println(" ");
        }
    }

}

