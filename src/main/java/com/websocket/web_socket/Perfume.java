package com.websocket.web_socket;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Perfume {
    private Integer id;
    private String name;
    private String description;
    private Integer price;
}
