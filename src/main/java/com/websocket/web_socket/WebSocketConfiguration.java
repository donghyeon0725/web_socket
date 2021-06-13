package com.websocket.web_socket;

import com.websocket.web_socket.socket.WebSocketRequestDispatcher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * 이 설정이 있어야, 소켓의 요청을 받을 수 있다.
 * */
@Configuration
@EnableWebSocket
public class WebSocketConfiguration implements WebSocketConfigurer {

    private WebSocketRequestDispatcher requestDispatcher;

    public WebSocketConfiguration(WebSocketRequestDispatcher requestDispatcher) {
        this.requestDispatcher = requestDispatcher;
    }

    /**
     * /rt로 온 요청을, origin 허용을 하고
     * Socket 통신으로 만들고, 해당 요청을 requestDispatcher 클래스에서 처리합니다.
     * */
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(requestDispatcher, "/rt").setAllowedOrigins("http://localhost:3000").withSockJS();
    }
}
