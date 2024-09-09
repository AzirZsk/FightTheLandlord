package org.azir.game.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.azir.game.common.GameThreadFactory;
import org.azir.game.common.exception.FTLException;
import org.azir.game.common.io.protocol.EventCodec;
import org.azir.game.common.io.protocol.EventFrameDecoder;

/**
 * @author zhangshukun
 * @since 2024/09/09
 */
@Slf4j
public class GameClient {

    private static final EventCodec EVENT_CODEC = new EventCodec();

    private final ClientConfig clientConfig;

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
                            ch.pipeline().addLast(new EventFrameDecoder())
                                    .addLast(EVENT_CODEC);
                        }
                    })
                    .connect(clientConfig.getServerHost(), clientConfig.getServerPort())
                    .sync();
        } catch (InterruptedException e) {
            log.error("连接游戏服务器失败，连接线程中断", e);
            throw new FTLException("连接游戏服务器失败，连接线程中断", e);
        } catch (Exception e) {
            log.error("连接游戏服务器失败", e);
            throw new FTLException("连接游戏服务器失败", e);
        }
        log.info("连接游戏服务器成功");
    }

}
