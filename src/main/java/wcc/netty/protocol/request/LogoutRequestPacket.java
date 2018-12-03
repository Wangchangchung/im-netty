package wcc.netty.protocol.request;

import lombok.Data;
import wcc.netty.protocol.Packet;

import static wcc.netty.protocol.command.Command.LOGOUT_REQUEST;


public class LogoutRequestPacket extends Packet {
    @Override
    public Byte getCommand() {

        return LOGOUT_REQUEST;
    }
}
