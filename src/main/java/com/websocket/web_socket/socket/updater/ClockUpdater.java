package com.websocket.web_socket.socket.updater;

import com.websocket.web_socket.domain.Clock;
import com.websocket.web_socket.socket.SubscriptionHub;
import com.websocket.web_socket.util.JsonUtils;
import org.springframework.stereotype.Component;

import javax.smartcardio.Card;
import java.util.HashMap;
import java.util.Map;

/**
 * 구독 허브를 통해서, 원하는 채널에 원하는 메세지를 보냅니다.
 * */
@Component
public class ClockUpdater {
    public void onClockAdded(Integer clockId) {
        Map<String, Object> cardData = new HashMap<>();
        cardData.put("id", clockId);

        SubscriptionHub.send("/clock/", JsonUtils.toJson(cardData));
    }

    public void onClockDeleted(Clock clock) {
        Map<String, Object> clockData = new HashMap<>();
        clockData.put("id", clock.getId());
        clockData.put("type", "delete");

        SubscriptionHub.send("/clock/", JsonUtils.toJson(clockData));
    }
}
