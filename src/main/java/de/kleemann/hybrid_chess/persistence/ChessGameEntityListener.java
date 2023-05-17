package de.kleemann.hybrid_chess.persistence;

import de.kleemann.hybrid_chess.persistence.entities.ChessGameEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@Component
public class ChessGameEntityListener extends AbstractMongoEventListener<ChessGameEntity> {

    private SequenceGeneratorService sequenceGenerator;

    @Autowired
    public ChessGameEntityListener(SequenceGeneratorService sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<ChessGameEntity> event) {
        if(event.getSource().getId() == null) {
            event.getSource().setId(sequenceGenerator.generateSequence(ChessGameEntity.SEQUENCE_NAME));
            return;
        }
        if (event.getSource().getId() < 1) {
            event.getSource().setId(sequenceGenerator.generateSequence(ChessGameEntity.SEQUENCE_NAME));
        }
    }

}
