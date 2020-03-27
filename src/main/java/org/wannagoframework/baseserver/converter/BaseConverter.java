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


package org.wannagoframework.baseserver.converter;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;
import org.wannagoframework.baseserver.domain.graphdb.Comment;
import org.wannagoframework.commons.utils.OrikaBeanMapper;
import org.wannagoframework.dto.domain.BaseEntity;
import org.wannagoframework.dto.domain.EntityTranslation;
import org.wannagoframework.dto.utils.Page;
import org.wannagoframework.dto.utils.StoredFile;

/**
 * @author WannaGo Dev1.
 * @version 1.0
 * @since 2019-06-05
 */
@Component
public class BaseConverter {

  private final OrikaBeanMapper orikaBeanMapper;

  public BaseConverter(OrikaBeanMapper orikaBeanMapper) {
    this.orikaBeanMapper = orikaBeanMapper;
  }

  @Bean
  public void baseConverters() {
    orikaBeanMapper.addMapper(Comment.class, org.wannagoframework.dto.domain.Comment.class);
    orikaBeanMapper.addMapper(org.wannagoframework.dto.domain.Comment.class, Comment.class);

    orikaBeanMapper
        .addMapper(EntityTranslation.class, EntityTranslation.class);
    orikaBeanMapper
        .addMapper(EntityTranslation.class, EntityTranslation.class);

    orikaBeanMapper
        .addMapper(org.wannagoframework.baseserver.domain.graphdb.BaseEntity.class,
            BaseEntity.class);
    orikaBeanMapper
        .addMapper(org.wannagoframework.baseserver.domain.nosqldb.BaseEntity.class,
            BaseEntity.class);
    orikaBeanMapper
        .addMapper(org.wannagoframework.baseserver.domain.relationaldb.BaseEntity.class, BaseEntity.class);

    orikaBeanMapper
        .getClassMapBuilder(BaseEntity.class, org.wannagoframework.baseserver.domain.graphdb.BaseEntity.class)
        .byDefault().customize(
        new CustomMapper<BaseEntity, org.wannagoframework.baseserver.domain.graphdb.BaseEntity>() {
          @Override
          public void mapAtoB(BaseEntity a, org.wannagoframework.baseserver.domain.graphdb.BaseEntity b,
              MappingContext context) {
            if (a.getIsNew()) {
              b.setId(null);
            }
          }
        }).register();

    orikaBeanMapper
        .getClassMapBuilder(BaseEntity.class, org.wannagoframework.baseserver.domain.nosqldb.BaseEntity.class)
        .byDefault().customize(
        new CustomMapper<BaseEntity, org.wannagoframework.baseserver.domain.nosqldb.BaseEntity>() {
          @Override
          public void mapAtoB(BaseEntity a,
              org.wannagoframework.baseserver.domain.nosqldb.BaseEntity b,
              MappingContext context) {
            if (a.getIsNew()) {
              b.setId(null);
            }
          }
        }).register();

    orikaBeanMapper
        .getClassMapBuilder(BaseEntity.class,
            org.wannagoframework.baseserver.domain.relationaldb.BaseEntity.class)
        .byDefault().customize(
        new CustomMapper<BaseEntity, org.wannagoframework.baseserver.domain.relationaldb.BaseEntity>() {
          @Override
          public void mapAtoB(BaseEntity a,
              org.wannagoframework.baseserver.domain.relationaldb.BaseEntity b,
              MappingContext context) {
            if (a.getIsNew()) {
              b.setId(null);
            }
          }
        }).register();

    orikaBeanMapper.addMapper(PageImpl.class, Page.class);
    orikaBeanMapper.addMapper(Page.class, PageImpl.class);
    
    orikaBeanMapper.addMapper(StoredFile.class, StoredFile.class);
  }
}