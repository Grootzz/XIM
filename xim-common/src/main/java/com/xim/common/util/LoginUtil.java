package com.xim.common.util;

import com.xim.common.attribute.Attributes;
import io.netty.channel.Channel;

/**
 * 登录验证工具
 *
 * @author noodle
 * @date 2019/6/23 19:49
 */
public class LoginUtil {

    /**
     * 标记 channel 登录成功
     *
     * @param channel
     */
    public static void markAsLogin(Channel channel) {
        channel.attr(Attributes.LOGON).set(true);
    }

    /**
     * 检查通道是否登录成功
     *
     * @param channel
     * @return
     */
    public static boolean hasLogin(Channel channel) {
        Boolean login = channel.attr(Attributes.LOGON).get();
        return login;
    }
}
