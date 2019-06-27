package com.xim.server.handler;

import com.xim.common.codec.PacketDecoder;
import com.xim.common.codec.PacketEncoder;
import com.xim.common.codec.Spliter;
import com.xim.common.handler.XIMIdleStateHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * channel handler 初始化器
 *
 * @author noodle
 * @date 2019/6/23 18:22
 */
public class ServerHandlerInitializer extends ChannelInitializer<NioSocketChannel> {

    @Override
    protected void initChannel(NioSocketChannel ch) throws Exception {

        ChannelPipeline pipeline = ch.pipeline();

        /* inbound handler*/
        // 空闲状态处理器
        pipeline.addLast(new XIMIdleStateHandler());
        // 粘包处理器
        pipeline.addLast(new Spliter());
        // Packet解码器
        pipeline.addLast(new PacketDecoder());
        // 注册请求处理器
        pipeline.addLast(new RegisterRequestHandler());
        // 登录请求处理器
        pipeline.addLast(new LoginRequestHandler());
        // 心跳请求处理器
        pipeline.addLast(new HeartBeatRequestHandler());
        // 权限处理器
        pipeline.addLast(new AuthHandler());
        // 单聊消息请求处理器
        pipeline.addLast(new MessageRequestHandler());
        // 创建群请求处理器
        pipeline.addLast(new CreateGroupRequestHandler());
        // 加群请求处理器
        pipeline.addLast(new JoinGroupRequestHandler());
        // 退群请求处理器
        pipeline.addLast(new QuitGroupRequestHandler());
        // 获取群成员请求处理器
        pipeline.addLast(new ListGroupMembersRequestHandler());
        // 消息群发请求处理器
        pipeline.addLast(new GroupMessageRequestHandler());
        // 登出请求处理器

        /* outbound handler */
        pipeline.addLast(new PacketEncoder());

    }
}
