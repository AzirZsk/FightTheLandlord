package org.azir.game.common.event;

/**
 * 游戏服务器与玩家端连接的事件
 *
 * @author zhangshukun
 * @since 2024/9/7
 */
public interface Event {

    /**
     * 获取事件类型
     *
     * @return 事件类型
     */
    int getType();
}
