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
     * 登出请求指令
     */
    Byte LOGOUT_REQUEST = 5;

    /**
     * 退出响应指令
     */
    Byte LOGOUT_RESPONSE = 6;

    /**
     * 创建群组请求指令
     */
    Byte CREATE_GROUP_REQUEST = 7;

    /**
     * 创建群组响应指令
     */
    Byte CREATE_GROUP_RESPONSE = 8;

    /**
     * 列出群成员请求指令
     */
    Byte LIST_GROUP_MEMBERS_REQUEST = 9;

    /**
     * 列出群成员响应指令
     */
    Byte LIST_GROUP_MEMBERS_RESPONSE = 10;

    /**
     * 加入群聊请求指令
     */
    Byte JOIN_GROUP_REQUEST = 11;

    /**
     * 加入群聊请求响应指令
     */
    Byte JOIN_GROUP_RESPONSE = 12;

    /**
     * 退出群聊请求指令
     */
    Byte QUIT_GROUP_REQUEST = 13;

    /**
     * 退出群聊请求响应指令
     */
    Byte QUIT_GROUP_RESPONSE = 14;

    /**
     * 消息群发请求命令
     */
    Byte GROUP_MESSAGE_REQUEST = 15;

    /**
     * 消息群发请求响应命令
     */
    Byte GROUP_MESSAGE_RESPONSE = 16;

    /**
     * 心跳检测请求命令
     */
    Byte HEARTBEAT_REQUEST = 17;

    /**
     * 心跳检测请求响应命令
     */
    Byte HEARTBEAT_RESPONSE = 18;
}
