package com.example.webSocketNotifications.webSocketNotifications.service;

import com.example.webSocketNotifications.webSocketNotifications.dto.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
//@EnableScheduling
public class WSService {

  private final SimpMessagingTemplate messagingTemplate;

  @Autowired
  public WSService(SimpMessagingTemplate messagingTemplate) {
    this.messagingTemplate = messagingTemplate;
  }

  //  @Scheduled(fixedDelay = 5000)
  public void sendMessages(final String message) {
    ResponseMessage responseMessage = new ResponseMessage(message);
    messagingTemplate.convertAndSend("/topic/messages",
        responseMessage);
  }
}
