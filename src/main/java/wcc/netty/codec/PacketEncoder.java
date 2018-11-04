package wcc.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import wcc.netty.protocol.Packet;
import wcc.netty.protocol.PacketCodec;

/**
 * @author charse
 * @create 2018-11-04
 * @Description:
 */
public class PacketEncoder extends MessageToByteEncoder<Packet>{

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet msg, ByteBuf out) throws Exception {
        PacketCodec.INSTANCE.encode(out,  msg);
    }
}
