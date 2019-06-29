package com.xim.client.scan;

import com.xim.client.console.ConsoleCommandManager;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;

import java.util.Scanner;

/**
 * 控制台任务
 * 在客户端连接上服务端之后启动控制台线程，从控制台获取消息，然后发送至服务端
 *
 * @author noodle
 * @date 2019/6/26 21:41
 */
public class Scan implements Runnable {

    private Channel channel;

    public Scan(Channel channel) {
        this.channel = channel;
    }

    @Override
    public void run() {
        ConsoleCommandManager consoleCommandManager = new ConsoleCommandManager();

        Scanner scanner = new Scanner(System.in);

        /* 接收控制台命令 */
        while (!Thread.interrupted()) {

            String stmt = scanner.nextLine();

            // 输入预校验
            if (stmt == null || stmt.length() == 0 || stmt.split(" ").length == 0) {
                continue;
            }

            consoleCommandManager.exec(stmt, channel);
        }
    }
}
