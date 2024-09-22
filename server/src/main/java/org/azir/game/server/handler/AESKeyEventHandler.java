package org.azir.game.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import lombok.extern.slf4j.Slf4j;
import org.azir.game.common.event.cipher.AESKeyEvent;
import org.azir.game.common.handler.AbstractGameEventHandler;
import org.azir.game.common.handler.EventHandlerContext;
import org.azir.game.common.io.protocol.EventCodec;
import org.azir.game.common.utils.CipherUtils;
import org.azir.game.server.GameServer;

import java.nio.charset.StandardCharsets;

/**
 * @author zhangshukun
 * @since 2024/9/21
 */
@Slf4j
@ChannelHandler.Sharable
public class AESKeyEventHandler extends AbstractGameEventHandler<AESKeyEvent> {

    @Override
    public void handle(EventHandlerContext<AESKeyEvent> eventHandlerContext) {
        AESKeyEvent event = eventHandlerContext.getEvent();
        byte[] key = event.getKey();
        Channel channel = eventHandlerContext.getChannel();
        byte[] bytes = CipherUtils.rsaDecrypt(channel.attr(GameServer.RSA_KEY_PAIR).get().getPrivate(), key);
        String aesKey = new String(bytes, StandardCharsets.UTF_8);
        log.info("本次的AES密钥：{}", aesKey);
        channel.attr(EventCodec.AES_SECRET_KEY).set(aesKey);
        if (log.isDebugEnabled()) {
            log.debug("成功接收到本次连接的AES密钥");
        }
    }
}
