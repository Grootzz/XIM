package com.xim.common.util;

import com.xim.common.attribute.Attributes;
import com.xim.common.session.Session;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author noodle
 * @date 2019/6/24 18:24
 */
public class SessionUtil {

    // userId -> channel 的映射
    private static final Map<String, Channel> userIdChannelMap = new ConcurrentHashMap<>();
    // groupId -> channels group
    private static final Map<String, ChannelGroup> groupIdChannelGroupMap = new ConcurrentHashMap<>();

    /**
     * 绑定channel到userId
     */
    public static void bindSession(Session session, Channel channel) {
        /*TODO 删除使用getUserId() put */
        //userIdChannelMap.put(session.getUserId(), channel);
        userIdChannelMap.put(session.getUserName(), channel);
        channel.attr(Attributes.SESSION).set(session);
    }

    /**
     * 解绑
     */
    public static void unBindSession(Channel channel) {
        if (LoginUtil.hasLogin(channel)) {
            userIdChannelMap.remove(getSession(channel).getUserName());
            LoginUtil.logout(channel);
        }
    }

    /**
     * 获取session
     */
    public static Session getSession(Channel channel) {
        return channel.attr(Attributes.SESSION).get();
    }

    /**
     * 根据用户获取channel
     */
    public static Channel getChannel(String username) {
        return userIdChannelMap.get(username);
    }


    public static void bindChannelGroup(String groupId, ChannelGroup channelGroup) {
        groupIdChannelGroupMap.put(groupId, channelGroup);
    }

    public static ChannelGroup getChannelGroup(String groupId) {
        return groupIdChannelGroupMap.get(groupId);
    }
}
