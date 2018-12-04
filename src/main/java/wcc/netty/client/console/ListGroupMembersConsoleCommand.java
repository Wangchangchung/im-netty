package wcc.netty.client.console;

import io.netty.channel.Channel;
import wcc.netty.protocol.request.ListGroupMembersRequestPacket;

import java.util.Scanner;

/**
 * @author charse
 * @create 2018-12-05
 * @Description:
 */
public class ListGroupMembersConsoleCommand implements ConsoleCommand{

    @Override
    public void exec(Scanner scanner, Channel channel) {
        ListGroupMembersRequestPacket listGroupMembersRequestPacket = new ListGroupMembersRequestPacket();

        System.out.print("输入 groupId，获取群成员列表：");
        String groupId = scanner.next();

        listGroupMembersRequestPacket.setGroupId(groupId);
        channel.writeAndFlush(listGroupMembersRequestPacket);
    }
}
