package com.xim.common.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * 空闲状态事件处理器
 * 继承自 IdleStateHandler，定义检测到假死连接之后的逻辑
 *
 * @author noodle
 * @date 2019/6/25 15:37
 */
public class XIMIdleStateHandler extends IdleStateHandler {

    /**
     * 读空闲时间
     */
    private static final int READER_IDLE_TIME = 15;

    public XIMIdleStateHandler() {
        // 设置读空闲时间
        super(READER_IDLE_TIME, 0, 0, TimeUnit.SECONDS);
    }

    /**
     * 空闲事件处理
     *
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) throws Exception {

        System.out.println(READER_IDLE_TIME + "秒内未读到数据，关闭连接");
        ctx.channel().close();
    }
}

