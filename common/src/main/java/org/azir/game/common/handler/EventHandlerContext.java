package org.azir.game.common.handler;

import io.netty.channel.Channel;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.azir.game.common.event.Event;

/**
 * @author zhangshukun
 * @since 2024/9/11
 */
@Data
@AllArgsConstructor
public class EventHandlerContext<E extends Event> {

    private E event;

    private Channel channel;

    public void sendEvent(Object object) {
        this.channel.writeAndFlush(object);
    }

}
