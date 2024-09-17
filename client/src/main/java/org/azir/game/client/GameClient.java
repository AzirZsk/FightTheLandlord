package org.azir.game.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.azir.game.client.handler.LoginResultEventHandler;
import org.azir.game.common.GameThreadFactory;
import org.azir.game.common.event.HeartbeatEvent;
import org.azir.game.common.event.client.LoginGameEvent;
import org.azir.game.common.exception.FTLException;
import org.azir.game.common.io.protocol.EventCodec;
import org.azir.game.common.io.protocol.EventFrameDecoder;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangshukun
 * @since 2024/09/09
 */
@Slf4j
@Getter
public class GameClient {

    private static final EventCodec EVENT_CODEC = new EventCodec();

    private static final HeartbeatEvent HEARTBEAT_EVENT = new HeartbeatEvent();

    private final ClientConfig clientConfig;

    private Channel channel;

    public GameClient(ClientConfig clientConfig) {
        this.clientConfig = clientConfig;
    }

    public void connect() {
        log.info("正在连接游戏服务器");
        NioEventLoopGroup eventExecutors = new NioEventLoopGroup(clientConfig.getMaxThreads(), new GameThreadFactory("game-thread"));
        try {
            ChannelFuture sync = new Bootstrap()
                    .group(eventExecutors)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel ch) throws Exception {
                            ch.pipeline()
                                    .addLast(new EventFrameDecoder())
                                    .addLast(EVENT_CODEC)
                                    .addLast(new LoginResultEventHandler());
                        }
                    })
                    .connect(clientConfig.getServerHost(), clientConfig.getServerPort())
                    .sync();
            log.info("连接游戏服务器成功");
            this.channel = sync.channel();
//            startHeartbeatSchedule(eventExecutors);
        } catch (Exception e) {
            log.error("连接游戏服务器失败", e);
            throw new FTLException("连接游戏服务器失败", e);
        }
    }

    /**
     * 启动心跳事件定时器
     *
     * @param eventExecutors 事件执行器
     */
    private void startHeartbeatSchedule(ScheduledExecutorService eventExecutors) {
        eventExecutors.scheduleAtFixedRate(() -> {
            try {
                if (log.isTraceEnabled()) {
                    log.trace("发送心跳包");
                }
                this.channel.writeAndFlush(HEARTBEAT_EVENT);
            } catch (Exception e) {
                log.error("发送心跳包失败", e);
            }
        }, 0, 1, TimeUnit.SECONDS);
        if (log.isDebugEnabled()) {
            log.debug("心跳事件定时器启动成功");
        }
    }

}