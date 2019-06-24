package com.xim.client.console;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * 控制台命令接口
 *
 * @author noodle
 * @date 2019/6/24 20:38
 */
public interface ConsoleCommand {
    void exec(Scanner scanner, Channel channel);
}
