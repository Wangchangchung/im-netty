package wcc.netty.client.console;

import io.netty.channel.Channel;
import wcc.netty.protocol.request.GroupMessageRequestPacket;

import java.util.Scanner;

/**
 * @author charse
 * @create 2018-12-05
 * @Description:
 */
public class SendToGroupConsoleCommand implements ConsoleCommand{

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("发送消息给某个某个群组：");

        String toGroupId = scanner.next();
        String message = scanner.next();
        channel.writeAndFlush(new GroupMessageRequestPacket(toGroupId, message));
    }
}
