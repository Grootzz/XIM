package com.xim.common.protocol.resp;

import com.xim.common.protocol.Command;
import com.xim.common.protocol.Packet;

/**
 * 登出请求响应数据包
 *
 * @author noodle
 * @date 2019/6/24 20:34
 */
public class LogoutResponsePacket extends Packet {

    /**
     * 登出状态
     */
    private boolean success;

    /**
     * 原因
     */
    private String reason;

    @Override
    public Byte getCommand() {
        return Command.LOGOUT_RESPONSE;
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
        return "LogoutResponsePacket{" +
                "success=" + success +
                ", reason='" + reason + '\'' +
                '}';
    }
}
