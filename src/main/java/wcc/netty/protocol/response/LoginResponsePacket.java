package wcc.netty.protocol.response;

import wcc.netty.protocol.Packet;

import static wcc.netty.protocol.command.Command.LOGIN_RESPONSE;

/**
 * @author charse
 * @create 2018-10-29
 * @Description:
 */
public class LoginResponsePacket extends Packet{

    private boolean success;

    private String message;


    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
