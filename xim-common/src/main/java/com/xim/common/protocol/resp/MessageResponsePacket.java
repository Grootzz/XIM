package com.xim.common.protocol.resp;

import com.xim.common.protocol.Command;
import com.xim.common.protocol.Packet;

/**
 * 收发消息数据包: 服务端发送至客户端的消息
 *
 * @author noodle
 * @date 2019/6/23 19:37
 */
public class MessageResponsePacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_RESPONSE;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MessageResponsePacket{" +
                "message='" + message + '\'' +
                '}';
    }
}
