package org.azir.game.common.event.client;

import org.azir.game.common.event.AbstractEvent;

/**
 * 客户端-出牌事件
 *
 * @author zhangshukun
 * @since 2024/9/7
 */
public class CardPlayEvent extends AbstractEvent {
    @Override
    public int getType() {
        return 0;
    }
}
