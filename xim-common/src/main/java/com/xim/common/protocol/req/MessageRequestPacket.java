package com.xim.common.protocol.req;


import com.xim.common.protocol.Command;
import com.xim.common.protocol.Packet;

/**
 * 收发消息数据包: 客户端发送至服务端的消息
 *
 * @author noodle
 * @date 2019/6/23 19:36
 */
public class MessageRequestPacket extends Packet {

    private String toUserId;

    private String message;

    public MessageRequestPacket(String toUserId, String message) {
        this.toUserId = toUserId;
        this.message = message;
    }

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    @Override
    public String toString() {
        return "MessageRequestPacket{" +
                "toUserId='" + toUserId + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
