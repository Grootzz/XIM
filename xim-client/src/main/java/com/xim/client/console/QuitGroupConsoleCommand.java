package com.xim.client.console;

import com.xim.common.protocol.req.ListGroupMembersRequestPacket;
import com.xim.common.protocol.req.QuitGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * 退出群聊命令
 *
 * @author noodle
 * @date 2019/6/24 21:54
 */
public class QuitGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {

        QuitGroupRequestPacket requestPacket = new QuitGroupRequestPacket();

        System.out.print("输入 groupId，退出群聊：");
        String groupId = scanner.next();

        requestPacket.setGroupId(groupId);

        channel.writeAndFlush(requestPacket);
    }

    @Override
    public void exec(String statement, Channel channel) {
        String trim = statement.trim();
        String[] strings = trim.split(" ");

        if (strings.length < 2) {
            logger.info("输入参数个数错误");
            return;
        }

        String groupId = strings[1];

        QuitGroupRequestPacket requestPacket = new QuitGroupRequestPacket();
        requestPacket.setGroupId(groupId);

        channel.writeAndFlush(requestPacket);
    }
}
