package Arthub.config;

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
        // Định nghĩa endpoint cho client kết nối, hỗ trợ SockJS nếu cần
        registry.addEndpoint("/ws").setAllowedOrigins("http://localhost:3000")
                .setAllowedOriginPatterns("*")
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // Thiết lập broker để gửi thông báo về client thông qua topic
        registry.enableSimpleBroker("/topic");
        // Định nghĩa tiền tố cho các message gửi từ client lên server (nếu cần)
        registry.setApplicationDestinationPrefixes("/app");
    }
}
