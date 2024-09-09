package org.azir.game.client;

import lombok.Data;

/**
 * @author zhangshukun
 * @since 2024/09/09
 */
@Data
public class ClientConfig {

    private String serverHost;

    private int serverPort;

    private int maxThreads = 10;
}
