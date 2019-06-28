package com.xim.server.handler;

import com.xim.common.protocol.req.CreateGroupRequestPacket;
import com.xim.common.protocol.resp.CreateGroupResponsePacket;
import com.xim.common.session.Session;
import com.xim.common.util.IDUtil;
import com.xim.common.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.EventExecutorGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建群组请求处理器
 *
 * @author noodle
 * @date 2019/6/24 21:06
 */
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {

    private static Logger logger = LoggerFactory.getLogger(CreateGroupRequestHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket createGroupRequestPacket) throws Exception {

        // 获取客户端创建群组命令后面的参数
        List<String> usernameList = createGroupRequestPacket.getUsernameList();

        List<String> userNameList = new ArrayList<>();

        // 1. 创建一个 channel 分组, 用于存储 channel, 这就模拟了“群”
        ChannelGroup channelGroup = new DefaultChannelGroup(ctx.executor());

        // 2. 筛选出待加入群聊的用户的 channel 和 userName
        for (String username : usernameList) {
            Channel channel = SessionUtil.getChannel(username);
            if (channel != null) {
                channelGroup.add(channel);
                Session session = SessionUtil.getSession(channel);
                userNameList.add(session.getUserName());
            }
        }

        // 3. 创建群聊创建结果的响应
        String groupId = IDUtil.randomId();
        CreateGroupResponsePacket responsePacket = new CreateGroupResponsePacket();
        responsePacket.setSuccess(true);
        responsePacket.setGroupId(groupId);
        responsePacket.setUserNameList(userNameList);

        // 4. 给每个客户端发送拉群通知
        channelGroup.writeAndFlush(responsePacket);

        logger.info("群创建成功，id 为: " + responsePacket.getGroupId() + ", ");
        logger.info("群里面有：" + responsePacket.getUserNameList());

        // 5. 保存群组相关的信息
        SessionUtil.bindChannelGroup(groupId, channelGroup);
    }
}
