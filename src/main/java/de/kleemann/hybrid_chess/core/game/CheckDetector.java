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

    public void checkForCheckmateOrStalemate(Color color) {
        if(getAllLegalPositions(color).size() > 0) {
            return;
        }

        if(isKingInCheck(getKing(color))) {
            System.err.println("CHECKMATE");
            return;
        }
        System.err.println("STALEMATE");
    }

    public boolean isKingInCheck(Color color) {
        return isKingInCheck(getKing(color));
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

    public ArrayList<Position> getAllLegalPositions(Color color) {
        ArrayList<Position> allLegalMoves = new ArrayList<>();

        if(color == Color.WHITE) {
            for(Piece piece : whitePieces) {
                if(piece instanceof King) {
                    if(!((King) piece).wasMoved()) {
                        continue;
                    }
                }
                List<Position> legalMoves = piece.getLegalMoves(chessBoard);
                allLegalMoves.addAll(legalMoves.stream().filter(position -> piece.verifyMove(chessBoard, position)).toList());
            }
        }

        if(color == Color.BLACK) {
            for(Piece piece : blackPieces) {
                if(piece instanceof King) {
                    if(!((King) piece).wasMoved()) {
                        continue;
                    }
                }
                List<Position> legalMoves = piece.getLegalMoves(chessBoard);
                allLegalMoves.addAll(legalMoves.stream().filter(position -> piece.verifyMove(chessBoard, position)).toList());
            }
        }

        return allLegalMoves;
    }

    private King getKing(Color color) {
        if(color == Color.WHITE) {
            return (King) whitePieces.stream().filter(piece -> piece instanceof King).findFirst().get();
        }

        if(color == Color.BLACK) {
            return (King) blackPieces.stream().filter(piece -> piece instanceof King).findFirst().get();
        }
        return null;
    }

    public void removePieceFromList(Piece piece) {
        if(piece == null) return;

        if(whitePieces.contains(piece)) {
            whitePieces.remove(piece);
            return;
        }

        if(blackPieces.contains(piece)) {
            blackPieces.remove(piece);
        }
    }

    public void addPieceToList(Piece piece) {
        if(piece == null) return;

        if(piece.getColor() == Color.WHITE) {
            if(whitePieces.contains(piece)) return;
            whitePieces.add(piece);
            return;
        }

        if(piece.getColor() == Color.BLACK) {
            if(blackPieces.contains(piece)) return;
            blackPieces.add(piece);
        }
    }

    private void initializeLists() {
        Position[][] board = chessBoard.getBoard();

        whitePieces = new ArrayList<>();
        blackPieces = new ArrayList<>();

        for(Position[] positions : board) {
            for(Position position : positions) {
                if(!position.isOccupied()) continue;

                if(position.getPiece().getColor() == Color.WHITE) {
                    whitePieces.add(position.getPiece());
                    continue;
                }

                if(position.getPiece().getColor() == Color.BLACK) {
                    blackPieces.add(position.getPiece());
                }
            }
        }
    }

}
