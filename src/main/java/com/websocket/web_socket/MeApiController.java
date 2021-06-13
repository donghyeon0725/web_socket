package com.websocket.web_socket;

import com.websocket.web_socket.model.UserId;
import com.websocket.web_socket.security.TokenManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 소켓 통신 연결에 사용할
 * 토큰과 url을 발급합니다.
 * */
@Controller
public class MeApiController {
    private String realTimeServerUrl = "/rt";
    private TokenManager tokenManager;

    public MeApiController(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    @GetMapping("/api/me")
    public ResponseEntity getMyData() {
        // @CurrentUser SimpleUser currentUser => 이 어노테이션을 통해, 세션에 있는 유저를 바로 가져올 수 있는데, 우리는 stateless 이므로 발근 한 토큰을 인증 정보로 사용한다.
        UserId userId = new UserId(12341L); // 실제로 사용할 때는, 사용자가 로그인을 할 때 이 정보를 발급하면 될 것 같다.
        String realTimeToken = tokenManager.jwt(userId);
        return new ResponseEntity(
                Result.builder().realTimeServerUrl(realTimeServerUrl).realTimeToken(realTimeToken).build()
                , null
                , HttpStatus.OK
        );
    }
}
