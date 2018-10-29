package wcc.netty.service;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import wcc.netty.protocol.Packet;
import wcc.netty.protocol.PacketCodec;
import wcc.netty.protocol.request.LoginRequestPacket;
import wcc.netty.protocol.response.LoginResponsePacket;

import java.util.Date;

/**
 * @author charse
 * @create 2018-10-29
 * @Description:
 */
public class ServiceHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(new Date() + ": client start login ...");

        ByteBuf byteBuf = (ByteBuf) msg;

        //对客户端发送的数据进行解码
        Packet packet = PacketCodec.INSTANCE.decode(byteBuf);

        if (packet instanceof LoginRequestPacket){
            //登录流程
            LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;
            //登录响应对象
            LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
            loginResponsePacket.setVesion(packet.getVesion());

            //假设验证成功
            if (valid(loginRequestPacket)){
                loginResponsePacket.setSuccess(true);
                System.out.println(new Date() + ": login success");

            }else {
                loginResponsePacket.setSuccess(false);
                loginResponsePacket.setMessage("login fail password wrong");

                System.out.println(new Date() +": login fail");
            }

            //对响应对象进行编码
            ByteBuf response = PacketCodec.INSTANCE.encode(ctx.alloc(),  loginResponsePacket);
            //写数据
            ctx.channel().writeAndFlush(response);


        }

    }


    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }

}
