package com.example.webSocketNotifications.webSocketNotifications.config;

import com.example.webSocketNotifications.webSocketNotifications.handler.StompHandShakeInterceptor;
import com.example.webSocketNotifications.webSocketNotifications.handler.UserHandShakeHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.adapters.springboot.KeycloakSpringBootProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
@RequiredArgsConstructor
@Slf4j
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

  private final KeycloakSpringBootProperties configuration;

  @Override
  public void registerStompEndpoints(final StompEndpointRegistry registry) {
    log.info("registerStompEndpoints  ");
    registry.addEndpoint("/our-websocket")
        .setAllowedOriginPatterns("*")
        .addInterceptors(new StompHandShakeInterceptor(configuration))
        .withSockJS();
  }

  @Override
  public void configureMessageBroker(final MessageBrokerRegistry registry) {
    log.info("configureMessageBroker  ");
    registry.enableSimpleBroker("/topic");
    registry.setApplicationDestinationPrefixes("/ws");
  }
}
