package com.xim.client.console;

import com.xim.common.protocol.req.RegisterRequestPacket;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;

import java.util.Scanner;

/**
 * 控制台注册命令处理
 *
 * @author noodle
 * @date 2019/6/27 21:18
 */
public class RegisterConsoleCommand implements ConsoleCommand {

    @Override
    public ChannelFuture exec(String statement, Channel channel) {

        String[] strings = statement.split(" ");
        if (strings.length != 3) {
            logger.info("输入参数个数错误");
            return null;
        }

        // 获取用户名和密码
        String username = strings[1];
        String password = strings[2];

        RegisterRequestPacket packet = new RegisterRequestPacket();
        packet.setUsername(username);
        packet.setPassword(password);

        return channel.writeAndFlush(packet);
    }
}
