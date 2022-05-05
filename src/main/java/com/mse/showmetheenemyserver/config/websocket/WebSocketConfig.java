package com.mse.showmetheenemyserver.config.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) { // Handshake와 통신을 담당할 endpoint 지정, 클라이언트에서 웹 소켓 연결할 때 사용하는 Endpoint
        registry.addEndpoint("/ws-gameplay")
                .setAllowedOriginPatterns("*")
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) { // Application 내부에서 사용할 path 지정
        registry.enableSimpleBroker("/sub"); // /sub으로 시작하는 주소의 subscriber들에게 메시지 전달 역할을 하는 메시지 브로커 등록
        registry.setApplicationDestinationPrefixes("/pub"); // 클라이언트가 서버로 메시지 보낼 때 붙여야 하는 prefix 지정
    }
}
