package com.xim.client.console;

import com.xim.common.attribute.Attributes;
import com.xim.common.protocol.req.ListGroupMembersRequestPacket;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;

/**
 * 获取群成员命令
 *
 * @author noodle
 * @date 2019/6/24 21:55
 */
public class ListGroupMembersConsoleCommand implements ConsoleCommand {

    @Override
    public ChannelFuture exec(String statement, Channel channel) {

        ChannelFuture channelFuture=null;

        String trim = statement.trim();
        String[] strings = trim.split(" ");

        if (strings.length < 2) {
            logger.info("输入参数个数错误");
            return null;
        }

        String groupId = strings[1];

        // 判断用户是否已经登录
        if (channel.attr(Attributes.LOGON).get() == null) {
            logger.info("您还未登录，请先登录！");
        } else {
            ListGroupMembersRequestPacket packet = new ListGroupMembersRequestPacket();
            packet.setGroupId(groupId);
            // 发送数据
            channelFuture = channel.writeAndFlush(packet);
        }

        return channelFuture;
    }
}
