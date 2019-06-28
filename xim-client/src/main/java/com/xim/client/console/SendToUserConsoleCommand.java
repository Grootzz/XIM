package com.xim.client.console;

import com.xim.common.attribute.Attributes;
import com.xim.common.protocol.req.MessageRequestPacket;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;

import java.util.Scanner;

/**
 * 单聊命令
 *
 * @author noodle
 * @date 2019/6/24 20:47
 */
public class SendToUserConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(String statement, Channel channel) {

        String trim = statement.trim();
        String[] strings = trim.split(" ");

        if (strings.length < 3) {
            logger.info("输入参数个数错误");
            return;
        }

        String[] res = parse(trim);

        // 获取输入的用户名和消息
        String username = res[0];
        String message = res[1];

        // 判断用户是否已经登录
        if (channel.attr(Attributes.LOGON).get() == null) {
            logger.info("您还未登录，请先登录！");
        } else {
            channel.writeAndFlush(new MessageRequestPacket(username, message));
        }
    }

    /**
     * 解析命令表达式
     */
    private String[] parse(String statement) {

        int start = statement.indexOf(" ");

        String stringWithoutCommandTmp = statement.substring(start);
        String stringWithoutCommand = stringWithoutCommandTmp.trim();

        start = stringWithoutCommand.indexOf(" ");

        String username = stringWithoutCommand.substring(0, start);
        String msg = stringWithoutCommand.substring(start + 1);

        return new String[]{username, msg};
    }
}
