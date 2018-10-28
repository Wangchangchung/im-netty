package wcc.netty.protocol;

/**
 * @author charse
 * @create 2018-10-29
 * @Description:
 */
public class LoginRequestPacket extends Packet {

    private Integer userId;

    private String userName;

    private String password;

    @Override
    public Byte getCommand() {
        return null;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
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