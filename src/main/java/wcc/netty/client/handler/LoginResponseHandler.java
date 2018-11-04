package wcc.netty.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import wcc.netty.protocol.request.LoginRequestPacket;
import wcc.netty.protocol.response.LoginResponsePacket;
import wcc.netty.utils.LoginUtil;

import java.util.Date;
import java.util.UUID;

/**
 * @author charse
 * @create 2018-11-04
 * @Description:
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket>{

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //创建登录对象
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserName("xiaoming");
        loginRequestPacket.setPassword("hahaha");
        loginRequestPacket.setUserId(UUID.randomUUID().toString());

        //写数据
        ctx.channel().writeAndFlush(loginRequestPacket);

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket msg) throws Exception {

        if (msg.isSuccess()){
            System.out.println(new Date() + ": client login success");
            LoginUtil.markAsLogin(ctx.channel());
        }else {
            System.out.println(new Date() + ": client login fail");
        }

    }
}
