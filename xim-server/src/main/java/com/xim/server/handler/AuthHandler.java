package com.xim.server.handler;

import com.xim.common.util.LoginUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 权限检验
 *
 * @author noodle
 * @date 2019/6/24 16:40
 */
public class AuthHandler extends ChannelInboundHandlerAdapter {

    private static Logger logger = LoggerFactory.getLogger(AuthHandler.class);

    /**
     * 如果没有登录，则关闭该channel，阻止后续处理
     * 如果已经登录，则移除该channel，节省资源
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        logger.info("Auth handler");
        if (!LoginUtil.hasLogin(ctx.channel())) {
            ctx.channel().close();
        } else {
            ctx.pipeline().remove(this);
            super.channelRead(ctx, msg);
        }
    }

    /**
     * 移除handler时会回调这个方法
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        if (LoginUtil.hasLogin(ctx.channel())) {
            logger.info("当前连接登录验证完毕，无需再次验证, AuthHandler 被移除");
        } else {
            logger.info("权限校验失败，关闭连接!");
        }
    }
}
