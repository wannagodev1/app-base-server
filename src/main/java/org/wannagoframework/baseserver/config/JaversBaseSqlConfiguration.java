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

package org.wannagoframework.baseserver.config;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.javers.spring.auditable.AuthorProvider;
import org.javers.spring.auditable.SpringSecurityAuthorProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.wannagoframework.commons.security.SecurityUtils;

/**
 * @author Alexandre Clavaud.
 * @version 1.0
 * @since 20/04/2020
 */
@Configuration
public class JaversBaseSqlConfiguration {
  @Bean
  public AuthorProvider authorProvider() {
    return () -> {
      String currentUsername = "Unknown";
      try {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
          HttpServletRequest servletRequest = ((ServletRequestAttributes) requestAttributes)
              .getRequest();
          currentUsername = servletRequest.getHeader("X-SecUsername");
        } else {
          currentUsername = SecurityUtils.getUsername();
        }
      } catch (IllegalStateException e) {
      }
      return currentUsername;
    };
  }
}
