package com.websocket.web_socket.socket.handlers;

import com.websocket.web_socket.socket.*;
import com.websocket.web_socket.socket.handlers.anotation.Action;
import com.websocket.web_socket.socket.handlers.anotation.ChannelHandler;
import com.websocket.web_socket.socket.handlers.anotation.ChannelValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ChannelHandler("/board/*")
public class BoardChannelHandler {

  private static final Logger log = LoggerFactory.getLogger(BoardChannelHandler.class);

  @Action("subscribe")
  public void subscribe(RealTimeSession session, @ChannelValue String channel) {
    log.debug("RealTimeSession[{}] Subscribe to channel `{}`", session.id(), channel);
    SubscriptionHub.subscribe(session, channel);
  }

  @Action("unsubscribe")
  public void unsubscribe(RealTimeSession session, @ChannelValue String channel) {
    log.debug("RealTimeSession[{}] Unsubscribe from channel `{}`", session.id(), channel);
    SubscriptionHub.unsubscribe(session, channel);
  }
}
