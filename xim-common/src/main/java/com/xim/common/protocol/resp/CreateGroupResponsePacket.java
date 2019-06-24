package com.xim.common.protocol.resp;

import com.xim.common.protocol.Command;
import com.xim.common.protocol.Packet;

import java.util.List;

/**
 * 创建群组响应数据包
 *
 * @author noodle
 * @date 2019/6/24 20:31
 */
public class CreateGroupResponsePacket extends Packet {

    /**
     * 创建成功与否
     */
    private boolean success;

    /**
     * 群组id
     */
    private String groupId;

    /**
     * 群组用户列表
     */
    private List<String> userNameList;

    @Override
    public Byte getCommand() {
        return Command.CREATE_GROUP_RESPONSE;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public List<String> getUserNameList() {
        return userNameList;
    }

    public void setUserNameList(List<String> userNameList) {
        this.userNameList = userNameList;
    }

    @Override
    public String toString() {
        return "CreateGroupResponsePacket{" +
                "success=" + success +
                ", groupId='" + groupId + '\'' +
                ", userNameList=" + userNameList +
                '}';
    }
}
