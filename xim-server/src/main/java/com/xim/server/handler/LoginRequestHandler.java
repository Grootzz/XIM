package com.xim.server.handler;

import com.xim.common.protocol.PacketCodeC;
import com.xim.common.protocol.req.LoginRequestPacket;
import com.xim.common.protocol.resp.LoginResponsePacket;
import com.xim.common.util.LoginUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.EventExecutorGroup;

import java.util.Date;

/**
 * 客户端登录请求处理器
 *
 * @author noodle
 * @date 2019/6/24 14:10
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket msg) throws Exception {
        System.out.println(new Date() + ": 收到客户端登录请求……");

        // 给客户端一个响应
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(msg.getVersion());
        if (valid(msg)) {
            System.out.println(new Date() + ": 登录成功!");
            loginResponsePacket.setSuccess(true);
            // 标记该channel已经已经经过验证
            LoginUtil.markAsLogin(ctx.channel());
        } else {
            loginResponsePacket.setReason("账号密码校验失败");
            loginResponsePacket.setSuccess(false);
            System.out.println(new Date() + ": 登录失败!");
        }

        // 写数据到客户端: 登录响应
        ctx.channel().writeAndFlush(loginResponsePacket);
    }

    /**
     * 检验用户
     */
    private boolean valid(LoginRequestPacket loginRequestPacket) {
        /* TODO 实现校验逻辑 */
        return true;
    }
}
