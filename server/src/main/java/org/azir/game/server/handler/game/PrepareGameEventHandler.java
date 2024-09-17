package org.azir.game.server.handler.game;

import lombok.extern.slf4j.Slf4j;
import org.azir.game.common.event.client.PrepareGameEvent;
import org.azir.game.common.handler.AbstractGameEventHandler;
import org.azir.game.common.handler.EventHandlerContext;

/**
 * 处理客户端准备游戏事件
 *
 * @author zhangshukun
 * @since 2024/9/9
 */
@Slf4j
public class PrepareGameEventHandler extends AbstractGameEventHandler<PrepareGameEvent> {

    @Override
    public void handle(EventHandlerContext<PrepareGameEvent> eventHandlerContext) {

    }
}
