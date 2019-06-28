package com.xim.server.handler;

import com.xim.common.protocol.req.QuitGroupRequestPacket;
import com.xim.common.protocol.resp.QuitGroupResponsePacket;
import com.xim.common.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * 退出群聊请求指令处理器
 * 1. 获取群对应的 channelGroup，然后将当前用户的 channel 移除
 * 2. 构造退群响应发送给客户端
 *
 * @author noodle
 * @date 2019/6/24 22:00
 */
public class QuitGroupRequestHandler extends SimpleChannelInboundHandler<QuitGroupRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupRequestPacket requestPacket) throws Exception {

        // 1. 获取群对应的 channelGroup，然后将当前用户的 channel 移除
        String groupId = requestPacket.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);

        QuitGroupResponsePacket responsePacket = new QuitGroupResponsePacket();
        responsePacket.setGroupId(requestPacket.getGroupId());

        if (channelGroup == null) {
            responsePacket.setSuccess(false);
            responsePacket.setReason("该群组不存在！");
        } else {
            // 2. 构造退群响应发送给客户端
            channelGroup.remove(ctx.channel());
            responsePacket.setSuccess(true);
        }

//        ctx.channel().writeAndFlush(responsePacket);
        ctx.writeAndFlush(responsePacket);
    }
}
