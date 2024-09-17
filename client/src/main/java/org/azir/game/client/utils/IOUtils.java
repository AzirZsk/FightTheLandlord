package org.azir.game.client.utils;

import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.azir.game.common.event.client.LoginGameEvent;

import java.util.Scanner;

/**
 * @author zhangshukun
 * @since 2024/9/16
 */
@Slf4j
public class IOUtils {

    public static void login(Channel channel) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("账号：");
        String username = scanner.nextLine();
        System.out.print("密码：");
        String password = scanner.nextLine();
        log.info("账号：{}，请求登录", username);
        channel.writeAndFlush(new LoginGameEvent(username, password));
    }
}
