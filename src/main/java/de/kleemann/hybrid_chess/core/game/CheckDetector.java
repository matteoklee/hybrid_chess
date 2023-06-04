package de.kleemann.hybrid_chess.core.game;

import de.kleemann.hybrid_chess.core.game.pieces.King;
import de.kleemann.hybrid_chess.core.game.pieces.Piece;
import de.kleemann.hybrid_chess.core.game.utils.Color;
import de.kleemann.hybrid_chess.core.game.utils.Position;

import java.util.ArrayList;
import java.util.List;

public class CheckDetector {

    private final ChessBoard chessBoard;

    private ArrayList<Piece> whitePieces;
    private ArrayList<Piece> blackPieces;

    public CheckDetector(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
        initializeLists();
    }

    public boolean isKingInCheck(King king) {
        Position[][] board = chessBoard.getBoard();
        Position kingPosition = board[king.getY()][king.getX()];

        if(king.getColor() == Color.WHITE) {
            return arePositionsThreatened(blackPieces, kingPosition);
        }

        if(king.getColor() == Color.BLACK) {
            return arePositionsThreatened(whitePieces, kingPosition);
        }
        return false;
    }

    public boolean isPositionThreatened(Color color, Position ... positions) {
        if(color == Color.WHITE) {
            return arePositionsThreatened(blackPieces, positions);
        }

        if(color == Color.BLACK) {
            return arePositionsThreatened(whitePieces, positions);
        }
        return false;
    }

    private boolean arePositionsThreatened(ArrayList<Piece> pieces, Position ... positions) {
        for(Piece piece : pieces) {
            if(piece instanceof King) continue; // Endlosschleife verhindern

            List<Position> legalMoves = piece.getLegalMoves(chessBoard);

            for(Position legal : legalMoves) {
                for(Position position : positions) {
                    if(legal == position) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void initializeLists() {
        Position[][] board = chessBoard.getBoard();

        whitePieces = new ArrayList<>();
        blackPieces = new ArrayList<>();

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                if(!board[i][j].isOccupied()) continue;

                if(board[i][j].getPiece().getColor() == Color.WHITE) {
                    whitePieces.add(board[i][j].getPiece());
                    continue;
                }

                if(board[i][j].getPiece().getColor() == Color.BLACK) {
                    blackPieces.add(board[i][j].getPiece());
                }
            }
        }
    }

}
