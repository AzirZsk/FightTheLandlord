package org.azir.game.client;

import org.azir.game.common.utils.YamlUtils;

/**
 * @author zhangshukun
 * @since 2024/09/09
 */
public class FTLClient {

    public static void main(String[] args) {
        ClientConfig clientConfig = YamlUtils.resolve("config.yaml", ClientConfig.class);
        GameClient gameClient = new GameClient(clientConfig);
        gameClient.connect();
    }
}
