package wcc.netty.protocol.request;

import lombok.Data;
import wcc.netty.protocol.Packet;

import java.util.List;

import static wcc.netty.protocol.command.Command.CREATE_GROUP_REQUEST;


public class CreateGroupRequestPacket extends Packet {

    private List<String> userIdList;

    @Override
    public Byte getCommand() {

        return CREATE_GROUP_REQUEST;
    }

    public List<String> getUserIdList() {
        return userIdList;
    }

    public void setUserIdList(List<String> userIdList) {
        this.userIdList = userIdList;
    }
}
