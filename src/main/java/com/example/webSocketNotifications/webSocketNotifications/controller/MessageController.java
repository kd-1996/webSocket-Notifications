package com.example.webSocketNotifications.webSocketNotifications.controller;

import com.example.webSocketNotifications.webSocketNotifications.dto.Message;
import com.example.webSocketNotifications.webSocketNotifications.dto.ResponseMessage;
import java.security.Principal;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

  /**
   * read about html escape as well
   */
  @MessageMapping("/message")
  @SendTo("/topic/messages")
  public ResponseMessage getMessage(final Message message) throws InterruptedException {
    Thread.sleep(1000);
    return new ResponseMessage("Hi " + message.getMessageContent());
  }

  @MessageMapping("/private-message")
  @SendToUser("/topic/private-messages")
  public ResponseMessage getPrivateMessage(final Message message, final Principal principal)
      throws InterruptedException {
    Thread.sleep(1000);
    return new ResponseMessage(
        "Sending private Message " + principal.getName()+ ": "+ message.getMessageContent());
  }
}
