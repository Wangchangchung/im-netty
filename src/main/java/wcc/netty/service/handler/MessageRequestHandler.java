package wcc.netty.service.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import wcc.netty.protocol.request.MessageRequestPacket;
import wcc.netty.protocol.response.MessageResponsePacket;
import wcc.netty.session.Session;
import wcc.netty.utils.SessionUtil;

/**
 * @author charse
 * @create 2018-11-04
 * @Description:
 */
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket>{

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket packet) throws Exception {
            // 1.拿到消息发送方的会话信息
            Session session = SessionUtil.getSession(ctx.channel());

            //2.通过消息发送方的回话信息,构造需要发的消息
            MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
            messageResponsePacket.setFromUserId(session.getUserId());
            messageResponsePacket.setFromUserName(session.getUserName());
            messageResponsePacket.setMessage(packet.getMessage());

            // 3.拿到消息接收方的 channel
            Channel toUserChannel = SessionUtil.getChannel(packet.getToUserId());

            // 4.将消息发送给消息接收方9fd65fc5
            if (toUserChannel != null && SessionUtil.hasLogin(toUserChannel)) {
                toUserChannel.writeAndFlush(messageResponsePacket);
            } else {
                System.err.println("[" + packet.getToUserId() + "] 不在线，发送失败!");
            }
    }
}
