package com.websocket.web_socket.model;

import lombok.Data;

@Data
public class User {
    private UserId id;
    private String username;
    private String password;
}
