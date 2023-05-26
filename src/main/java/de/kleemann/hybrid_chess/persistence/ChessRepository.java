package de.kleemann.hybrid_chess.persistence;

import de.kleemann.hybrid_chess.persistence.entities.ChessGameEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChessRepository extends MongoRepository<ChessGameEntity, Integer> {


}
