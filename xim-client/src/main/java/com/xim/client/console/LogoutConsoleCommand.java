package com.xim.client.console;

import com.xim.common.attribute.Attributes;
import com.xim.common.protocol.req.ListGroupMembersRequestPacket;
import com.xim.common.protocol.req.LogoutRequestPacket;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;

import java.util.Scanner;

/**
 * 登出命令
 *
 * @author noodle
 * @date 2019/6/24 20:46
 */
public class LogoutConsoleCommand implements ConsoleCommand {

    @Override
    public ChannelFuture exec(String statement, Channel channel) {

        ChannelFuture channelFuture = null;

        LogoutRequestPacket requestPacket = new LogoutRequestPacket();

        // 判断用户是否已经登录
        if (channel.attr(Attributes.LOGON).get() == null) {
            logger.info("您还未登录，请先登录！");
        } else {
            // 发送数据
            channelFuture = channel.writeAndFlush(requestPacket);
        }

        return channelFuture;
    }
}
