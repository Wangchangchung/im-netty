package wcc.netty.serialize.impl;

import com.alibaba.fastjson.JSON;
import wcc.netty.serialize.Serializer;
import wcc.netty.serialize.SerializerAlgorithm;

/**
 * @author charse
 * @create 2018-10-29
 * @Description: 使用fast JSON进行序列化操作
 */
public class JSONSerializer implements Serializer {


    @Override
    public byte getSerializerAlogrithm() {

        return SerializerAlgorithm.JSON;
    }

    @Override
    public byte[] serialize(Object object) {

        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {

        return JSON.parseObject(bytes, clazz);
    }
}
