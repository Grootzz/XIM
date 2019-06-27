package com.xim.client.console;

import com.xim.common.protocol.req.MessageRequestPacket;
import io.netty.channel.Channel;
import org.junit.Test;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 单聊命令
 *
 * @author noodle
 * @date 2019/6/24 20:47
 */
public class SendToUserConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("发送消息给某个某个用户：");

        String toUserId = scanner.next();
        String message = scanner.next();
        channel.writeAndFlush(new MessageRequestPacket(toUserId, message));
    }

    @Override
    public void exec(String statement, Channel channel) {

        String trim = statement.trim();
        String[] strings = trim.split(" ");

        if (strings.length < 3) {
            logger.info("输入参数个数错误");
            return;
        }

        String[] res = parse(trim);

        // 获取输入的用户id和消息
        String toUserId = res[0];
        String message = res[1];

        channel.writeAndFlush(new MessageRequestPacket(toUserId, message));
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
