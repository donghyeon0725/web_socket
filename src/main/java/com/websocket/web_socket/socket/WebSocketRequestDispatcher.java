package com.websocket.web_socket.socket;

import com.websocket.web_socket.model.UserId;
import com.websocket.web_socket.security.TokenManager;
import com.websocket.web_socket.util.JsonUtils;
import io.jsonwebtoken.JwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * TextWebSocketHandler 를 상속 받음으로써,
 * 소켓 요청이 들어 왔을 때
 * 해당 요청을 받아서 처리해 줄 수 있는 가장 앞에 있는 컨트롤러
 * */
@Component
public class WebSocketRequestDispatcher extends TextWebSocketHandler {

  private static final Logger log = LoggerFactory.getLogger(WebSocketRequestDispatcher.class);

  private TokenManager tokenManager;
  private ChannelHandlerResolver channelHandlerResolver;

  public WebSocketRequestDispatcher(TokenManager tokenManager,
                                    ChannelHandlerResolver channelHandlerResolver) {
    this.tokenManager = tokenManager;
    this.channelHandlerResolver = channelHandlerResolver;
  }

  /**
   * 초기화 단계로, 서버와 사용자가 연결된 경우
   * 사용자 토큰의 유효성을 검사하고 사용자에게 연결이 되었음을 알립니다.
   * */
  @Override
  public void afterConnectionEstablished(WebSocketSession webSocketSession) {
    log.debug("WebSocket connection established");
    RealTimeSession session = new RealTimeSession(webSocketSession);
    String token = session.getToken();

    try {
      // 세션에서 토큰을 검출하고 유효성 검사를 거친 뒤, 유효하다면 세션에 연결이 되었다고 알립니다.
      UserId userId = tokenManager.verifyJwt(token);
      session.setUserId(userId);
      session.reply("authenticated");
    } catch (JwtException exception) {
      log.debug("Invalid JWT token value: {}", token);
      session.fail("authentication failed");
    }
  }

  /**
   * 사용자가 메세지를 보내온 순간
   * 적절한 핸들러를 찾아서, action 에 해당하는 동작을 수행 합니다.
   * */
  @Override
  protected void handleTextMessage(WebSocketSession webSocketSession, TextMessage message) {
    RealTimeSession session = new RealTimeSession(webSocketSession);
    log.debug("RealTimeSession[{}] Received message `{}`", session.id(), message.getPayload());

    IncomingMessage incomingMessage = JsonUtils.toObject(message.getPayload(), IncomingMessage.class);
    if (incomingMessage == null) {
      session.error("Illegal format of incoming message: " + message.getPayload());
      return;
    }

    ChannelHandlerInvoker invoker = channelHandlerResolver.findInvoker(incomingMessage);
    if (invoker == null) {
      String errorMessage = "No handler found for action `" + incomingMessage.getAction() +
        "` at channel `" + incomingMessage.getChannel() + "`";
      session.error(errorMessage);
      log.error("RealTimeSession[{}] {}", session.id(), errorMessage);
      return;
    }

    invoker.handle(incomingMessage, session);
  }

  /**
   * 사용자의 연결이 끊긴 후
   * 세션을 제거합니다.
   * */
  @Override
  public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus status) {
    RealTimeSession session = new RealTimeSession(webSocketSession);
    SubscriptionHub.unsubscribeAll(session);
    log.debug("RealTimeSession[{}] Unsubscribed all channels after disconnecting", session.id());
  }
}
