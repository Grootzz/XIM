package com.xim.server;

import com.xim.server.handler.ServerHandlerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.util.Date;

/**
 * xim 服务端
 *
 * @author noodle
 * @date 2019/6/23 18:17
 */
public class XIMServer {

    private static final int PORT = 8000;

    public static void main(String[] args) {

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();

            bootstrap
                    .group(bossGroup, workerGroup) // 设置线程模型
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024) // 临时存放已完成三次握手的请求的队列的最大长度
                    .childOption(ChannelOption.SO_KEEPALIVE, true) // 开启TCP底层心跳机制
                    .childOption(ChannelOption.TCP_NODELAY, true) // 开启 Nagle 算法
                    .childHandler(new ServerHandlerInitializer());

            bind(bootstrap, PORT);
        } finally {
            //bossGroup.shutdownGracefully();
            //workerGroup.shutdownGracefully();
        }
    }

    /**
     * 绑定端口
     */
    private static void bind(ServerBootstrap bootstrap, int port) {
        bootstrap.bind(port) // 绑定端口
                .addListener(future -> {
                    if (future.isSuccess()) {
                        System.out.println(new Date() + ": 端口[" + port + "]绑定成功!");
                    } else {
                        System.err.println("端口[" + port + "]绑定失败!");
                    }
                });
    }
}
