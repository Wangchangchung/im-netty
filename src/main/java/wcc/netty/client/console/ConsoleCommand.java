package wcc.netty.client.console;


import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author charse
 * @create 2018-12-04
 * @Description:
 */
public interface ConsoleCommand {

    void exec(Scanner scanner, Channel channel);

}
