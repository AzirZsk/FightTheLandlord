package org.azir.game.common.io.protocol;

import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * 帧解码器
 *
 * @author zhangshukun
 * @since 2024/9/7
 */
public class EventFrameDecoder extends LengthFieldBasedFrameDecoder {

    /**
     * maxFrameLength：最大帧长度 = 65535
     * lengthFieldOffset：长度字段偏移 = 4
     * lengthFieldLength：长度字段长度 = 4
     * lengthAdjustment：长度调整 = 0
     * initialBytesToStrip：初始字节剥离 = 0
     */
    public EventFrameDecoder() {
        this(65535, 4, 4, 0, 0);
    }

    public EventFrameDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength, int lengthAdjustment, int initialBytesToStrip) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment, initialBytesToStrip);
    }
}
