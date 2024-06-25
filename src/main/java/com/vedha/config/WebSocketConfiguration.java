package com.vedha.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {

        // Registering the endpoint for the websocket connection
        registry.addEndpoint("/ws") // Endpoint for the websocket connection used by the client to connect to the server
                .withSockJS(); // SockJS is used to enable fallback options for browsers that don't support websocket
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

        // Configuring the message broker
        registry.enableSimpleBroker("/topic"); // Enables a simple in-memory broker for the server to send messages to the client on destinations prefixed with "/topic", subscribe to the topic to receive messages
        registry.setApplicationDestinationPrefixes("/app"); // Prefix for messages that are bound for methods annotated with @MessageMapping in the controller and sent to the broker(topic)
    }
}
