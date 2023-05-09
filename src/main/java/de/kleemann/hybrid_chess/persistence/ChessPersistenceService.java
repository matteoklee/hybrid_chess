package de.kleemann.hybrid_chess.persistence;

import de.kleemann.hybrid_chess.core.game.ChessGame;
import de.kleemann.hybrid_chess.persistence.documents.ChessGameDocument;
import org.springframework.stereotype.Service;

@Service
public class ChessPersistenceService {

    private final ChessRepository chessRepository;

    public ChessPersistenceService(ChessRepository chessRepository) {
        this.chessRepository = chessRepository;
    }

    public String test() {
        return chessRepository.findById(0).get().getBoard().printChessBoard();
    }

    public void createChessGame(ChessGame chessGame) {
        chessRepository.save(chessGame);
    }

}
