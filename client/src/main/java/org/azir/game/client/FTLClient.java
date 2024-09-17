package org.azir.game.client;

import io.netty.channel.Channel;
import org.azir.game.client.utils.IOUtils;
import org.azir.game.common.utils.YamlUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * @author zhangshukun
 * @since 2024/09/09
 */
public class FTLClient {

    public static void main(String[] args) throws InterruptedException {
        ClientConfig clientConfig = YamlUtils.resolve("config.yaml", ClientConfig.class);
        GameClient gameClient = new GameClient(clientConfig);
        gameClient.connect();
        IOUtils.login(gameClient.getChannel());

    }

}
