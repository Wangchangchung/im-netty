package wcc.netty.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import wcc.netty.protocol.response.LoginResponsePacket;
import wcc.netty.session.Session;
import wcc.netty.utils.SessionUtil;


/**
 * @author charse
 * @create 2018-11-04
 * @Description:
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket>{

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        System.out.println("客户端连接被关闭!");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket msg) throws Exception {

        String userId = msg.getUserId();
        String userName = msg.getUserName();

        if (msg.isSuccess()) {
            System.out.println("[" + userName + "]登录成功，userId 为: " + msg.getUserId());
            SessionUtil.bindSession(new Session(userId, userName), ctx.channel());
        } else {
            System.out.println("[" + userName + "]登录失败，原因：" + msg.getReason());
        }


    }
}
