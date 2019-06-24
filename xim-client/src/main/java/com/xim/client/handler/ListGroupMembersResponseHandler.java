package com.xim.client.handler;

import com.xim.common.protocol.resp.ListGroupMembersResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.EventExecutorGroup;

/**
 * 列出群成员指令响应处理器
 *
 * @author noodle
 * @date 2019/6/24 22:08
 */
public class ListGroupMembersResponseHandler extends SimpleChannelInboundHandler <ListGroupMembersResponsePacket>{
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupMembersResponsePacket responsePacket) throws Exception {
        System.out.println("群[" + responsePacket.getGroupId() + "]中的人包括：" + responsePacket.getSessionList());
    }
}
