package com.xim.client.handler;

import com.xim.common.protocol.req.HeartBeatRequestPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.TimeUnit;

/**
 * 定时心跳处理器
 * 因为心跳包对数据没有要求，所以继承 ChannelInboundHandlerAdapter
 *
 * @author noodle
 * @date 2019/6/25 15:49
 */
public class HeartBeatTimerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 心跳间隔
     */
    private static final int HEARTBEAT_INTERVAL = 5;

    /**
     * 第一次建立连接时，就开始周期地心跳检测
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("ping...");
        // 定时发送心跳包
        scheduleSendHeartBeat(ctx);
        super.channelActive(ctx);
    }

    private void scheduleSendHeartBeat(ChannelHandlerContext ctx) {
        ctx.executor().schedule(() -> {

            if (ctx.channel().isActive()) {
                //ctx.writeAndFlush(new HeartBeatRequestPacket());
                ctx.channel().writeAndFlush(new HeartBeatRequestPacket());
                scheduleSendHeartBeat(ctx);
            }

        }, HEARTBEAT_INTERVAL, TimeUnit.SECONDS);
    }
}
