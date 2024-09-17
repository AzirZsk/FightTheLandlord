package org.azir.game.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import lombok.extern.slf4j.Slf4j;
import org.azir.game.common.event.HeartbeatEvent;
import org.azir.game.common.handler.AbstractGameEventHandler;
import org.azir.game.common.handler.EventHandlerContext;

/**
 * @author zhangshukun
 * @since 2024/9/11
 */
@Slf4j
@ChannelHandler.Sharable
public class HeartbeatEventHandler extends AbstractGameEventHandler<HeartbeatEvent> {

    @Override
    public void handle(EventHandlerContext<HeartbeatEvent> eventHandlerContext) {
        HeartbeatEvent event = eventHandlerContext.getEvent();
        log.info("接受到心跳事件：{}", event);
    }
}
