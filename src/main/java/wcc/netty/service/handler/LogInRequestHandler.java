package wcc.netty.service.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import wcc.netty.protocol.request.LoginRequestPacket;
import wcc.netty.protocol.response.LoginResponsePacket;

import java.util.Date;

/**
 * @author charse
 * @create 2018-11-04
 * @Description:
 */
public class LogInRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket>{

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket packet) throws Exception {

        System.out.println(new Date() + ": client start login ...");
        //登录流程
        //登录响应对象
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVesion(packet.getVesion());

        //假设验证成功
        if (valid(packet)){
            loginResponsePacket.setSuccess(true);
            System.out.println(new Date() + ": login success");

        }else {
            loginResponsePacket.setSuccess(false);
            loginResponsePacket.setMessage("login fail password wrong");

            System.out.println(new Date() +": login fail");
        }

        //对响应对象进行编码
        //ByteBuf response = PacketCodec.INSTANCE.encode(ctx.alloc(),  loginResponsePacket);
        //写数据
        ctx.channel().writeAndFlush(loginResponsePacket);
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }

}
