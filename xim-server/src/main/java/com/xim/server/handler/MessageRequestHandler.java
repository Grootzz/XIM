package com.xim.server.handler;

import com.xim.common.protocol.req.MessageRequestPacket;
import com.xim.common.protocol.resp.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * 处理客户端发送的消息
 *
 * @author noodle
 * @date 2019/6/24 14:16
 */
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket messageRequestPacket) throws Exception {
        System.out.println(new Date() + ": 收到客户端消息: " + messageRequestPacket.getMessage());

        // 生成响应消息
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setMessage("服务端回复【" + messageRequestPacket.getMessage() + "】");

        // 写数据到客户端
        ctx.channel().writeAndFlush(messageResponsePacket);
    }
}
