package com.xim.server;

import com.xim.common.util.DateUtils;
import com.xim.server.handler.ServerHandlerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * xim 服务端
 *
 * @author noodle
 * @date 2019/6/23 18:17
 */
public class XIMServer {

    private static Logger logger = LoggerFactory.getLogger(XIMServer.class);

    private static final int PORT = 4444;

    public static void main(String[] args) {

        System.out.println("****************************************************************");
        System.out.println("*                         启动 XIM 服务端                       *");
        System.out.println("****************************************************************");

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();

            bootstrap
                    .group(bossGroup, workerGroup) // 设置线程模型
                    .channel(NioServerSocketChannel.class) // 指定 channel 实例
                    .handler(new LoggingHandler(LogLevel.INFO)) // 添加日志处理器
                    .option(ChannelOption.SO_BACKLOG, 1024) // 临时存放已完成三次握手的请求的队列的最大长度
                    .childOption(ChannelOption.SO_KEEPALIVE, true) // 开启TCP底层心跳机制
                    .childOption(ChannelOption.TCP_NODELAY, true) // 开启 Nagle 算法（用于流量控制）
                    .childHandler(new ServerHandlerInitializer());

            // start server
            ChannelFuture future = bind(bootstrap, PORT);
            // wait util the server socket is closed.
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // shutdown all event loop to terminate all threads.
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    /**
     * 绑定端口
     */
    private static ChannelFuture bind(ServerBootstrap bootstrap, int port) throws InterruptedException {
        ChannelFuture channelFuture = bootstrap.bind(port) // 绑定端口
                .addListener(future -> {
                    if (future.isSuccess()) {
                        System.out.println(DateUtils.date() + " 服务器启动成功, 端口[" + port + "]");
                    } else {
                        System.err.println(DateUtils.date() + " 服务器启动失败, 端口[" + port + "]");
                    }
                }).sync();
        return channelFuture;
    }
}