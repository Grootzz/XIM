package com.xim.client.console;

import com.xim.common.protocol.req.ListGroupMembersRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * 获取群成员命令
 *
 * @author noodle
 * @date 2019/6/24 21:55
 */
public class ListGroupMembersConsoleCommand implements ConsoleCommand{
    @Override
    public void exec(Scanner scanner, Channel channel) {
        ListGroupMembersRequestPacket listGroupMembersRequestPacket = new ListGroupMembersRequestPacket();

        System.out.print("输入 groupId，获取群成员列表：");
        String groupId = scanner.next();

        listGroupMembersRequestPacket.setGroupId(groupId);
        channel.writeAndFlush(listGroupMembersRequestPacket);
    }

    @Override
    public void exec(String statement, Channel channel) {

    }
}
