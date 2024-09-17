package org.azir.game.common.event;

import java.time.LocalDateTime;

/**
 * 抽象事件类
 *
 * @author zhangshukun
 * @since 2024/09/06
 */
public abstract class AbstractEvent implements Event {

    private final LocalDateTime createTime = LocalDateTime.now();

}

