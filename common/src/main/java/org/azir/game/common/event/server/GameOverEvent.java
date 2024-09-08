package org.azir.game.common.event.server;

import org.azir.game.common.event.AbstractEvent;

/**
 * 服务器-游戏结束事件
 * @author zhangshukun
 * @since 2024/9/7
 */
public class GameOverEvent extends AbstractEvent {
    @Override
    public int getType() {
        return 0;
    }
}
