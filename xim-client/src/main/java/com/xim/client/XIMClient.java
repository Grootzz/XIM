package com.xim.client;

import com.xim.client.handler.ClientHandlerInitializer;
import com.xim.common.protocol.PacketCodeC;
import com.xim.common.protocol.req.MessageRequestPacket;
import com.xim.common.util.LoginUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * xim 客户端
 *
 * @author noodle
 * @date 2019/6/23 16:40
 */
public class XIMClient {

    private static final int MAX_RETRY = 5;
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 3333;

    public static void main(String[] args) {
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            // 配置客户端
            bootstrap
                    .group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000) // 连接的超时时间，超过这个时间还是建立不上的话则代表连接失败
                    .option(ChannelOption.SO_KEEPALIVE, true) // 开启 TCP 底层心跳机制
                    .option(ChannelOption.TCP_NODELAY, true) //  开启 Nagle 算法
                    .handler(new ClientHandlerInitializer());// 添加请求处理器

            connect(bootstrap, HOST, PORT, MAX_RETRY);

        } finally {
            // workerGroup.shutdownGracefully();
        }
    }

    /**
     * 连接服务器
     *
     * @param bootstrap
     * @param host
     * @param port
     * @param retry
     */
    private static void connect(Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap.connect(host, port) // 连接服务器
                .addListener(future -> {
                    if (future.isSuccess()) {
                        System.out.println(new Date() + ": 连接成功，启动控制台线程……");
                        // 获取与服务端关联的channel
                        Channel channel = ((ChannelFuture) future).channel();
                        // 获取控制台输入信息
                        startConsoleThread(channel);
                    } else if (retry == 0) {
                        System.err.println("重试次数已用完，放弃连接！");
                    } else {
                        // 第几次重连
                        int order = (MAX_RETRY - retry) + 1;

                        // 本次重连的时间间隔, 指数分布
                        int delay = 1 << order;
                        System.err.println(new Date() + ": 连接失败，第" + order + "次重连……");

                        bootstrap.config()
                                .group()
                                .schedule(() -> connect(bootstrap, host, port, retry - 1), delay, TimeUnit.SECONDS);
                    }
                });
    }

    /**
     * 在客户端连接上服务端之后启动控制台线程，从控制台获取消息，然后发送至服务端
     *
     * @param channel
     */
    private static void startConsoleThread(Channel channel) {
        new Thread(()->{
            while (!Thread.interrupted()){
//                if (LoginUtil.hasLogin(channel)){
                    System.out.println("输入消息发送至服务端: ");

                    Scanner scanner = new Scanner(System.in);
                    String meg = scanner.nextLine();

                    MessageRequestPacket packet = new MessageRequestPacket();
                    packet.setMessage(meg);
                    ByteBuf byteBuf = PacketCodeC.INSTANCE.encode(channel.alloc(), packet);
                    channel.writeAndFlush(byteBuf);
//                }
            }
        }).start();
    }
}
