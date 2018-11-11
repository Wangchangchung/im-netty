package wcc.netty.service.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import wcc.netty.protocol.request.LoginRequestPacket;
import wcc.netty.protocol.response.LoginResponsePacket;
import wcc.netty.session.Session;
import wcc.netty.utils.SessionUtil;

import java.util.Date;
import java.util.UUID;

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
        loginResponsePacket.setUserName(packet.getUserName());

        //假设验证成功
        if (valid(packet)){
            loginResponsePacket.setSuccess(true);
            //将session与Channel进行绑定
            //客户端进行生成 userId
            String  userId = randomUserId();
            loginResponsePacket.setUserId(userId);
            SessionUtil.bindSession(new Session(userId, packet.getUserName()), ctx.channel());

        }else {
            loginResponsePacket.setSuccess(false);
            loginResponsePacket.setReason("账号密码校验失败");
            System.out.println(new Date() +": login fail");

        }

        //对响应对象进行编码
        //ByteBuf response = PacketCodec.INSTANCE.encode(ctx.alloc(),  loginResponsePacket);
        //写数据
        ctx.channel().writeAndFlush(loginResponsePacket);
    }

    private static String randomUserId() {
        return UUID.randomUUID().toString().split("-")[0];
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        SessionUtil.unBindSession(ctx.channel());
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }

}
