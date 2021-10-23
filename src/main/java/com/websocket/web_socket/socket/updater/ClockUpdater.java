package com.websocket.web_socket.socket.updater;

import com.websocket.web_socket.domain.Clock;
import com.websocket.web_socket.socket.SubscriptionHub;
import com.websocket.web_socket.socket.message.Event;
import com.websocket.web_socket.util.JsonUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 구독 허브를 통해서, 원하는 채널에 원하는 메세지를 보냅니다.
 * */
@Component
public class ClockUpdater {
    public void onClockAdded(Clock clock) {
        Map<String, Object> cardData = new HashMap<>();
        cardData.put("id", clock.getId());
        // 이 부분은 WebSocketMessages.java 메세지를 통해서 보내는 것이 더 나은 방법으로 보임
        SubscriptionHub.send("/clock/", Event.CREATE, JsonUtils.toJson(cardData));
    }

    public void onClockDeleted(Clock clock) {
        Map<String, Object> clockData = new HashMap<>();
        clockData.put("id", clock.getId());
        // 이 부분은 WebSocketMessages.java 메세지를 통해서 보내는 것이 더 나은 방법으로 보임
        SubscriptionHub.send("/clock/", Event.DELETE, JsonUtils.toJson(clockData));
    }
}
