package wcc.netty.protocol.request;

import wcc.netty.protocol.Packet;

import static wcc.netty.protocol.command.Command.LIST_GROUP_MEMBERS_REQUEST;

/**
 * @author charse
 * @create 2018-12-05
 * @Description:
 */
public class ListGroupMembersRequestPacket extends Packet{

    private String groupId;

    @Override
    public Byte getCommand() {

        return LIST_GROUP_MEMBERS_REQUEST;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
