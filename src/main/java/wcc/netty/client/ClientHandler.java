package wcc.netty.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import wcc.netty.protocol.Packet;
import wcc.netty.protocol.request.LoginRequestPacket;
import wcc.netty.protocol.PacketCodec;
import wcc.netty.protocol.response.LoginResponsePacket;

import java.util.Date;
import java.util.UUID;

/**
 * @author charse
 * @create 2018-10-29
 * @Description:
 */
public class ClientHandler extends ChannelInboundHandlerAdapter{



    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        System.out.println(new Date() + ": client start login");

        //创建登录对象
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserName("xiaoming");
        loginRequestPacket.setPassword("hahaha");
        loginRequestPacket.setUserId(UUID.randomUUID().toString());

        //编码
        ByteBuf byteBuf = PacketCodec.INSTANCE.encode(ctx.alloc(),loginRequestPacket);

        //写数据
        ctx.channel().writeAndFlush(byteBuf);

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(new Date() + ": client read ");

        ByteBuf byteBuf = (ByteBuf) msg;

        Packet packet = PacketCodec.INSTANCE.decode(byteBuf);

        if (packet instanceof LoginResponsePacket){
            LoginResponsePacket loginResponsePacket = (LoginResponsePacket) packet;

            if (loginResponsePacket.isSuccess()){
                System.out.println(new Date() + ": client login success !");
            }else {
                System.out.println(new Date() + ": client login fail message :" +loginResponsePacket.getMessage());
            }
        }


    }
}
