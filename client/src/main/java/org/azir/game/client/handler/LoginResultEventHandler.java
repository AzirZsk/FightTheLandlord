package org.azir.game.client.handler;

import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.azir.game.client.utils.IOUtils;
import org.azir.game.common.event.server.LoginResultEvent;
import org.azir.game.common.handler.AbstractGameEventHandler;
import org.azir.game.common.handler.EventHandlerContext;

/**
 * @author zhangshukun
 * @since 2024/9/15
 */
@Slf4j
public class LoginResultEventHandler extends AbstractGameEventHandler<LoginResultEvent> {

    @Override
    public void handle(EventHandlerContext<LoginResultEvent> eventHandlerContext) {
        log.info("处理登录结果事件");
        LoginResultEvent loginResultEvent = eventHandlerContext.getEvent();
        boolean success = loginResultEvent.isSuccess();
        log.info("登录结果:{}", success);
        System.out.println("登录结果：" + (success ? "成功" : "失败，" + loginResultEvent.getMessage()));
        if (!success) {
            IOUtils.login(eventHandlerContext.getChannel());
        }
    }
}
