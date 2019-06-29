package com.xim.client.handler;

import com.xim.common.attribute.Attributes;
import com.xim.common.protocol.resp.LoginResponsePacket;
import com.xim.common.session.Session;
import com.xim.common.util.LoginUtil;
import com.xim.common.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * LoginResponsePacket handler
 *
 * @author noodle
 * @date 2019/6/24 14:21
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket responsePacket) throws Exception {
        String userName = responsePacket.getUserName();

        if (responsePacket.isSuccess()) {
            System.out.println("[" + userName + "]登录成功");
            // 标记 channel 完成登录
            ctx.channel().attr(Attributes.LOGON).set(true);
        } else {
            System.err.println("[" + userName + "]登录失败，原因：" + responsePacket.getReason());
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.err.println("客户端连接被关闭!");
    }
}
