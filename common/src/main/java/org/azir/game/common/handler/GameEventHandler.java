package org.azir.game.common.handler;


import org.azir.game.common.event.Event;

/**
 * 游戏事件处理器
 *
 * @author zhangshukun
 * @since 2024/09/09
 */
public interface GameEventHandler<E extends Event> {

    /**
     * 处理事件
     *
     * @param eventHandlerContext 上下文
     */
    void handle(EventHandlerContext<E> eventHandlerContext);
}
