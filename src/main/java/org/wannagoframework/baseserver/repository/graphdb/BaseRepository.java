package org.wannagoframework.baseserver.repository.graphdb;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.wannagoframework.baseserver.domain.graphdb.BaseEntity;

/**
 * @author WannaGo Dev1.
 * @version 1.0
 * @since 3/8/20
 */
public interface BaseRepository<T extends BaseEntity> extends Neo4jRepository<T, Long>{

}