package com.websocket.web_socket;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Result {
    private String realTimeServerUrl;
    private String realTimeToken;
}
