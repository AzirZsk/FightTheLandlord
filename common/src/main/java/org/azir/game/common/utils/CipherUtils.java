package org.azir.game.common.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.X509EncodedKeySpec;
import java.util.Random;

/**
 * @author zhangshukun
 * @since 2024/9/21
 */
public class CipherUtils {

    private static final String RSA = "RSA";

    private static final String AES = "AES";

    /**
     * 获取 RSA 密钥对
     *
     * @return RSA密钥对
     */
    public static KeyPair createRSAKeyPair() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA);
            keyPairGenerator.initialize(2048);
            return keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 使用RSA公钥加密数据
     *
     * @param keyBytes RSA公钥
     * @param data     待加密的数据
     * @return 加密后的数据
     */
    public static byte[] rsaEncrypt(byte[] keyBytes, byte[] data) {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(RSA);
            PublicKey publicKey = keyFactory.generatePublic(keySpec);
            Cipher cipher = Cipher.getInstance(RSA);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 使用RSA私钥解密数据
     *
     * @param privateKey RSA私钥
     * @param data       待解密的数据
     * @return 解密后的数据
     */
    public static byte[] rsaDecrypt(PrivateKey privateKey, byte[] data) {
        try {
            Cipher cipher = Cipher.getInstance(RSA);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 创建AES密钥，16位
     *
     * @return 16位AES密钥
     */
    public static String createAESSecretKey() {
        Random random = new Random();
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            res.append(random.nextInt(10));
        }
        return res.toString();
    }

    /**
     * 解密数据包
     *
     * @param key  AES密钥
     * @param data 加密前的数据包
     * @return 解密后的数据包
     */
    public static byte[] aesDecrypt(String key, byte[] data) {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), AES);
        try {
            Cipher cipher = Cipher.getInstance(AES);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 加密数据包
     *
     * @param key  AES密钥
     * @param data 待加密的数据包
     * @return 加密后的数据包
     */
    public static byte[] aesEncrypt(String key, byte[] data) {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), AES);
        try {
            Cipher cipher = Cipher.getInstance(AES);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
