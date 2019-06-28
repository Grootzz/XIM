package com.xim.client.handler;

import com.xim.common.protocol.resp.LogoutResponsePacket;
import com.xim.common.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.EventExecutorGroup;

/**
 * 登出响应处理器
 *
 * @author noodle
 * @date 2019/6/24 20:58
 */
public class LogoutResponseHandler extends SimpleChannelInboundHandler<LogoutResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutResponsePacket responsePacket) throws Exception {

        SessionUtil.unBindSession(ctx.channel());

        System.out.println(responsePacket.getReason());
    }
}
