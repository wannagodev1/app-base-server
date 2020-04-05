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

package org.wannagoframework.baseserver.utils;

import java.util.List;
import org.springframework.stereotype.Component;
import org.wannagoframework.baseserver.client.SecurityUserService;
import org.wannagoframework.dto.domain.security.SecurityRole;
import org.wannagoframework.dto.domain.security.SecurityUser;
import org.wannagoframework.dto.serviceQuery.ServiceResult;
import org.wannagoframework.dto.serviceQuery.generic.GetByStrIdQuery;
import org.wannagoframework.dto.utils.AppContextThread;

/**
 * @author Alexandre Clavaud.
 * @version 1.0
 * @since 05/04/2020
 */
@Component
public class SecurityUserUtils {
private static ThreadLocal<SecurityUser> currentSecurityUser = new ThreadLocal<>();

private final SecurityUserService securityUserService;

  public SecurityUserUtils(
      SecurityUserService securityUserService) {
    this.securityUserService = securityUserService;
  }

  public SecurityUser getCurrentSecurityUser() {
    String currentSecurityUserId = AppContextThread.getCurrentSecurityUserId();
    ServiceResult<SecurityUser> securityUserServiceResult = securityUserService.getById( new GetByStrIdQuery( currentSecurityUserId ));
    if ( securityUserServiceResult.getIsSuccess() && securityUserServiceResult.getData() != null ) {
        return securityUserServiceResult.getData();
    } else {
      return null;
    }
}

public boolean hasRole( String securityRole ) {
    if (  getCurrentSecurityUser() != null ) {
      SecurityUser securityUser = getCurrentSecurityUser();
      for (SecurityRole securityRole1 : securityUser.getRoles()) {
        if ( securityRole1.getName().equalsIgnoreCase( securityRole))
          return true;
      }
    }
    return false;
}
}
