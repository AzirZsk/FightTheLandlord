package org.azir.game.common.event.client;

import org.azir.game.common.event.AbstractEvent;

/**
 * 客户端-叫分事件
 * @author zhangshukun
 * @since 2024/9/7
 */
public class BidEvent extends AbstractEvent {
    @Override
    public int getType() {
        return 0;
    }
}
