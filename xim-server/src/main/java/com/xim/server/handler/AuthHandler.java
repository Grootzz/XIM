package com.xim.server.handler;

import com.xim.common.util.LoginUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 权限检验
 *
 * @author noodle
 * @date 2019/6/24 16:40
 */
public class AuthHandler extends ChannelInboundHandlerAdapter {

    /**
     * 如果没有登录，则关闭该channel，阻止后续处理
     * 如果已经登录，则移除该channel，节省资源
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
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
            //System.out.println("当前连接登录验证完毕，无需再次验证, AuthHandler 被移除");
        } else {
            //System.out.println("无登录验证，强制关闭连接!");
        }
    }
}
