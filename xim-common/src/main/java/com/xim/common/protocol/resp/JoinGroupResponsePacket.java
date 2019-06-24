package com.xim.common.protocol.resp;

import com.xim.common.protocol.Command;
import com.xim.common.protocol.Packet;

/**
 * 加入群聊请求响应
 *
 * @author noodle
 * @date 2019/6/24 21:48
 */
public class JoinGroupResponsePacket extends Packet {

    private String groupId;

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {

        return Command.JOIN_GROUP_RESPONSE;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "JoinGroupResponsePacket{" +
                "groupId='" + groupId + '\'' +
                ", success=" + success +
                ", reason='" + reason + '\'' +
                '}';
    }
}
