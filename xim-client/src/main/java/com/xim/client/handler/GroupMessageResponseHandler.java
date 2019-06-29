package com.xim.client.handler;

import com.xim.common.protocol.resp.GroupMessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author noodle
 * @date 2019/6/25 14:59
 */
public class GroupMessageResponseHandler extends SimpleChannelInboundHandler<GroupMessageResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageResponsePacket responsePacket) throws Exception {
        String groupId = responsePacket.getGroupId();
        String username = responsePacket.getUsername();
        System.out.println("收到群[" + groupId + "]中[" + username + "]发来的消息：" + responsePacket.getMessage());
    }
}
