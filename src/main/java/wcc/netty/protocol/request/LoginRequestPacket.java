package wcc.netty.protocol.request;

import wcc.netty.protocol.Packet;

import static wcc.netty.protocol.command.Command.LOGIN_REQUEST;

/**
 * @author charse
 * @create 2018-10-29
 * @Description:
 */
public class LoginRequestPacket extends Packet {

    private String userId;

    private String userName;

    private String password;

    @Override
    public Byte getCommand() {
        return LOGIN_REQUEST;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
