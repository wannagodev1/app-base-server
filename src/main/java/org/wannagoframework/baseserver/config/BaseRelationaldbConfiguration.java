/*
 * This file is part of the WannaGo distribution (https://github.com/wannago).
 * Copyright (c) [2019] - [2020].
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */


package org.wannagoframework.baseserver.config;

import javax.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.JpaTransactionManager;

// @Configuration
// @EnableJpaRepositories(basePackages = "org.wannagoframework.backend.repository.relationaldb", transactionManagerRef = "relationaldbTransactionManager")
// @EnableJpaAuditing(auditorAwareRef = "springSecurityAuditorAware")
// @EntityScan("org.wannagoframework.backend.domain.relationaldb")
// @EnableTransactionManagement
public class BaseRelationaldbConfiguration {
  @Bean(name = "relationaldbTransactionManager")
  public JpaTransactionManager relationaldbTransactionManager(EntityManagerFactory entityManagerFactory) {
    return new JpaTransactionManager(entityManagerFactory);
  }
}
