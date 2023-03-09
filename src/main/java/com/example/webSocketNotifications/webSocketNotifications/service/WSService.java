package com.example.webSocketNotifications.webSocketNotifications.service;

import com.example.webSocketNotifications.webSocketNotifications.dto.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class WSService {

  private final SimpMessagingTemplate messagingTemplate;

  @Autowired
  public WSService(SimpMessagingTemplate messagingTemplate) {
    this.messagingTemplate = messagingTemplate;
  }

  public void sendMessages(final String message) {
    log.info("Inside service");
    ResponseMessage responseMessage = new ResponseMessage(message);
    String destinationQueue = "/topic/messages/";
    messagingTemplate.convertAndSend(destinationQueue,
        responseMessage);
    log.info("Inside service : - Sent to topic");
  }

  public void sendPrivateMessages(final String Id, final String message) {
    ResponseMessage responseMessage = new ResponseMessage(message);
    messagingTemplate.convertAndSendToUser(Id, "/topic/private-messages",
        responseMessage);
  }
}
