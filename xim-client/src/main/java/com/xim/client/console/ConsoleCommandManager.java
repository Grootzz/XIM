package com.xim.client.console;

import com.xim.common.util.LoginUtil;
import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 控制台命令管理器
 *
 * @author noodle
 * @date 2019/6/24 20:48
 */
public class ConsoleCommandManager implements ConsoleCommand{

    /**
     * 命令-->命令处理器
     */
    private Map<String, ConsoleCommand> consoleCommandMap;

    public ConsoleCommandManager() {
        consoleCommandMap = new HashMap<>();

//        consoleCommandMap.put("sendToUser", new SendToUserConsoleCommand());
//        consoleCommandMap.put("logout", new LogoutConsoleCommand());
//        consoleCommandMap.put("createGroup", new CreateGroupConsoleCommand());
//        consoleCommandMap.put("joinGroup", new JoinGroupConsoleCommand());
//        consoleCommandMap.put("quitGroup", new QuitGroupConsoleCommand());
//        consoleCommandMap.put("listGroupMembers", new ListGroupMembersConsoleCommand());
//        consoleCommandMap.put("sendToGroup", new SendToGroupConsoleCommand());

//        consoleCommandMap.put(":xim",new );
        consoleCommandMap.put(":logout", new LogoutConsoleCommand());
        consoleCommandMap.put(":cg", new CreateGroupConsoleCommand());
        consoleCommandMap.put(":jg", new JoinGroupConsoleCommand());
        consoleCommandMap.put(":qg", new QuitGroupConsoleCommand());
        consoleCommandMap.put(":lqm", new ListGroupMembersConsoleCommand());
        consoleCommandMap.put(":pc", new SendToUserConsoleCommand());
        consoleCommandMap.put(":gc", new SendToGroupConsoleCommand());
    }

    @Override
    public void exec(Scanner scanner, Channel channel) {

        //  获取第一个指令
        String command = scanner.next();

        if (!LoginUtil.hasLogin(channel)) {
            return;
        }

        ConsoleCommand consoleCommand = consoleCommandMap.get(command);

        if (consoleCommand != null) {
            consoleCommand.exec(scanner, channel);
        } else {
            System.err.println("无法识别[" + command + "]指令，请重新输入!");
        }
    }
}
