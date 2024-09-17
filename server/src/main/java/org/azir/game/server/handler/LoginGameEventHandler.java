package org.azir.game.server.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.azir.game.common.event.client.LoginGameEvent;
import org.azir.game.common.event.server.LoginResultEvent;
import org.azir.game.common.handler.AbstractGameEventHandler;
import org.azir.game.common.handler.EventHandlerContext;
import org.azir.game.server.manager.user.UserManager;

/**
 * @author zhangshukun
 * @since 2024/09/09
 */
@Slf4j
@RequiredArgsConstructor
public class LoginGameEventHandler extends AbstractGameEventHandler<LoginGameEvent> {

    private final UserManager userManager;

    @Override
    public void handle(EventHandlerContext<LoginGameEvent> eventHandlerContext) {
        log.info("接收到登录事件：{}", eventHandlerContext);
        LoginGameEvent loginGameEvent = eventHandlerContext.getEvent();
        log.info("登录事件的账号：{}", loginGameEvent.getAccount());
        String loginResult = userManager.login(loginGameEvent.getAccount(), loginGameEvent.getPassword());
        boolean isSuccess = "".equals(loginResult);
        log.info("登录结果：{}", isSuccess);
        LoginResultEvent loginResultEvent = new LoginResultEvent(isSuccess, loginResult);
        eventHandlerContext.sendEvent(loginResultEvent);
    }
}
