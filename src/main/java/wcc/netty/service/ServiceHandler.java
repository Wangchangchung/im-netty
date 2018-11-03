package wcc.netty.service;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import wcc.netty.protocol.Packet;
import wcc.netty.protocol.PacketCodec;
import wcc.netty.protocol.request.LoginRequestPacket;
import wcc.netty.protocol.request.MessageRequestPacket;
import wcc.netty.protocol.response.LoginResponsePacket;
import wcc.netty.protocol.response.MessageResponsePacket;

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

        ByteBuf byteBuf = (ByteBuf) msg;

        //对客户端发送的数据进行解码
        Packet packet = PacketCodec.INSTANCE.decode(byteBuf);

        if (packet instanceof LoginRequestPacket){
            System.out.println(new Date() + ": client start login ...");
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
        }else if (packet instanceof MessageRequestPacket){
            //客户端发送消息
            MessageRequestPacket messageRequestPacket = (MessageRequestPacket) packet;
            MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
            System.out.println(new Date() + ": accept message " + messageRequestPacket.getMessage());

            messageResponsePacket.setMessage("service send back [ " + messageRequestPacket.getMessage() + " ]");
            ByteBuf responseByteBuf = PacketCodec.INSTANCE.encode(ctx.alloc(), messageResponsePacket);
            ctx.channel().writeAndFlush(responseByteBuf);
        }

    }


    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }


}
