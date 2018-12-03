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
    /**
     * 消息请求指令
     */
    Byte MESSAGE_REQUEST = 3;
    /**
     * 消息返回指令
     */
    Byte MESSAGE_RESPONSE = 4;

    Byte LOGOUT_REQUEST = 5;

    Byte LOGOUT_RESPONSE = 6;

    Byte CREATE_GROUP_REQUEST = 7;

    Byte CREATE_GROUP_RESPONSE = 8;



}
