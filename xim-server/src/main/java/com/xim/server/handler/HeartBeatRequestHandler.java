package com.xim.server.handler;

import com.xim.common.protocol.req.HeartBeatRequestPacket;
import com.xim.common.protocol.resp.HeartBeatResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 心跳检测请求处理器
 *
 * @author noodle
 * @date 2019/6/25 16:01
 */
public class HeartBeatRequestHandler extends SimpleChannelInboundHandler<HeartBeatRequestPacket> {

    private static Logger logger = LoggerFactory.getLogger(HeartBeatRequestHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HeartBeatRequestPacket msg) throws Exception {
        System.out.println("pong");
        logger.info(ctx.channel().id() + ": Pong");
        ctx.channel().writeAndFlush(new HeartBeatResponsePacket());
        //ctx.writeAndFlush(new HeartBeatResponsePacket());
    }
}
