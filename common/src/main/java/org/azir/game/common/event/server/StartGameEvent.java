package org.azir.game.common.event.server;

import org.azir.game.common.event.AbstractEvent;

/**
 * 服务器-开启游戏事件
 *
 * @author zhangshukun
 * @since 2024/9/7
 */
public class StartGameEvent extends AbstractEvent {

    @Override
    public int getType() {
        return 0;
    }
}
