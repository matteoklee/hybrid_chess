package de.kleemann.hybrid_chess.persistence;

import de.kleemann.hybrid_chess.core.game.ChessGame;
import de.kleemann.hybrid_chess.persistence.documents.ChessGameDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChessRepository extends MongoRepository<ChessGame, Integer> {


}
