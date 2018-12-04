package wcc.netty.client.console;

import io.netty.channel.Channel;
import wcc.netty.protocol.request.LogoutRequestPacket;

import java.util.Scanner;

/**
 * @author charse
 * @create 2018-12-04
 * @Description:
 */
public class LogoutConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        LogoutRequestPacket logoutRequestPacket = new LogoutRequestPacket();
        channel.writeAndFlush(logoutRequestPacket);
    }
}
