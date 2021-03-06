package com.websocket.web_socket.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Clock {
    private Integer id;
    private String name;
    private String description;
    private Integer price;
}
