package com.xim.server.handler;

import com.xim.common.protocol.req.RegisterRequestPacket;
import com.xim.common.protocol.resp.RegisterResponsePacket;
import com.xim.common.redis.JedisUtils;
import com.xim.common.util.IDUtil;
import com.xim.server.vo.User;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 注册请求处理器
 *
 * @author noodle
 * @date 2019/6/27 21:25
 */
public class RegisterRequestHandler extends SimpleChannelInboundHandler<RegisterRequestPacket> {

    private static Logger logger = LoggerFactory.getLogger(RegisterRequestHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RegisterRequestPacket requestPacket) throws Exception {

        String username = requestPacket.getUsername();
        String password = requestPacket.getPassword();

        RegisterResponsePacket responsePacket = new RegisterResponsePacket();

        if (JedisUtils.exist(username)) {
            responsePacket.setSuccess(false);
            responsePacket.setReason("用户名已被注册！");
        } else {
            // 写入redis
            JedisUtils.set(username, password);
            // 使用 token 关联用户
            String token = "token-" + IDUtil.randomId();
            JedisUtils.set(token, new User(username, password));

            responsePacket.setSuccess(true);
            responsePacket.setReason("您的用户名为: " + username + ", 密码为: " + password);
        }

//        ctx.channel().writeAndFlush(responsePacket);
        ctx.writeAndFlush(responsePacket);
    }
}
