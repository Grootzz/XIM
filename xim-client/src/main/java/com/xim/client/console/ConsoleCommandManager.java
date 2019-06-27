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
public class ConsoleCommandManager implements ConsoleCommand {

    /**
     * 命令-->命令处理器
     */
    private Map<String, ConsoleCommand> consoleCommandMap;

    /**
     * 当前控制台命令
     */
    private ConsoleCommand currentConsoleCommand;

    public ConsoleCommandManager() {
        consoleCommandMap = new HashMap<>();

        consoleCommandMap.put(":reg", new RegisterConsoleCommand());
        consoleCommandMap.put(":login", new LoginConsoleCommand());
        consoleCommandMap.put(":logout", new LogoutConsoleCommand());
        consoleCommandMap.put(":cg", new CreateGroupConsoleCommand());
        consoleCommandMap.put(":jg", new JoinGroupConsoleCommand());
        consoleCommandMap.put(":qg", new QuitGroupConsoleCommand());
        consoleCommandMap.put(":lgm", new ListGroupMembersConsoleCommand());
        consoleCommandMap.put(":pc", new SendToUserConsoleCommand());
        consoleCommandMap.put(":gc", new SendToGroupConsoleCommand());
    }

    @Override
    public void exec(Scanner scanner, Channel channel) {

        System.out.print(">");
        String consoleIn = scanner.nextLine();
        if (!LoginUtil.hasLogin(channel)) {
            return;
        }

        ConsoleCommand consoleCommand = consoleCommandMap.get(consoleIn);

        if (consoleCommand != null) {
            consoleCommand.exec(scanner, channel);
        } else {
            System.err.println("无法识别[" + consoleIn + "]指令，请重新输入!");
        }
    }

    @Override
    public void exec(String statement, Channel channel) {

        if (containsCmd(statement)) {
            currentConsoleCommand.exec(statement, channel);
        } else {
            System.err.println("无法识别[" + statement + "]指令，请重新输入!");
        }
    }

    /**
     * 命令解析
     */
    public boolean containsCmd(String cmdstr) {

        String[] strings = cmdstr.split(" ");

        // 获取命令
        String cmd = strings[0];

        if (consoleCommandMap.get(cmd) != null) {
            currentConsoleCommand = consoleCommandMap.get(cmd);
            return true;
        }

        return false;
    }

}
