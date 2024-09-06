package org.azir.game.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.azir.game.common.GameThreadFactory;

/**
 * 游戏服务器启动类
 *
 * @author zhangshukun
 * @since 2024/09/06
 */
public class GameServer {

    private final ServerConfig serverConfig;


    public GameServer(ServerConfig serverConfig) {
        this.serverConfig = serverConfig;
    }


    public void start() {
        NioEventLoopGroup socketGroup = new NioEventLoopGroup(serverConfig.getSocketThreadNums(), new GameThreadFactory("socket-thread"));
        NioEventLoopGroup gameGroup = new NioEventLoopGroup(serverConfig.getGameThreadNums(), new GameThreadFactory("game-thread"));
        new ServerBootstrap()
                .group(socketGroup, gameGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioServerSocketChannel>() {
                    @Override
                    protected void initChannel(NioServerSocketChannel ch) throws Exception {

                    }
                })
                .bind(serverConfig.getPort());
    }

    public static void main(String[] args) {

    }
}
