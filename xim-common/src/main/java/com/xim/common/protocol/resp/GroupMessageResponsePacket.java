package com.xim.common.protocol.resp;

import com.xim.common.protocol.Command;
import com.xim.common.protocol.Packet;
import com.xim.common.session.Session;

/**
 * 发送消息到群的请求响应数据包
 *
 * @author noodle
 * @date 2019/6/25 14:51
 */
public class GroupMessageResponsePacket extends Packet{

    private String fromGroupId;

    private Session fromUser;

    private String message;

    @Override
    public Byte getCommand() {
        return Command.GROUP_MESSAGE_RESPONSE;
    }

    public String getFromGroupId() {
        return fromGroupId;
    }

    public void setFromGroupId(String fromGroupId) {
        this.fromGroupId = fromGroupId;
    }

    public Session getFromUser() {
        return fromUser;
    }

    public void setFromUser(Session fromUser) {
        this.fromUser = fromUser;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "GroupMessageResponsePacket{" +
                "fromGroupId='" + fromGroupId + '\'' +
                ", fromUser=" + fromUser +
                ", message='" + message + '\'' +
                '}';
    }
}
