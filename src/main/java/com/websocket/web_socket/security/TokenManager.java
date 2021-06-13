package com.websocket.web_socket.security;

import com.websocket.web_socket.model.UserId;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 아이디를 통해서 토큰을 발급하고,
 * 해당 토큰에서 아이디를 추출해줍니다.
 * */
import java.security.Key;
@Component
public class TokenManager {

    private Key secretKey;

    public TokenManager() {
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode("60dKuW2Qpc3YkUoaa9i6qY5cyaGgQM8clfxpDGWS3sY="));
    }

    /**
     * Generate a JWT with user's id as its subject
     *
     * @param userId the id of the user
     * @return a JWT value
     */
    public String jwt(UserId userId) {
        return Jwts.builder()
                .setSubject(String.valueOf(userId.value()))
                .signWith(secretKey).compact();
    }

    /**
     * Get user id out of a JWT value
     *
     * @param jws the jwt string
     * @return user id
     */
    public UserId verifyJwt(String jws) {
        String userIdValue = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jws).getBody().getSubject();
        return new UserId(Long.valueOf(userIdValue));
    }
}
