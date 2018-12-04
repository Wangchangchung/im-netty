package wcc.netty.protocol.request;

import wcc.netty.protocol.Packet;

import static wcc.netty.protocol.command.Command.GROUP_MESSAGE_REQUEST;

/**
 * @author charse
 * @create 2018-12-05
 * @Description:
 */
public class GroupMessageRequestPacket extends Packet{

    private String toGroupId;
    private String message;

    public GroupMessageRequestPacket(String toGroupId, String message) {
        this.toGroupId = toGroupId;
        this.message = message;
    }

    @Override
    public Byte getCommand() {
        return GROUP_MESSAGE_REQUEST;
    }

    public String getToGroupId() {
        return toGroupId;
    }

    public void setToGroupId(String toGroupId) {
        this.toGroupId = toGroupId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
