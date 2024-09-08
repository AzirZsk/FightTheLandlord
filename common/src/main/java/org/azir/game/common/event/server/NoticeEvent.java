package org.azir.game.common.event.server;

import org.azir.game.common.event.AbstractEvent;

/**
 * 服务器-公告事件
 * @author zhangshukun
 * @since 2024/9/7
 */
public class NoticeEvent extends AbstractEvent {
    @Override
    public int getType() {
        return 0;
    }
}
