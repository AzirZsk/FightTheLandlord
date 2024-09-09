package org.azir.game.server.handler;

import lombok.extern.slf4j.Slf4j;
import org.azir.game.common.event.client.LoginGameEvent;
import org.azir.game.common.handler.AbstractGameEventHandler;

/**
 * @author zhangshukun
 * @since 2024/09/09
 */
@Slf4j
public class LoginGameEventHandler extends AbstractGameEventHandler<LoginGameEvent> {

    @Override
    public void handle(LoginGameEvent event) {
        log.info("登录游戏事件:{}", event);
    }
}
