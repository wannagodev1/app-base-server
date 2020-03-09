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

package org.wannagoframework.baseserver.client;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;
import org.wannagoframework.dto.domain.notification.Mail;
import org.wannagoframework.dto.domain.notification.Sms;

/**
 * @author Alexandre Clavaud.
 * @version 1.0
 * @since 2019-07-02
 */
@Component
public class NotificationSenderQueue {

  @Autowired
  JmsTemplate jmsTemplate;

  public void sendMail(final Mail mailMessage) {
    jmsTemplate.send("mailbox", new MessageCreator() {
      @Override
      public Message createMessage(Session session) throws JMSException {
        ObjectMessage objectMessage = session.createObjectMessage(mailMessage);
        return objectMessage;
      }
    });
  }

  public void sendSms(final Sms smsMessage) {
    jmsTemplate.send("sms", new MessageCreator() {
      @Override
      public Message createMessage(Session session) throws JMSException {
        ObjectMessage objectMessage = session.createObjectMessage(smsMessage);
        return objectMessage;
      }
    });
  }
}
