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

    Byte LIST_GROUP_MEMBERS_REQUEST = 9;

    Byte LIST_GROUP_MEMBERS_RESPONSE = 10;

    Byte JOIN_GROUP_REQUEST = 11;

    Byte JOIN_GROUP_RESPONSE = 12;

    Byte QUIT_GROUP_REQUEST = 13;

    Byte QUIT_GROUP_RESPONSE = 14;

    Byte GROUP_MESSAGE_REQUEST = 15;

    Byte GROUP_MESSAGE_RESPONSE = 16;




}
