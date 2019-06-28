package com.xim.server.handler;

import com.xim.common.protocol.req.JoinGroupRequestPacket;
import com.xim.common.protocol.resp.JoinGroupResponsePacket;
import com.xim.common.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * 加入群聊指令处理器
 *1. 获取群对应的 channelGroup，然后将当前用户的 channel 添加进去
 * 2. 构造加群响应发送给客户端
 * @author noodle
 * @date 2019/6/24 21:57
 */
public class JoinGroupRequestHandler extends SimpleChannelInboundHandler<JoinGroupRequestPacket>{
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupRequestPacket requestPacket) throws Exception {

        // 1. 获取群对应的 channelGroup，然后将当前用户的 channel 添加进去
        String groupId = requestPacket.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);

        JoinGroupResponsePacket responsePacket = new JoinGroupResponsePacket();
        responsePacket.setGroupId(groupId);

        if (channelGroup == null){
            responsePacket.setSuccess(false);
            responsePacket.setReason("该群不存在！");
        }else {
            channelGroup.add(ctx.channel());
            // 2. 构造加群响应发送给客户端
            responsePacket.setSuccess(true);
            responsePacket.setReason("加群成功");
        }

        ctx.writeAndFlush(responsePacket);
    }
}
