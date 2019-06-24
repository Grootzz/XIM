package com.xim.common.codec;

import com.xim.common.protocol.Packet;
import com.xim.common.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * outbound channel decoder
 *
 * @author noodle
 * @date 2019/6/24 14:06
 */
public class PacketEncoder extends MessageToByteEncoder<Packet> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet msg, ByteBuf out) throws Exception {
        PacketCodeC.INSTANCE.encode(msg, out);
    }
}
