package wcc.netty.client;

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
public class FirstClientHandler extends ChannelInboundHandlerAdapter{


    /**
     * 该方法会在收到服务端消息的时候调用
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;

        System.out.println(new Date() + ": client receive data :"  + byteBuf.toString(Charset.forName("utf-8")));

    }

    /**
     * 该方法会在客户端建立成功之后被调用
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date() + ": client send data");

        ByteBuf buf = getByteBuf(ctx);

        //通过channel 将ByteBuf中的数据写出
        ctx.channel().writeAndFlush(buf);
    }


    /**
     * 从ChannelHandlerContext 中获取ByteBuf对象,然后往ByteBuf中写入数据
     * @param ctx
     * @return
     */
    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        //获取netty的二进制对象ByteBuf, alloc()获取到一个ByteBuf的内存管理器,这个内存管理器的作用
        //就是分配一个ByteBuf netty中的数据都是以ByteBuf为单位的
        ByteBuf byteBuf = ctx.alloc().buffer();

        byte[] bytes = "hello netty".getBytes(Charset.forName("utf-8"));

        byteBuf.writeBytes(bytes);

        return byteBuf;

    }
}
