package org.wannagoframework.baseserver.repository.nosqldb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.wannagoframework.baseserver.domain.nosqldb.BaseEntity;

/**
 * @author WannaGo Dev1.
 * @version 1.0
 * @since 3/8/20
 */
public interface BaseRepository<T extends BaseEntity> extends MongoRepository<T, String> {

}