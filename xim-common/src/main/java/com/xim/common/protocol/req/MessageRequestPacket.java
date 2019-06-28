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

    private String username;

    private String message;

    public MessageRequestPacket() {
    }

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }

    public MessageRequestPacket(String username, String message) {
        this.username = username;
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MessageRequestPacket{" +
                "username='" + username + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
