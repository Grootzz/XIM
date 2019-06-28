package com.xim.client.handler;

import com.xim.common.protocol.resp.MessageResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.EventExecutorGroup;

import java.util.Date;

/**
 * MessageResponsePacket handler
 *
 * @author noodle
 * @date 2019/6/24 14:22
 */
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket responsePacket) throws Exception {
        //String fromUserId = responsePacket.getFromUserId();
        String username = responsePacket.getUsername();
        System.out.println(username + " -> " + responsePacket.getMessage());
    }
}
