package com.xim.server.handler;

import com.xim.common.protocol.req.HeartBeatRequestPacket;
import com.xim.common.protocol.resp.HeartBeatResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 心跳检测请求处理器
 *
 * @author noodle
 * @date 2019/6/25 16:01
 */
public class HeartBeatRequestHandler extends SimpleChannelInboundHandler<HeartBeatRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HeartBeatRequestPacket msg) throws Exception {
        ctx.channel().writeAndFlush(new HeartBeatResponsePacket());
        System.out.println("received heartbeat...");
        //ctx.writeAndFlush(new HeartBeatResponsePacket());
    }
}
