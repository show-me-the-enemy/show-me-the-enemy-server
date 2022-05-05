package com.mse.showmetheenemyserver.config.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@EnableWebSocketMessageBroker
@Configuration
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) { // handshake와 통신을 담당할 endpoint 지정
        registry.addEndpoint("/play")
                .setAllowedOrigins("*")
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) { // Application 내부에서 사용할 path 지정
        registry.setApplicationDestinationPrefixes("/pub"); // Client의 Send 요청 처리 prefix
        registry.enableSimpleBroker("/sub"); // 해당 경로로 SimpleBroker를 등록한다. SimpleBroker는 해당하는 경로를 구독하는 client에게 메시지를 전달한다.
    }
}
