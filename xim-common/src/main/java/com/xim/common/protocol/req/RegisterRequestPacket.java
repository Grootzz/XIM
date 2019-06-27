package com.xim.common.protocol.req;

import com.xim.common.protocol.Command;
import com.xim.common.protocol.Packet;

/**
 * 用户注册请求数据包
 *
 * @author noodle
 * @date 2019/6/27 21:10
 */
public class RegisterRequestPacket extends Packet {

    private String username;

    private String password;

    @Override
    public Byte getCommand() {
        return Command.REGISTER_REQUEST;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "RegisterRequestPacket{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
