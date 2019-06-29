package com.xim.client.console;

import com.xim.common.attribute.Attributes;
import com.xim.common.protocol.req.CreateGroupRequestPacket;
import com.xim.common.protocol.req.JoinGroupRequestPacket;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 创建群组命令
 *
 * @author noodle
 * @date 2019/6/24 20:43
 */
public class CreateGroupConsoleCommand implements ConsoleCommand {

    /**
     * 用户id分隔符
     */
    private static final String USER_ID_DELIMITER = ",";

    @Override
    public ChannelFuture exec(String statement, Channel channel) {

        ChannelFuture channelFuture = null;

        String trim = statement.trim();
        String[] strings = trim.split(" ");

        if (strings.length < 2) {
            logger.info("输入参数个数错误");
            return null;
        }

        int start = trim.indexOf(" ");
        String stringWithoutCommandTmp = trim.substring(start);
        String usernameList = stringWithoutCommandTmp.trim();

        // 判断用户是否已经登录
        if (channel.attr(Attributes.LOGON).get() == null) {
            logger.info("您还未登录，请先登录！");
        } else {
            CreateGroupRequestPacket createGroupRequestPacket = new CreateGroupRequestPacket();
            createGroupRequestPacket.setUsernameList(Arrays.asList(usernameList.split(USER_ID_DELIMITER)));
            // 发送数据
            channelFuture = channel.writeAndFlush(createGroupRequestPacket);
        }

        return channelFuture;
    }

    /**
     * 解析命令表达式
     */
    private String[] parse(String statement) {
        return null;
    }
}
