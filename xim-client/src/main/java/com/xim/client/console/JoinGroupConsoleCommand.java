package com.xim.client.console;

import com.xim.common.attribute.Attributes;
import com.xim.common.protocol.req.CreateGroupRequestPacket;
import com.xim.common.protocol.req.JoinGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 加入群聊命令处理
 *
 * @author noodle
 * @date 2019/6/24 21:38
 */
public class JoinGroupConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(String statement, Channel channel) {
        String trim = statement.trim();
        String[] strings = trim.split(" ");

        if (strings.length < 2) {
            logger.info("输入参数个数错误");
            return;
        }

        String groupId = strings[1];

        // 判断用户是否已经登录
        if (channel.attr(Attributes.LOGON).get() == null) {
            logger.info("您还未登录，请先登录！");
        } else {
            JoinGroupRequestPacket requestPacket = new JoinGroupRequestPacket();
            requestPacket.setGroupId(groupId);

            channel.writeAndFlush(requestPacket);
        }
    }
}
