package com.xim.client.console;

import com.xim.common.protocol.req.LoginRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * 登录命令
 *
 * @author noodle
 * @date 2019/6/24 20:39
 */
public class LoginConsoleCommand implements ConsoleCommand{


    @Override
    public void exec(Scanner scanner, Channel channel) {
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();

        System.out.print("输入用户名登录: ");
        loginRequestPacket.setUserName(scanner.nextLine());
        loginRequestPacket.setPassword("admin");

        // 发送登录数据包
        channel.writeAndFlush(loginRequestPacket);
        waitForLoginResponse();
    }

    private static void waitForLoginResponse() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
    }
}