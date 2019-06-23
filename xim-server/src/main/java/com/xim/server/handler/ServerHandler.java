package com.xim.server.handler;

import com.xim.common.protocol.Packet;
import com.xim.common.protocol.PacketCodeC;
import com.xim.common.protocol.req.LoginRequestPacket;
import com.xim.common.protocol.req.MessageRequestPacket;
import com.xim.common.protocol.resp.LoginResponsePacket;
import com.xim.common.protocol.resp.MessageResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

/**
 * 客户端请求处理器
 *
 * @author noodle
 * @date 2019/6/23 18:23
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 处理客户端传递的数据
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ByteBuf requestByteBuf = (ByteBuf) msg;

        // 解码收到的数据包
        Packet packet = PacketCodeC.INSTANCE.decode(requestByteBuf);

        // 处理登录
        if (packet instanceof LoginRequestPacket) {
            System.out.println(new Date() + ": 收到客户端登录请求……");
            // 登录流程
            LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;

            // 给客户端一个响应
            LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
            loginResponsePacket.setVersion(packet.getVersion());
            if (valid(loginRequestPacket)) {
                loginResponsePacket.setSuccess(true);
                System.out.println(new Date() + ": 登录成功!");
            } else {
                loginResponsePacket.setReason("账号密码校验失败");
                loginResponsePacket.setSuccess(false);
                System.out.println(new Date() + ": 登录失败!");
            }

            // 写数据到客户端: 登录响应
            ByteBuf responseByteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(), loginResponsePacket);
            ctx.channel().writeAndFlush(responseByteBuf);

            // 处理消息
        } else if (packet instanceof MessageRequestPacket) {

            MessageRequestPacket messageRequestPacket = (MessageRequestPacket) packet;
            System.out.println(new Date() + ": 收到客户端消息: " + messageRequestPacket.getMessage());

            // 生成相应消息
            MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
            messageResponsePacket.setMessage("服务端回复【" + messageRequestPacket.getMessage() + "】");

            // 写数据到客户端
            ByteBuf byteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(), messageResponsePacket);
            ctx.channel().writeAndFlush(byteBuf);
        }
    }

    /**
     * 检验用户
     */
    private boolean valid(LoginRequestPacket loginRequestPacket) {
        /* TODO 实现校验逻辑 */
        return true;
    }
}
