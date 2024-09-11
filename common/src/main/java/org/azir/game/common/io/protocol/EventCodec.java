package org.azir.game.common.io.protocol;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import lombok.extern.slf4j.Slf4j;
import org.azir.game.common.event.Event;
import org.azir.game.common.io.serializable.KryoBuilder;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * 斗地主使用的通信协议
 * <pre>
 *  magic: FTL 其他的都关闭连接
 *  消息长度: 消息序列化后消息内容的长度
 *  消息内容: 消息主题
 * +-------+----------------+---------+
 * | magic |  contentLength | content |
 * |  FTL  |   消息内容长度   | 消息内容  |
 * +-------+----------------+---------+
 * </pre>
 *
 * @author zhangshukun
 * @since 2024/09/06
 */
@Slf4j
@ChannelHandler.Sharable
public class EventCodec extends MessageToMessageCodec<ByteBuf, Event> implements Serializable {

    /**
     * 序列化缓存
     */
    private static final ThreadLocal<Kryo> SERIALIZABLE_THREAD_LOCAL = ThreadLocal.withInitial(KryoBuilder::build);

    /**
     * 事件魔数
     */
    private static final byte[] MAGIC = "FTL".getBytes();

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Event event, List<Object> list) throws Exception {
        if (log.isTraceEnabled()) {
            log.trace("发送事件: {}", event);
        }
        ByteBuf buffer = channelHandlerContext.alloc().buffer();
        // 写入魔数
        buffer.writeBytes(MAGIC);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Output output = new Output(outputStream);
        Kryo kryo = SERIALIZABLE_THREAD_LOCAL.get();
        kryo.writeObject(output, event);
        output.close();
        // 写入消息长度
        buffer.writeInt(outputStream.size());
        // 写入消息内容
        buffer.writeBytes(outputStream.toByteArray());
        if (log.isTraceEnabled()) {
            log.trace("事件字节长度: {}", buffer.writerIndex());
        }
        list.add(buffer);
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if (log.isTraceEnabled()) {
            log.trace("接收事件");
        }
        // 读取魔数
        ByteBuf magicByteBuf = byteBuf.readBytes(MAGIC.length);
        byte[] array = magicByteBuf.array();
        if (!Arrays.equals(MAGIC, array)) {
            log.error("非法连接: {}", channelHandlerContext.channel().remoteAddress());
            channelHandlerContext.close();
            return;
        }
        int eventLength = byteBuf.readInt();
        byte[] bytes = new byte[eventLength];
        byteBuf.readBytes(bytes);
        Kryo kryo = SERIALIZABLE_THREAD_LOCAL.get();
        Event event = kryo.readObject(new Input(bytes), Event.class);
        if (log.isTraceEnabled()) {
            log.trace("接收到的事件: {}", event);
        }
        list.add(event);
    }
}
