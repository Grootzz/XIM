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
public class LoginConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();

//        String line = scanner.nextLine();
//        String[] strings = line.split(" ");
//
//        String loginCommand = strings[0];
//        String username = strings[1];
//        String pwd = strings[2];



        System.out.print("输入用户名登录: ");
        loginRequestPacket.setUserName(scanner.nextLine());
        loginRequestPacket.setPassword("admin");

        //loginRequestPacket.setUserName(username);
        //loginRequestPacket.setPassword(pwd);

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
