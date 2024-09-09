package org.azir.game.common.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.azir.game.common.event.Event;

/**
 * 抽象的游戏事件处理器
 * 事件处理器均需在Netty流水线中可复用
 *
 * @author zhangshukun
 * @since 2024/09/09
 */
@Slf4j
@ChannelHandler.Sharable
public abstract class AbstractGameEventHandler<E extends Event> extends SimpleChannelInboundHandler<E> implements GameEventHandler<E> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, E msg) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("收到并开始处理游戏事件:{}", msg);
        }
        handle(msg);
        if (log.isDebugEnabled()) {
            log.debug("成功处理游戏事件");
        }
    }
}
