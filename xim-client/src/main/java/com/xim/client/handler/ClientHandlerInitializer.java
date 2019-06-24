package com.xim.client.handler;

import com.xim.common.codec.PacketDecoder;
import com.xim.common.codec.PacketEncoder;
import com.xim.common.codec.Spilter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;


/**
 * channel handler 初始化器
 *
 * @author noodle
 * @date 2019/6/23 16:53
 */
public class ClientHandlerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        ChannelPipeline pipeline = ch.pipeline();

        /* inbound handler */
        // 添加拆包器
        pipeline.addLast(new Spilter());
        // 添加Packet解码器
        pipeline.addLast(new PacketDecoder());
        // 添加登录响应处理器
        pipeline.addLast(new LoginResponseHandler());
        // 添加消息响应处理器
        pipeline.addLast(new MessageResponseHandler());
        // 添加创建群组响应处理器
        pipeline.addLast(new CreateGroupResponseHandler());
        // 加群响应处理器
        pipeline.addLast(new JoinGroupResponseHandler());
        // 退群响应处理器
        pipeline.addLast(new QuitGroupResponseHandler());
        // 获取群成员响应处理器
        pipeline.addLast(new ListGroupMembersResponseHandler());
        // 登出响应处理器
        pipeline.addLast(new LogoutResponseHandler());

        /* outbound handler */
        pipeline.addLast(new PacketEncoder()); // 添加编码处理器
    }
}
