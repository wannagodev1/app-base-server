/*
 *   ilem group CONFIDENTIAL
 *    __________________
 *
 *    [2019] ilem Group
 *    All Rights Reserved.
 *
 *    NOTICE:  All information contained herein is, and remains the property of "ilem Group"
 *    and its suppliers, if any. The intellectual and technical concepts contained herein are
 *    proprietary to "ilem Group" and its suppliers and may be covered by Morocco, Switzerland and Foreign
 *    Patents, patents in process, and are protected by trade secret or copyright law.
 *    Dissemination of this information or reproduction of this material is strictly forbidden unless
 *    prior written permission is obtained from "ilem Group".
 */

package org.wannagoframework.baseserver.endpoint;

import org.wannagoframework.baseserver.service.AuditLogService;
import org.wannagoframework.dto.domain.audit.AuditLog;
import org.wannagoframework.dto.serviceQuery.auditLog.CountAuditLogQuery;
import org.wannagoframework.dto.serviceQuery.auditLog.FindAuditLogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wannagoframework.commons.endpoint.BaseEndpoint;
import org.wannagoframework.commons.utils.OrikaBeanMapper;
import org.wannagoframework.dto.serviceQuery.ServiceResult;

/**
 * @author Alexandre Clavaud.
 * @version 1.0
 * @since 2019-06-05
 */
@RestController
@RequestMapping("/auditLogService")
public class AuditLogServiceEndpoint extends BaseEndpoint {

  private final AuditLogService auditLogService;

  public AuditLogServiceEndpoint(AuditLogService auditLogService,
      OrikaBeanMapper mapperFacade) {
    super(mapperFacade);
    this.auditLogService = auditLogService;
  }

  @PostMapping(value = "/getAuditLog")
  public ResponseEntity<ServiceResult> getAuditLog(
      @RequestBody FindAuditLogQuery query) {
    String loggerPrefix = getLoggerPrefix("getAuditLog");
    try {
      Page<AuditLog> result = auditLogService
          .getAudit(query.getClassName(), query.getRecordId(),
              mapperFacade.map(query.getPageable(),
                  Pageable.class));
      return handleResult(loggerPrefix, mapperFacade
          .map(result, org.wannagoframework.dto.utils.Page.class, getOrikaContext(query)));
    } catch (Throwable t) {
      return handleResult(loggerPrefix, t);
    }
  }

  @PostMapping(value = "/countAuditLog")
  public ResponseEntity<ServiceResult> countAuditLog(
      @RequestBody CountAuditLogQuery query) {
    String loggerPrefix = getLoggerPrefix("countAuditLog");
    try {
      return handleResult(loggerPrefix,
          auditLogService
              .countAudit(query.getClassName(), query.getRecordId()));
    } catch (Throwable t) {
      return handleResult(loggerPrefix, t);
    }
  }
}