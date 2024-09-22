package org.azir.game.client.utils;

import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.azir.game.common.event.client.LoginGameEvent;

import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.util.Base64;
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

    public static void main(String[] args) throws Exception {
        // 生成 RSA 密钥对
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048); // 设置密钥长度为 2048 位
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        // 获取公钥和私钥
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        // 将公私钥转换为 Base64 编码后的字符串
        String publicKeyEncoded = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        String privateKeyEncoded = Base64.getEncoder().encodeToString(privateKey.getEncoded());

        // 打印明文的公钥和私钥
        System.out.println("Public Key (Base64): " + publicKeyEncoded);
        System.out.println("Private Key (Base64): " + privateKeyEncoded);
    }
}
