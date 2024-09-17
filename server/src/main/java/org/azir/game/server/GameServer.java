package org.azir.game.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.azir.game.common.GameThreadFactory;
import org.azir.game.common.exception.FTLException;
import org.azir.game.common.io.protocol.EventCodec;
import org.azir.game.common.io.protocol.EventFrameDecoder;
import org.azir.game.server.handler.HeartbeatEventHandler;
import org.azir.game.server.handler.LoginGameEventHandler;
import org.azir.game.server.manager.user.MemoryUserManager;

/**
 * 游戏服务器启动类
 *
 * @author zhangshukun
 * @since 2024/09/06
 */
@Slf4j
public class GameServer {

    private static final EventCodec EVENT_CODEC = new EventCodec();

    private final ServerConfig serverConfig;

    public GameServer(ServerConfig serverConfig) {
        this.serverConfig = serverConfig;
    }


    public void start() {
        log.info("网络连接服务正在开启");
        NioEventLoopGroup socketGroup = new NioEventLoopGroup(serverConfig.getSocketThreadNums(), new GameThreadFactory("socket-thread"));
        NioEventLoopGroup gameGroup = new NioEventLoopGroup(serverConfig.getGameThreadNums(), new GameThreadFactory("game-thread"));
        try {
            ChannelFuture sync = new ServerBootstrap()
                    .group(socketGroup, gameGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new EventFrameDecoder())
                                    .addLast(EVENT_CODEC)
                                    .addLast(new HeartbeatEventHandler())
                                    .addLast(new LoginGameEventHandler(new MemoryUserManager()));
                            log.info("客户端连接：{}", ch.remoteAddress());
                            // todo 连接时发送非对称加密的
                        }
                    })
                    .bind(serverConfig.getPort()).sync();
        } catch (InterruptedException e) {
            log.error("网络连接服务开启失败，线程中断", e);
            throw new FTLException("网络连接服务开启失败，线程中断", e);
        } catch (Exception e) {
            log.error("网络连接服务开启失败", e);
            throw new FTLException("网络连接服务开启失败", e);
        }
        log.info("网络连接服务启动成功，绑定端口：{}", serverConfig.getPort());
    }
}
