package com.xim.server.handler;

import com.xim.common.protocol.req.LoginRequestPacket;
import com.xim.common.protocol.resp.LoginResponsePacket;
import com.xim.common.redis.JedisUtils;
import com.xim.common.session.Session;
import com.xim.common.util.LoginUtil;
import com.xim.common.util.SessionUtil;
import com.xim.server.XIMServer;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.UUID;

/**
 * 客户端登录请求处理器
 *
 * @author noodle
 * @date 2019/6/24 14:10
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    private static Logger logger = LoggerFactory.getLogger(LoginRequestHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket requestPacket) throws Exception {
        logger.info("收到客户端登录请求……");

        String userName = requestPacket.getUserName();
        String password = requestPacket.getPassword();

        // 给客户端一个响应
        LoginResponsePacket responsePacket = new LoginResponsePacket();

        responsePacket.setUserName(userName);

        if (JedisUtils.exist(userName)) {
            if (JedisUtils.get(userName).equals(password)) {
                responsePacket.setSuccess(true);
                // 在redis中标记该channel已经已经经过验证
                LoginUtil.markAsLogin(ctx.channel());

                // 在内存中用session标记该channel已经登录
                Session session = new Session(userName);
                SessionUtil.bindSession(session, ctx.channel());

                logger.info("[" + userName + "]登录成功");
            } else {
                responsePacket.setSuccess(false);
                responsePacket.setReason("密码输入错误");
            }
        } else {
            responsePacket.setSuccess(false);
            responsePacket.setReason("用户名不存在");
        }


//        if (valid(requestPacket)) {
//            logger.info("登录成功!");
//            responsePacket.setSuccess(true);
//
//            // 在redis中标记该channel已经已经经过验证
//            LoginUtil.markAsLogin(ctx.channel());
//
//            String userId = randomUserId();
//            responsePacket.setUserId(userId);
//            System.out.println("[" + requestPacket.getUserName() + "]登录成功");
//            SessionUtil.bindSession(new Session(userId, requestPacket.getUserName()), ctx.channel());
//        } else {
//            responsePacket.setReason("账号密码校验失败");
//            responsePacket.setSuccess(false);
//            System.out.println(new Date() + ": 登录失败!");
//        }

        // 写数据到客户端: 登录响应
//        ctx.channel().writeAndFlush(responsePacket);
        ctx.writeAndFlush(responsePacket);
    }

    /**
     * 连接断开时解绑session
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        // 从 map 中 remove 该用户的 channel
        SessionUtil.unBindSession(ctx.channel());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
    }

    /**
     * 检验用户
     */
    private boolean valid(LoginRequestPacket requestPacket) {
        /* TODO 实现校验逻辑 */
        String userName = requestPacket.getUserName();
        String password = requestPacket.getPassword();

        if (JedisUtils.exist(userName)) {
            if (JedisUtils.get(userName).equals(password)) {
                return true;
            }
            return false;
        } else {

        }

        return true;
    }

    private static String randomUserId() {
        return UUID.randomUUID().toString().split("-")[0];
    }
}
