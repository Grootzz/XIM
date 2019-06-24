package com.xim.server.handler;

import com.xim.common.codec.PacketDecoder;
import com.xim.common.codec.PacketEncoder;
import com.xim.common.codec.Spilter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
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
        pipeline.addLast(new Spilter());
        pipeline.addLast(new PacketDecoder());
        pipeline.addLast(new LoginRequestHandler());
        pipeline.addLast(new MessageRequestHandler());

        /* outbound handler */
        pipeline.addLast(new PacketEncoder());
    }



}
