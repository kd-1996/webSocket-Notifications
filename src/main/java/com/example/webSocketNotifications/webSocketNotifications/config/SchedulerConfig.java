package com.example.webSocketNotifications.webSocketNotifications.config;

import com.example.webSocketNotifications.webSocketNotifications.dto.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class SchedulerConfig {

  /**
   * This scheduler is meant to send a messages to two topics synchronously and two different clients
   * are listening to these topics.
   * Here Topics are distinguished with employeeIds.
   */

  Integer userId;
  @Autowired
  private SimpMessagingTemplate messagingTemplate;

  @Scheduled(fixedDelay = 5000)
  public void sendNotifications() throws InterruptedException {
    userId = 475;
    Message message1 = new Message();
    message1.setMessageContent("This is for user" + userId);
    String destinationQueue1 = "/topic/messages/" + userId.toString();
    messagingTemplate.convertAndSend(destinationQueue1,
        message1);
    Thread.sleep(1000);
    userId = 476;
    Message message2 = new Message();
    message2.setMessageContent("This is for user" + userId);
    String destinationQueue2 = "/topic/messages/" + userId.toString();
    messagingTemplate.convertAndSend(destinationQueue2,
        message2);
  }
}
