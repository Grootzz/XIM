package com.xim.common.protocol.resp;

import com.xim.common.protocol.Command;
import com.xim.common.protocol.Packet;
import com.xim.common.session.Session;

import java.util.List;

/**
 * 获取群成员请求响应
 *
 * @author noodle
 * @date 2019/6/24 21:49
 */
public class ListGroupMembersResponsePacket extends Packet{


    private String groupId;

    private List<Session> sessionList;

    @Override
    public Byte getCommand() {

        return Command.LIST_GROUP_MEMBERS_RESPONSE;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public List<Session> getSessionList() {
        return sessionList;
    }

    public void setSessionList(List<Session> sessionList) {
        this.sessionList = sessionList;
    }

    @Override
    public String toString() {
        return "ListGroupMembersResponsePacket{" +
                "groupId='" + groupId + '\'' +
                ", sessionList=" + sessionList +
                '}';
    }
}
