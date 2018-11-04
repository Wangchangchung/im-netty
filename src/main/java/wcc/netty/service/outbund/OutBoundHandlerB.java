package wcc.netty.service.outbund;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * @author charse
 * @create 2018-11-04
 * @Description:
 */
public class OutBoundHandlerB extends ChannelOutboundHandlerAdapter{

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        System.out.println("OutBoundHandlerB: " + msg );
        super.write(ctx, msg, promise);
    }
}
