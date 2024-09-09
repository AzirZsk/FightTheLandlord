package org.azir.game.server;

import lombok.extern.slf4j.Slf4j;
import org.azir.game.common.utils.YamlUtils;

/**
 * 斗地主游戏服务器
 *
 * @author zhangshukun
 * @since 2024/09/09
 */
@Slf4j
public class FTLServer {

    public static void main(String[] args) {
        ServerConfig serverConfig = YamlUtils.resolve("config.yaml", ServerConfig.class);
        if (log.isDebugEnabled()) {
            log.debug("服务器配置：{}", serverConfig);
        }
        GameServer gameServer = new GameServer(serverConfig);
        gameServer.start();
    }
}
