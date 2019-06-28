package com.xim.client.console;

import com.xim.common.attribute.Attributes;
import com.xim.common.protocol.req.GroupMessageRequestPacket;
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
            QuitGroupRequestPacket requestPacket = new QuitGroupRequestPacket();
            requestPacket.setGroupId(groupId);
            // 发送数据
            channel.writeAndFlush(requestPacket);
        }
    }
}
