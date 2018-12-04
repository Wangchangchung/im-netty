package wcc.netty.protocol.request;

import wcc.netty.protocol.Packet;

import static wcc.netty.protocol.command.Command.JOIN_GROUP_REQUEST;

public class JoinGroupRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {

        return JOIN_GROUP_REQUEST;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
