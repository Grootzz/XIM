package com.xim.server.handler;

import com.xim.common.protocol.req.LoginRequestPacket;
import com.xim.common.protocol.resp.LoginResponsePacket;
import com.xim.common.session.Session;
import com.xim.common.util.LoginUtil;
import com.xim.common.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;
import java.util.UUID;

/**
 * 客户端登录请求处理器
 *
 * @author noodle
 * @date 2019/6/24 14:10
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) throws Exception {
        System.out.println(new Date() + ": 收到客户端登录请求……");

        // 给客户端一个响应
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(loginRequestPacket.getVersion());
        loginResponsePacket.setUserName(loginRequestPacket.getUserName());

        if (valid(loginRequestPacket)) {
            System.out.println(new Date() + ": 登录成功!");
            loginResponsePacket.setSuccess(true);

            // 在redis中标记该channel已经已经经过验证
            LoginUtil.markAsLogin(ctx.channel());

            String userId = randomUserId();
            loginResponsePacket.setUserId(userId);
            System.out.println("[" + loginRequestPacket.getUserName() + "]登录成功");
            SessionUtil.bindSession(new Session(userId, loginRequestPacket.getUserName()), ctx.channel());
        } else {
            loginResponsePacket.setReason("账号密码校验失败");
            loginResponsePacket.setSuccess(false);
            System.out.println(new Date() + ": 登录失败!");
        }

        // 写数据到客户端: 登录响应
        ctx.channel().writeAndFlush(loginResponsePacket);
    }


    /**
     * 连接断开时解绑session
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
         SessionUtil.unBindSession(ctx.channel());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
    }

    /**
     * 检验用户
     */
    private boolean valid(LoginRequestPacket loginRequestPacket) {
        /* TODO 实现校验逻辑 */
        return true;
    }

    private static String randomUserId() {
        return UUID.randomUUID().toString().split("-")[0];
    }
}
