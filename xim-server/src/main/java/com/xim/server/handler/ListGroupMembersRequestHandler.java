package com.xim.server.handler;

import com.xim.common.protocol.req.ListGroupMembersRequestPacket;
import com.xim.common.protocol.resp.ListGroupMembersResponsePacket;
import com.xim.common.session.Session;
import com.xim.common.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 列出群成员请求处理器
 *
 * @author noodle
 * @date 2019/6/24 22:02
 */
public class ListGroupMembersRequestHandler extends SimpleChannelInboundHandler<ListGroupMembersRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupMembersRequestPacket requestPacket) throws Exception {
        // 1. 获取群的 ChannelGroup
        String groupId = requestPacket.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);

        ListGroupMembersResponsePacket responsePacket = new ListGroupMembersResponsePacket();
        responsePacket.setGroupId(groupId);

        // 判断群是否存在
        if (channelGroup == null) {
            responsePacket.setSuccess(false);
            responsePacket.setInfo("该群组不存在，检查群组id是否正确！");
        } else {
            // 2. 遍历群成员的 channel，对应的 session，构造群成员的信息
            List<String> usernameList = new ArrayList<>();
            for (Channel channel : channelGroup) {
                Session session = SessionUtil.getSession(channel);
                usernameList.add(session.getUserName());
            }

            // 3. 构建获取成员列表响应写回到客户端
            responsePacket.setSuccess(true);
            responsePacket.setUsernameList(usernameList);
            responsePacket.setInfo("成功获取群成员");
        }

        ctx.writeAndFlush(responsePacket);
    }
}
