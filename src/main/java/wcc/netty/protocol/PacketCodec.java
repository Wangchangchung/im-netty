package wcc.netty.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import wcc.netty.protocol.request.LoginRequestPacket;
import wcc.netty.protocol.request.MessageRequestPacket;
import wcc.netty.protocol.response.LoginResponsePacket;
import wcc.netty.protocol.response.MessageResponsePacket;
import wcc.netty.serialize.Serializer;
import wcc.netty.serialize.impl.JSONSerializer;

import java.util.HashMap;
import java.util.Map;

import static wcc.netty.protocol.command.Command.*;

/**
 * @author charse
 * @create 2018-10-29
 * @Description:
 */
public class PacketCodec {

    /**
     * 协议包魔数
     */
    private static final int MAGIC_NUMBER = 0x12345678;

    public static final PacketCodec INSTANCE = new PacketCodec();

    private final Map<Byte, Class<? extends Packet>> packetTypeMap;

    private final Map<Byte, Serializer> serializerMap;

    private PacketCodec(){
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(LOGIN_REQUEST,LoginRequestPacket.class);
        packetTypeMap.put(LOGIN_RESPONSE, LoginResponsePacket.class);
        packetTypeMap.put(MESSAGE_REQUEST, MessageRequestPacket.class);
        packetTypeMap.put(MESSAGE_RESPONSE, MessageResponsePacket.class);

        serializerMap = new HashMap<>();
        Serializer serializer = new JSONSerializer();
        serializerMap.put(serializer.getSerializerAlogrithm(), serializer);
    }


    /**
     * 对数据进行编码
     * @param packet
     * @return
     */
    public ByteBuf encode(ByteBufAllocator byteBufAllocator, Packet packet){
        //1. 创建ByteBuf对象
        ByteBuf byteBuf = byteBufAllocator.ioBuffer();
        //2. 序列化Java对象 (对象的数据)
        byte[] bytes = Serializer.DEFAULT.serialize(packet);

        //3. 实际编码的过程
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVesion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlogrithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);

        return byteBuf;
    }



    public Packet decode(ByteBuf byteBuf){
        //跳过魔数
        byteBuf.skipBytes(4);

        //跳过版本号
        byteBuf.skipBytes(1);

        //序列化算法
        byte serializeAlgorithm = byteBuf.readByte();

        //指令
        byte command = byteBuf.readByte();

        //数据包长度
        int length = byteBuf.readInt();

        byte[] bytes  = new  byte[length];
        byteBuf.readBytes(bytes);


        Class<? extends Packet> requestType = getRequestType(command);
        Serializer serializer = getSerializer(serializeAlgorithm);

        if (requestType != null && serializer != null) {
            return serializer.deserialize(requestType, bytes);
        }

        return null;

    }

    private Serializer getSerializer(byte serializeAlgorithm) {

        return serializerMap.get(serializeAlgorithm);
    }

    private Class<? extends Packet> getRequestType(byte command) {

        return packetTypeMap.get(command);
    }





}
