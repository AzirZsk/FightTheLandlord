package org.azir.game.client.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import lombok.extern.slf4j.Slf4j;
import org.azir.game.common.event.cipher.AESKeyEvent;
import org.azir.game.common.event.cipher.RSAPublicKeyEvent;
import org.azir.game.common.handler.AbstractGameEventHandler;
import org.azir.game.common.handler.EventHandlerContext;
import org.azir.game.common.io.protocol.EventCodec;
import org.azir.game.common.utils.CipherUtils;

import java.nio.charset.StandardCharsets;

/**
 * @author zhangshukun
 * @since 2024/9/21
 */
@Slf4j
@ChannelHandler.Sharable
public class RSAPublicKeyEventHandler extends AbstractGameEventHandler<RSAPublicKeyEvent> {

    @Override
    public void handle(EventHandlerContext<RSAPublicKeyEvent> eventHandlerContext) {
        Channel channel = eventHandlerContext.getChannel();
        if (channel.hasAttr(EventCodec.AES_SECRET_KEY)) {
            throw new IllegalStateException("已经初始化AES密钥了");
        }
        String aesSecretKey = CipherUtils.createAESSecretKey();
        log.info("生成AES密钥:{}", aesSecretKey);
        byte[] bytes = CipherUtils.rsaEncrypt(eventHandlerContext.getEvent().getKey(), aesSecretKey.getBytes(StandardCharsets.UTF_8));
        AESKeyEvent aesKeyEvent = new AESKeyEvent();
        aesKeyEvent.setKey(bytes);
        channel.writeAndFlush(aesKeyEvent);
        channel.attr(EventCodec.AES_SECRET_KEY).set(aesSecretKey);
        if (log.isDebugEnabled()) {
            log.debug("发送AES密钥成功");
        }
    }
}
