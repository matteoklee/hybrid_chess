package de.kleemann.hybrid_chess.persistence;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EntityScan("de.kleemann.hybrid_chess.persistence")
@EnableTransactionManagement
//@EnableJpaRepositories("de.kleemann.hybrid_chess.persistence")
@EnableMongoRepositories
public class PersistenceConfiguration {
}
