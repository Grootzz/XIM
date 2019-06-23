package com.xim.client.handler;

import com.xim.common.protocol.Packet;
import com.xim.common.protocol.PacketCodeC;
import com.xim.common.protocol.req.LoginRequestPacket;
import com.xim.common.protocol.resp.LoginResponsePacket;
import com.xim.common.protocol.resp.MessageResponsePacket;
import com.xim.common.util.LoginUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;
import java.util.UUID;

/**
 * 服务器请求处理器
 *
 * @author noodle
 * @date 2019/6/23 16:56
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {

    /**
     * channel 建立连接时回调
     * 在连接建立时向服务端发送登录数据
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date() + ": 客户端开始登录");

        // 创建登录对象
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUsername("admin");
        loginRequestPacket.setPassword("admin");

        // 编码
        ByteBuf byteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(), loginRequestPacket);

        // 向服务端写登录数据
        ctx.channel().writeAndFlush(byteBuf);
    }

    /**
     * 接收服务端返回的数据
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;

        // 解码收到的数据包
        Packet packet = PacketCodeC.INSTANCE.decode(byteBuf);

        // 登录逻辑
        if (packet instanceof LoginResponsePacket) {

            LoginResponsePacket loginResponsePacket = (LoginResponsePacket) packet;

            if (loginResponsePacket.isSuccess()) {

                System.out.println(new Date() + ": 客户端登录成功");

                // 标记 channel 完成登录
                LoginUtil.markAsLogin(ctx.channel());
            } else {
                System.out.println(new Date() + ": 客户端登录失败，原因：" + loginResponsePacket.getReason());
            }
            // 处理收到的消息
        } else if (packet instanceof MessageResponsePacket) {
            MessageResponsePacket messageResponsePacket = (MessageResponsePacket) packet;
            System.out.println(new Date() + ": 收到服务端的消息: " + messageResponsePacket.getMessage());
        }
    }

    public void print(String action, ByteBuf buffer) {
        System.out.println("after ===========" + action + "============");
        System.out.println("capacity(): " + buffer.capacity());
        System.out.println("maxCapacity(): " + buffer.maxCapacity());
        System.out.println("readerIndex(): " + buffer.readerIndex());
        System.out.println("readableBytes(): " + buffer.readableBytes());
        System.out.println("isReadable(): " + buffer.isReadable());
        System.out.println("writerIndex(): " + buffer.writerIndex());
        System.out.println("writableBytes(): " + buffer.writableBytes());
        System.out.println("isWritable(): " + buffer.isWritable());
        System.out.println("maxWritableBytes(): " + buffer.maxWritableBytes());
        System.out.println();
    }
}
