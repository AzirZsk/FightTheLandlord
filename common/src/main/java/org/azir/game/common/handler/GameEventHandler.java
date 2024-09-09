package org.azir.game.common.handler;

import org.azir.game.common.event.Event;

/**
 * 游戏事件处理器
 *
 * @author zhangshukun
 * @since 2024/09/09
 */
public interface GameEventHandler<E> {

    /**
     * 处理事件
     *
     * @param event 事件
     */
    void handle(E event);
}
