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
public class ListGroupMembersResponsePacket extends Packet {


    private String groupId;

    /**
     * 用户名列表
     */
    private List<String> usernameList;

    /**
     * 获取的群操作是否成功
     */
    private boolean success;

    /**
     * 附加消息
     */
    private String info;

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

    public List<String> getUsernameList() {
        return usernameList;
    }

    public void setUsernameList(List<String> usernameList) {
        this.usernameList = usernameList;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "ListGroupMembersResponsePacket{" +
                "groupId='" + groupId + '\'' +
                ", usernameList=" + usernameList +
                ", success=" + success +
                ", info='" + info + '\'' +
                '}';
    }
}
