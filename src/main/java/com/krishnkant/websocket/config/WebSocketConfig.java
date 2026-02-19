package com.krishnkant.websocket.config;

import com.krishnkant.websocket.util.WebSocketConstants;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

        @Override
        public void registerStompEndpoints(StompEndpointRegistry registry) {
            registry.addEndpoint(WebSocketConstants.ENDPOINT_WS)
                    .setAllowedOriginPatterns("*")
                    .withSockJS();
        }

        @Override
        public void configureMessageBroker(MessageBrokerRegistry registry) {
            registry.setApplicationDestinationPrefixes(WebSocketConstants.APP_PREFIX);

            // Yahan change hai: Relay hata diya, simple broker use karenge
            // Kyunki hum Kafka se data le kar manually '/topic' pe bhejenge
            registry.enableSimpleBroker("/topic");
        }
}
