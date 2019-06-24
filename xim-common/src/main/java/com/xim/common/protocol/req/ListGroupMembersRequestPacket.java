package com.xim.common.protocol.req;

import com.xim.common.protocol.Command;
import com.xim.common.protocol.Packet;

/**
 * 列出群成员请求
 *
 * @author noodle
 * @date 2019/6/24 21:46
 */
public class ListGroupMembersRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {
        return Command.LIST_GROUP_MEMBERS_REQUEST;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        return "ListGroupMembersRequestPacket{" +
                "groupId='" + groupId + '\'' +
                '}';
    }
}
