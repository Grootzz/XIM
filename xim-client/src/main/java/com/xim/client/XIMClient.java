package com.xim.client;

import com.xim.client.console.ConsoleCommandManager;
import com.xim.client.console.LoginConsoleCommand;
import com.xim.client.handler.ClientHandlerInitializer;
import com.xim.client.scan.Scan;
import com.xim.common.util.DateUtils;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * xim 客户端
 *
 * @author noodle
 * @date 2019/6/23 16:40
 */
public class XIMClient {

    private static final InternalLogger logger = InternalLoggerFactory.getInstance(XIMClient.class);
    private static final int MAX_RETRY = 5;
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 4444;

    public static void main(String[] args) {

        System.out.println("****************************************************************");
        System.out.println("*                         启动 XIM 客户端                       *");
        System.out.println("****************************************************************");

        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            // 配置客户端
            bootstrap
                    .group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000) // 连接的超时时间，超过这个时间还是建立不上的话则代表连接失败
                    .option(ChannelOption.SO_KEEPALIVE, true) // 开启 TCP 底层心跳机制
                    .option(ChannelOption.TCP_NODELAY, true) //  开启 Nagle 算法（流量控制算法）
                    .handler(new ClientHandlerInitializer());// 添加请求处理器

            connect(bootstrap, HOST, PORT, MAX_RETRY);

        } finally {
           // workerGroup.shutdownGracefully();
        }
    }

    /**
     * 连接服务器
     */
    private static void connect(Bootstrap bootstrap, String host, int port, int retry) {
        // 连接服务器
        ChannelFuture channelFuture = bootstrap.connect(host, port);

        channelFuture.addListener(future -> {
            if (future.isSuccess()) {
                System.out.println(DateUtils.date() + " 与服务器建立连接成功，启动控制台线程");
                // 获取与服务端关联的channel
                Channel channel = ((ChannelFuture) future).channel();
                // 获取控制台输入信息
                new Thread(new Scan(channel)).start();
            } else if (retry == 0) {
                System.err.println(DateUtils.date() + " 重试次数已用完，放弃连接！");
            } else {
                // 第几次重连
                int order = (MAX_RETRY - retry) + 1;

                // 本次重连的时间间隔, 指数分布
                int delay = 1 << order;
                System.err.println(DateUtils.date() + " 连接失败，第" + order + "次重连……");

                EventLoopGroup eventExecutors = bootstrap.config().group();
                eventExecutors.schedule(() -> connect(bootstrap, host, port, retry - 1), delay, TimeUnit.SECONDS);
            }
        });
    }
}
