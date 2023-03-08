package com.example.webSocketNotifications.webSocketNotifications.controller;

import com.example.webSocketNotifications.webSocketNotifications.dto.Message;
import com.example.webSocketNotifications.webSocketNotifications.dto.ResponseMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
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
}
