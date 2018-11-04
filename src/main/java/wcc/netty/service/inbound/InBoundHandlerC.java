package wcc.netty.service.inbound;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author charse
 * @create 2018-11-04
 * @Description:
 */
public class InBoundHandlerC extends ChannelInboundHandlerAdapter{

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("InBoundHandlerC: " + msg);
        ctx.channel().writeAndFlush(msg);
    }
}
