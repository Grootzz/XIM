package com.xim.server.handler;

import com.xim.common.protocol.req.MessageRequestPacket;
import com.xim.common.protocol.resp.MessageResponsePacket;
import com.xim.common.session.Session;
import com.xim.common.util.LoginUtil;
import com.xim.common.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * 处理客户端发送的消息
 *
 * @author noodle
 * @date 2019/6/24 14:16
 */
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    private static Logger logger = LoggerFactory.getLogger(MessageRequestHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket requestPacket) throws Exception {
        logger.info("收到客户端消息: " + requestPacket.getMessage());

        // 1.拿到消息发送方的会话信息
        Session session = SessionUtil.getSession(ctx.channel());

        // 2.通过消息发送方的会话信息构造要发送的消息
        MessageResponsePacket responsePacket = new MessageResponsePacket();
        /*TODO comment*/
        //responsePacket.setFromUserId(session.getUserId());
        responsePacket.setUsername(session.getUserName());
        responsePacket.setMessage(requestPacket.getMessage());

        // 3.拿到消息接收方的 channel
        Channel channel = SessionUtil.getChannel(requestPacket.getUsername());

        // 4.将消息发送给消息接收方
//        ctx.channel().writeAndFlush(responsePacket);
        if (channel != null && LoginUtil.hasLogin(channel)) {
            channel.writeAndFlush(responsePacket);
        } else {
            System.err.println("[" + requestPacket.getUsername() + "] 不在线，发送失败!");
            responsePacket.setMessage("[" + requestPacket.getUsername() + "] 不在线，发送失败!");
            ctx.writeAndFlush(responsePacket);
        }
    }
}
