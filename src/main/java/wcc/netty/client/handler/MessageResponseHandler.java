package wcc.netty.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import wcc.netty.protocol.response.MessageResponsePacket;

import java.util.Date;

/**
 * @author charse
 * @create 2018-11-04
 * @Description:
 */
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket msg) throws Exception {
        System.out.println(new Date() + "accept message from service : " + msg.getMessage());
    }
}
