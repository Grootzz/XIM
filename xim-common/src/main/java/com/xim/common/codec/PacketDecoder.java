package com.xim.common.codec;

import com.xim.common.protocol.Packet;
import com.xim.common.protocol.PacketCodeC;
import com.xim.common.protocol.req.LoginRequestPacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * inbound channel 解码器
 *
 * @author noodle
 * @date 2019/6/24 14:01
 */
public class PacketDecoder extends ByteToMessageDecoder{
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        Packet packet = PacketCodeC.INSTANCE.decode(in);
        out.add(packet);
    }
}
