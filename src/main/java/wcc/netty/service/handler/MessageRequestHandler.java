package wcc.netty.service.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import wcc.netty.protocol.PacketCodec;
import wcc.netty.protocol.request.MessageRequestPacket;
import wcc.netty.protocol.response.MessageResponsePacket;

import java.util.Date;

/**
 * @author charse
 * @create 2018-11-04
 * @Description:
 */
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket>{

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket packet) throws Exception {
        //客户端发送消息
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        System.out.println(new Date() + ": accept message " + packet.getMessage());

        messageResponsePacket.setMessage("service send back [ " + packet.getMessage() + " ]");

        ctx.channel().writeAndFlush(messageResponsePacket);
    }
}
