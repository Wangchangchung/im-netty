package wcc.netty.service;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * @author charse
 * @create 2018-10-28
 * @Description:
 */
public class FirstServiceHandler extends ChannelInboundHandlerAdapter{


    /**
     * 收到客户端发送的消息被调用
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;

        System.out.println(new Date() + ": service receive data :" + byteBuf.toString(Charset.forName("utf-8")));

        //向客户端发送数据

        ByteBuf byBuf =  getByteBuf(ctx);

        ctx.channel().writeAndFlush(byBuf);

    }


    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        ByteBuf byteBuf = ctx.alloc().buffer();

        byte[]  bytes = "hello client, i am service".getBytes(Charset.forName("utf-8"));

        byteBuf.writeBytes(bytes);

        return byteBuf;

    }


}
