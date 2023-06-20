package de.kleemann.hybrid_chess.persistence.entities;

import de.kleemann.hybrid_chess.persistence.entities.ChessGameEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChessRepository extends MongoRepository<ChessGameEntity, Integer> {

    public Optional<ChessGameEntity> findFirstByOrderByIdDesc();
}
