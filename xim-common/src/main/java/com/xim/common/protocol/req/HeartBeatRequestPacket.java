package com.xim.common.protocol.req;

import com.xim.common.protocol.Command;
import com.xim.common.protocol.Packet;

/**
 * 心跳检测请求数据包
 *
 * @author noodle
 * @date 2019/6/25 15:59
 */
public class HeartBeatRequestPacket extends Packet{

    @Override
    public Byte getCommand() {
        return Command.HEARTBEAT_REQUEST;
    }
}
