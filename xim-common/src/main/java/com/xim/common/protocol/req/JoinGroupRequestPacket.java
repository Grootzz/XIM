package com.xim.common.protocol.req;

import com.xim.common.protocol.Command;
import com.xim.common.protocol.Packet;

/**
 * 加入群聊请求
 *
 * @author noodle
 * @date 2019/6/24 21:39
 */
public class JoinGroupRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {
        return Command.JOIN_GROUP_REQUEST;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        return "JoinGroupRequestPacket{" +
                "groupId='" + groupId + '\'' +
                '}';
    }
}
