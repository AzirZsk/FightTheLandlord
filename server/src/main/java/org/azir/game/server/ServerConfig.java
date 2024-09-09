package org.azir.game.server;

import lombok.Data;

/**
 * @author zhangshukun
 * @since 2024/09/06
 */
@Data
public class ServerConfig {

    /**
     * socket线程数
     */
    private Integer socketThreadNums = 4;

    /**
     * 游戏线程数
     */
    private Integer gameThreadNums = 8;

    /**
     * 端口号
     */
    private Integer port = 12345;

}
