package org.azir.game.common.event;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.azir.game.common.exception.FTLException;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * 心跳事件
 * <pre>
 * 包含以下信息：
 * 1. 客户端ip地址
 * 2. 客户端机器信息（设备类型、电量、）
 * 3. 客户端当前时区时间
 * 4.
 * </pre>
 * 间隔1秒发送一次
 *
 * @author zhangshukun
 * @since 2024/9/10
 */
@Data
@Slf4j
public class HeartbeatEvent extends AbstractEvent {

    private final String osType = System.getProperty("os.name");

    private final String osVersion = System.getProperty("os.version");

    private final String macAddress;

    private final String clientName;

    public HeartbeatEvent() {
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            clientName = localHost.getHostName();
            macAddress = getMacAddress();
        } catch (Exception e) {
            log.error("获取本地客户端信息失败", e);
            throw new FTLException("获取本地客户端信息失败", e);
        }
    }

    /**
     * 获取mac地址
     *
     * @return mac地址
     */
    private String getMacAddress() {
        try {
            byte[] mac = NetworkInterface.getByInetAddress(InetAddress.getLocalHost())
                    .getHardwareAddress();
            StringBuilder res = new StringBuilder();
            for (int i = 0; i < mac.length; i++) {
                res.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
            }
            return res.toString();
        } catch (SocketException | UnknownHostException e) {
            log.error("获取mac地址失败", e);
            throw new FTLException("获取mac地址失败", e);
        }
    }

}
