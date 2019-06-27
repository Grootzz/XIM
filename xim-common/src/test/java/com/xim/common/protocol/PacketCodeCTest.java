package com.xim.common.protocol;

import com.xim.common.protocol.req.LoginRequestPacket;
import com.xim.common.serialize.Serializer;
import com.xim.common.serialize.impl.JSONSerializer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import org.junit.Assert;
import org.junit.Test;

public class PacketCodeCTest {

    @Test
    public void encode() {
        Serializer serializer = new JSONSerializer();
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();

//        loginRequestPacket.setVersion((byte) 1);
////        loginRequestPacket.setUserId("" + 000);
//        loginRequestPacket.setUserName("admin");
//        loginRequestPacket.setPassword("admin");
//
//        PacketCodeC packetCodeC = new PacketCodeC();
//        ByteBuf byteBuf = packetCodeC.encode((ByteBufAllocator) ByteBufAllocator.DEFAULT.ioBuffer(), loginRequestPacket);
//        Packet decodedPacket = packetCodeC.decode(byteBuf);
//
//        Assert.assertArrayEquals(serializer.serialize(loginRequestPacket), serializer.serialize(decodedPacket));
    }

    @Test
    public void decode() {
    }
}