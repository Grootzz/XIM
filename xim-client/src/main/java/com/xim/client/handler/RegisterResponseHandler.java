package com.xim.client.handler;

import com.xim.common.protocol.resp.RegisterResponsePacket;
import com.xim.common.util.DateUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 注册请求响应处理器
 *
 * @author noodle
 * @date 2019/6/27 21:55
 */
public class RegisterResponseHandler extends SimpleChannelInboundHandler<RegisterResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RegisterResponsePacket responsePacket) throws Exception {
        if (responsePacket.isSuccess()){
            System.out.println(DateUtils.date()+responsePacket.getReason());
        }else {
            System.err.println(DateUtils.date()+responsePacket.getReason());
        }
    }
}
