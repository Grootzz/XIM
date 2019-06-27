package com.xim.client.console;

import com.xim.common.protocol.req.MessageRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * 单聊命令
 *
 * @author noodle
 * @date 2019/6/24 20:47
 */
public class SendToUserConsoleCommand implements ConsoleCommand{

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("发送消息给某个某个用户：");

        String toUserId = scanner.next();
        String message = scanner.next();
        channel.writeAndFlush(new MessageRequestPacket(toUserId, message));
    }

    @Override
    public void exec(String statement, Channel channel) {



//        String toUserId = scanner.next();
//        String message = scanner.next();
//        channel.writeAndFlush(new MessageRequestPacket(toUserId, message));

    }
}
