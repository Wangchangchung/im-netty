package wcc.netty.client.console;

import io.netty.channel.Channel;
import wcc.netty.protocol.request.MessageRequestPacket;

import java.util.Scanner;

/**
 * @author charse
 * @create 2018-12-04
 * @Description:
 */
public class SendToUserConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
            System.out.print("发送消息给某个某个用户：");
            String toUserId = scanner.next();
            String message = scanner.next();
            channel.writeAndFlush(new MessageRequestPacket(toUserId, message));
    }

}
