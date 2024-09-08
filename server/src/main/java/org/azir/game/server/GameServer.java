package org.azir.game.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.azir.game.common.GameThreadFactory;
import org.azir.game.common.io.protocol.EventCodec;
import org.azir.game.common.io.protocol.EventFrameDecoder;

/**
 * 游戏服务器启动类
 *
 * @author zhangshukun
 * @since 2024/09/06
 */
public class GameServer {

    private final ServerConfig serverConfig;

    private final EventCodec eventCodec = new EventCodec();


    public GameServer(ServerConfig serverConfig) {
        this.serverConfig = serverConfig;
    }


    public void start() {
        NioEventLoopGroup socketGroup = new NioEventLoopGroup(serverConfig.getSocketThreadNums(), new GameThreadFactory("socket-thread"));
        NioEventLoopGroup gameGroup = new NioEventLoopGroup(serverConfig.getGameThreadNums(), new GameThreadFactory("game-thread"));
        new ServerBootstrap()
                .group(socketGroup, gameGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new EventFrameDecoder())
                                .addLast(eventCodec);
                    }
                })
                .bind(serverConfig.getPort());
    }

    public static void main(String[] args) {

    }
}
