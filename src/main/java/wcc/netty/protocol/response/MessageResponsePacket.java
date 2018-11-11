package wcc.netty.protocol.response;

import wcc.netty.protocol.Packet;

import static wcc.netty.protocol.command.Command.MESSAGE_RESPONSE;

/**
 * @author charse
 * @create 2018-11-03
 * @Description:
 */
public class MessageResponsePacket extends Packet{

    private String fromUserId;

    private String fromUserName;

    private String message;

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

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
