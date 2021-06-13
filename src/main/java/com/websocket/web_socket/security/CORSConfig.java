package com.websocket.web_socket.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CORSConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                // 웹 소켓 처럼, 통신 할 때마다 인증 정보를 주고 보내는 경우, Origin을 모두 허용인(* = asterisk)를 사용할 수 없다.
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("*");
    }
}
