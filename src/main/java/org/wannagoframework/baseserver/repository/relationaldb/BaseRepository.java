package org.wannagoframework.baseserver.repository.relationaldb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.wannagoframework.baseserver.domain.relationaldb.BaseEntity;

/**
 * @author WannaGo Dev1.
 * @version 1.0
 * @since 3/8/20
 */
public interface BaseRepository<T extends BaseEntity> extends JpaRepository<T, Long> {

}