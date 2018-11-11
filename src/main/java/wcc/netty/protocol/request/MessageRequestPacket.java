package wcc.netty.protocol.request;

import wcc.netty.protocol.Packet;

import static wcc.netty.protocol.command.Command.MESSAGE_REQUEST;

/**
 * @author charse
 * @create 2018-11-03
 * @Description:
 */
public class MessageRequestPacket extends Packet{

    private String toUserId;

    private String message;


    public MessageRequestPacket(String toUserId, String message){
        this.toUserId = toUserId;
        this.message = message;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }

    public MessageRequestPacket() {
    }

    public MessageRequestPacket(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
