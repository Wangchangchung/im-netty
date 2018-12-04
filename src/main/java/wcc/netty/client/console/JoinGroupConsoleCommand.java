package wcc.netty.client.console;

import io.netty.channel.Channel;
import wcc.netty.protocol.request.JoinGroupRequestPacket;

import java.util.Scanner;

/**
 * @author charse
 * @create 2018-12-05
 * @Description:
 */
public class JoinGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        JoinGroupRequestPacket joinGroupRequestPacket = new JoinGroupRequestPacket();

        System.out.print("输入 groupId，加入群聊：");
        String groupId = scanner.next();

        joinGroupRequestPacket.setGroupId(groupId);
        channel.writeAndFlush(joinGroupRequestPacket);
    }
}
