package wcc.netty.protocol.response;

import wcc.netty.protocol.Packet;

import static wcc.netty.protocol.command.Command.MESSAGE_RESPONSE;

/**
 * @author charse
 * @create 2018-11-03
 * @Description:
 */
public class MessageResponsePacket extends Packet{

    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_RESPONSE;
    }

    public MessageResponsePacket() {
    }

    public MessageResponsePacket(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
