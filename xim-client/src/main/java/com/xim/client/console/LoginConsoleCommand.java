package com.xim.client.console;

import com.xim.common.attribute.Attributes;
import com.xim.common.protocol.req.LoginRequestPacket;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;

import java.util.Scanner;

/**
 * 登录命令
 *
 * @author noodle
 * @date 2019/6/24 20:39
 */
public class LoginConsoleCommand implements ConsoleCommand {

    @Override
    public ChannelFuture exec(String statement, Channel channel) {

        ChannelFuture channelFuture = null;

        String[] strings = statement.split(" ");
        if (strings.length != 3) {
            logger.info("输入参数个数错误");
            return null;
        }

        // 获取用户名和密码
        String username = strings[1];
        String password = strings[2];

        LoginRequestPacket requestPacket = new LoginRequestPacket(username, password);

        // 判断用户是否已经登录
        if (channel.attr(Attributes.LOGON).get() != null) {
            logger.info("您已登录，无需重复登录");
        } else {
            try {
                // 发送登录数据包
                channelFuture = channel.writeAndFlush(requestPacket).sync();
            } catch (InterruptedException e) {
                logger.info("您已登录，无需重复登录");
            }
        }

        return channelFuture;
    }

}
