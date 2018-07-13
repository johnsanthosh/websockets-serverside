package com.john.websockets;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketStompConfig extends AbstractWebSocketMessageBrokerConfigurer {

/**
 * Registers the stomp endpoints.
 * @param registry
 */
  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
    // TODO update the wildcard in setAllowedOrigins.
    registry.addEndpoint("/marcopolo").setAllowedOrigins("*").withSockJS();
  }

/**
 * Enables message brokers for different STOMP channels.
 * @param registry
 */
  @Override
  public void configureMessageBroker(MessageBrokerRegistry registry) {
    // Enables /topic and /queue channels so that each message with destination as /queue/.* or /topic/.*
    // gets directed to the SimpleBrokerMessageHandler.
    registry.enableSimpleBroker("/queue", "/topic");

    // Enables the /app channel so that each message with destination set to /app/.* gets directed
    // to the AnnotationMethodMessageHandler i.e methods with @MessageMapping or @SubscribeMapping in the Controller.
    registry.setApplicationDestinationPrefixes("/app");
  }
  
}
