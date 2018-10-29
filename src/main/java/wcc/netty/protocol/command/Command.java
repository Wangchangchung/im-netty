package wcc.netty.protocol.command;

/**
 * @author charse
 * @create 2018-10-29
 * @Description: 指令
 */
public interface Command {
    /**
     * 登录操作指令
     */
    Byte LOGIN_REQUEST = 1;
    /**
     * 登录返回指令
     */
    Byte LOGIN_RESPONSE = 2;
}
