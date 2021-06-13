package com.websocket.web_socket.domain;

import com.websocket.web_socket.socket.SubscriptionHub;
import com.websocket.web_socket.util.JsonUtils;
import org.springframework.stereotype.Component;

import javax.smartcardio.Card;
import java.util.HashMap;
import java.util.Map;

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

        SubscriptionHub.send("/clock/", JsonUtils.toJson(clockData));
    }
}
