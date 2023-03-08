package com.example.webSocketNotifications.webSocketNotifications.service;

import com.example.webSocketNotifications.webSocketNotifications.dto.ResponseMessage;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
private final SimpMessagingTemplate messagingTemplate;

  public NotificationService(SimpMessagingTemplate messagingTemplate) {
    this.messagingTemplate = messagingTemplate;
  }
   public void sendGlobalNotifications(){
     ResponseMessage globalNotifications = new ResponseMessage("Global Notifications");
     messagingTemplate.convertAndSend("");
   }
}
