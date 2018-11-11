package wcc.netty.session;

/**
 * @author charse
 * @create 2018-11-11
 * @Description:
 */
public class Session {

    private String userName;

    private String userId;

    public Session(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
