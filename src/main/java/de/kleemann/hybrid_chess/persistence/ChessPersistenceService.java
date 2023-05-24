package de.kleemann.hybrid_chess.persistence;

import de.kleemann.hybrid_chess.persistence.entities.ChessGameEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ChessPersistenceService {

    private final ChessRepository chessRepository;
    private final SequenceGeneratorService sequenceGeneratorService;

    public ChessPersistenceService(final ChessRepository chessRepository, final SequenceGeneratorService sequenceGeneratorService) {
        this.chessRepository = chessRepository;
        this.sequenceGeneratorService = sequenceGeneratorService;
    }

    public ChessGameEntity createChessGameEntity(Integer id) {
        return new ChessGameEntity(id);
        //chessGame.setId(sequenceGeneratorService.generateSequence(ChessGameEntity.SEQUENCE_NAME));
    }

    public ChessGameEntity createChessGameEntity() {
        return new ChessGameEntity();
    }

    private ChessGameEntity saveChessGame(ChessGameEntity chessGame) {
        return chessRepository.save(chessGame);
    }

    public ChessGameEntity updateChessGame(int chessGameId, ChessGameEntity chessGameEntity) {
        if(chessGameEntity == null) {
            throw new IllegalArgumentException("chessGameEntity must not be null.");
        }
        ChessGameEntity updatedChessGameEntity = findChessGameById(chessGameId);
        updatedChessGameEntity.setId(chessGameId);
        updatedChessGameEntity.setGameState(chessGameEntity.getGameState());
        //updatedChessGameEntity.setPlayers(chessGameEntity.getPlayers());
        updatedChessGameEntity.setWhoIsPlaying(chessGameEntity.getWhoIsPlaying());
        updatedChessGameEntity.getMoves().add(chessGameEntity.getMoves().get(0));
        return saveChessGame(updatedChessGameEntity);
    }

    public ChessGameEntity findChessGameById(int id) {
        return chessRepository.findById(id).orElseThrow(() -> new NoSuchElementException("unknown chessGame with id: " + id));
    }

    public List<ChessGameEntity> findAll() {
        return new ArrayList<>(chessRepository.findAll());
    }

    public ChessGameEntity persistChessGame(ChessGameEntity chessGameEntity) {
        if(chessGameEntity == null) {
            throw new IllegalArgumentException("chessGameEntity must not be null.");
        }
        if(chessGameEntity.getId() != null) {
            throw new IllegalArgumentException("new chessGameEntity must not contain an id.");
        }
        return saveChessGame(chessGameEntity);
    }

}
