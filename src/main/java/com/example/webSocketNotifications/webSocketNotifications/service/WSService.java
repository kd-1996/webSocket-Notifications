package com.example.webSocketNotifications.webSocketNotifications.service;

import com.example.webSocketNotifications.webSocketNotifications.dto.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class WSService {

  private final SimpMessagingTemplate messagingTemplate;

  @Autowired
  public WSService(SimpMessagingTemplate messagingTemplate) {
    this.messagingTemplate = messagingTemplate;
  }

  public void sendMessages(final String message) {
    ResponseMessage responseMessage = new ResponseMessage(message);
    String destinationQueue = "/topic/messages/";
    messagingTemplate.convertAndSend(destinationQueue,
        responseMessage);
  }

  public void sendPrivateMessages(final String Id, final String message) {
    ResponseMessage responseMessage = new ResponseMessage(message);
    messagingTemplate.convertAndSendToUser(Id, "/topic/private-messages",
        responseMessage);
  }
}
