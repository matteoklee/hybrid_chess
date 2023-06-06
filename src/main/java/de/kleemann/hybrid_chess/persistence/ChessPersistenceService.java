package de.kleemann.hybrid_chess.persistence;

import de.kleemann.hybrid_chess.core.game.ChessBoard;
import de.kleemann.hybrid_chess.core.game.utils.Move;
import de.kleemann.hybrid_chess.persistence.entities.ChessGameEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ChessPersistenceService {

    private final ChessRepository chessRepository;
    private final SequenceGeneratorService sequenceGeneratorService;

    public ChessPersistenceService(ChessRepository chessRepository, final SequenceGeneratorService sequenceGeneratorService) {
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

    public ChessGameEntity updateChessGame(int chessGameId, ChessGameEntity chessGameEntity, ChessBoard chessBoard) {
        if(chessGameEntity == null) {
            throw new IllegalArgumentException("chessGameEntity must not be null.");
        }
        ChessGameEntity updatedChessGameEntity = findChessGameById(chessGameId);
        updatedChessGameEntity.setId(chessGameId);
        updatedChessGameEntity.setGameState(chessGameEntity.getGameState());
        System.err.println(chessBoard.printChessBoard());
        //updatedChessGameEntity.setPlayers(chessGameEntity.getPlayers());
        System.err.println("Move from last player: " + updatedChessGameEntity.getWhoIsPlaying().getName() + "; new move from player: " + chessGameEntity.getWhoIsPlaying().getName());
        if(!updatedChessGameEntity.getWhoIsPlaying().getName().equals(chessGameEntity.getWhoIsPlaying().getName())) {
            updatedChessGameEntity.setWhoIsPlaying(chessGameEntity.getWhoIsPlaying());
        } else {
            System.err.println("Wrong Player is playing.");
            throw new IllegalArgumentException("Wrong Player is playing.");
        }

        if(chessGameEntity.getMoves() != null) {
            System.err.println("Size of moves: " + chessGameEntity.getMoves().size());
            for (Iterator<Move> it = chessGameEntity.getMoves().iterator(); it.hasNext();) {
                Move move = it.next();
                //TODO: check if player is using figure of own color. --> Error: pieces in movelist are null!
                //TODO: test caes of moves from all possible figures.
                /*
                if(move.getPreviousPos().getPiece().getColor() != chessGameEntity.getWhoIsPlaying().getColor()) {
                    throw new IllegalArgumentException("Player used figure of wrong color");
                }
                */
                if(chessBoard.getBoard()[move.getPreviousPos().getY()][move.getPreviousPos().getX()].getPiece() != null) {
                    //boolean validMove = chessBoard.getBoard()[move.getPreviousPos().getY()][move.getPreviousPos().getX()].getPiece().move(chessBoard, move.getNewPos().getY(), move.getNewPos().getX());
                    boolean validMove = chessBoard.getBoard()[move.getPreviousPos().getY()][move.getPreviousPos().getX()].getPiece().move(chessBoard, move.getNewPos().getY(), move.getNewPos().getX());
                    System.err.println("CHESS BOARD VALIDATES MOVE: " + validMove);
                    System.err.println("Move from " + move.getPreviousPos().getX() + " " + move.getPreviousPos().getY() + " to " + move.getNewPos().getX() + " " + move.getNewPos().getY());

                    if(!validMove) {
                        //chessGameEntity.getMoves().remove(move);
                        it.remove();
                        System.err.println("Move was not saved, because its not valid!");
                    }
                    System.out.println(chessBoard.printChessBoard());
                } else {
                    System.err.println("chessBoard.getBoard()[move.getPreviousPos().getY()][move.getPreviousPos().getX()].getPiece() == null");
                    System.out.println(chessBoard.printChessBoard());
                }
            }
        }

        if(updatedChessGameEntity.getMoves() == null) {
            updatedChessGameEntity.setMoves(chessGameEntity.getMoves());
        } else {
            if(chessGameEntity.getMoves().size() > 0) {
                updatedChessGameEntity.getMoves().add(chessGameEntity.getMoves().get(0));
            }
        }
        System.err.println("DEBUG SIZE OF MOVES: " + updatedChessGameEntity.getMoves().size());
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
