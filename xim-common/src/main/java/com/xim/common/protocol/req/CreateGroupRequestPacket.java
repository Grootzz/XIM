package com.xim.common.protocol.req;

import com.xim.common.protocol.Command;
import com.xim.common.protocol.Packet;

import java.sql.Array;
import java.util.Arrays;
import java.util.List;

/**
 * 创建群组请求数据包
 *
 * @author noodle
 * @date 2019/6/24 20:28
 */
public class CreateGroupRequestPacket extends Packet {

    /**
     * 群组成员
     */
    private List<String> userIdList;

    @Override
    public Byte getCommand() {
        return Command.CREATE_GROUP_REQUEST;
    }

    public List<String> getUserIdList() {
        return userIdList;
    }

    public void setUserIdList(List<String> userIdList) {
        this.userIdList = userIdList;
    }

    @Override
    public String toString() {
        return "CreateGroupRequestPacket{" +
                "userIdList=" + userIdList +
                '}';
    }
}
