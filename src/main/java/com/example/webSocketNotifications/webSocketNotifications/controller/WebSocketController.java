package com.example.webSocketNotifications.webSocketNotifications.controller;

import com.example.webSocketNotifications.webSocketNotifications.dto.Message;
import com.example.webSocketNotifications.webSocketNotifications.service.WSService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class WebSocketController {

  /**
   * This is called from postman, and we can see our message from frontend as our service is sending
   * messages to /topic/messages and our client is subscribed to that topic. Also in WSService we
   * can implement a Scheduler which sends out messages to /topic/messages at some fixed delay.
   */

  @Autowired
  private WSService service;

  @PostMapping("/send-message")
  public void sendMessage(@RequestBody Message message) {
    log.info("Inside controller");
    service.sendMessages(message.getMessageContent());
  }

  @PostMapping("/sendPrivate-message/{userId}")
  public void sendPrivateMessage(@PathVariable final String userId, @RequestBody Message message) {
    service.sendPrivateMessages(userId, message.getMessageContent());
  }
}
