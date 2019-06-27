package com.xim.client.console;

import com.xim.common.protocol.req.CreateGroupRequestPacket;
import io.netty.channel.Channel;

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
    public void exec(Scanner scanner, Channel channel) {

        CreateGroupRequestPacket createGroupRequestPacket = new CreateGroupRequestPacket();

        System.out.print("【拉人群聊】输入 userId 列表，userId 之间英文逗号隔开：");
        String userIds = scanner.next();
        createGroupRequestPacket.setUserIdList(Arrays.asList(userIds.split(USER_ID_DELIMITER)));
        channel.writeAndFlush(createGroupRequestPacket);
    }

    @Override
    public void exec(String statement, Channel channel) {

        String trim = statement.trim();
        String[] strings = trim.split(" ");

        if (strings.length < 2) {
            logger.info("输入参数个数错误");
            return;
        }

        int start = trim.indexOf(" ");
        String stringWithoutCommandTmp = trim.substring(start);
        String stringWithoutCommand = stringWithoutCommandTmp.trim();

        System.out.println(Arrays.asList(stringWithoutCommand.split(USER_ID_DELIMITER)));
        CreateGroupRequestPacket createGroupRequestPacket = new CreateGroupRequestPacket();

        createGroupRequestPacket.setUserIdList(Arrays.asList(stringWithoutCommand.split(USER_ID_DELIMITER)));

        channel.writeAndFlush(createGroupRequestPacket);
    }

    /**
     * 解析命令表达式
     */
    private String[] parse(String statement) {
        return null;
    }
}
