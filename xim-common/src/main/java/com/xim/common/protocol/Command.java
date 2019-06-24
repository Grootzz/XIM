package com.xim.common.protocol;

/**
 * 客户端与服务端的交互命令
 *
 * @author noodle
 * @date 2019/6/23 14:59
 */
public interface Command {
    /**
     * 登录请求指令
     */
    Byte LOGIN_REQUEST = 1;

    /**
     * 登录响应指令
     */
    Byte LOGIN_RESPONSE = 2;

    /**
     * 客户端发送信息指令
     */
    Byte MESSAGE_REQUEST = 3;

    /**
     * 服务端回复消息指令
     */
    Byte MESSAGE_RESPONSE = 4;

    /**
     * 登出请求
     */
    Byte LOGOUT_REQUEST = 5;

    /**
     * 退出响应
     */
    Byte LOGOUT_RESPONSE = 6;

    /**
     * 创建群组请求
     */
    Byte CREATE_GROUP_REQUEST = 7;

    /**
     * 创建群组响应
     */
    Byte CREATE_GROUP_RESPONSE = 8;
}
