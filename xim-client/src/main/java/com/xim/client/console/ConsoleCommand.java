package com.xim.client.console;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

/**
 * 控制台命令接口
 *
 * @author noodle
 * @date 2019/6/24 20:38
 */
public interface ConsoleCommand {

    InternalLogger logger = InternalLoggerFactory.getInstance(ConsoleCommand.class);

    /**
     * 命令执行
     *
     * @param statement 从控制台输入的命令表达式
     * @param channel   发送命令的 channel
     * @return The result of an asynchronous Channel I/O operation.
     */
    ChannelFuture exec(String statement, Channel channel);
}
