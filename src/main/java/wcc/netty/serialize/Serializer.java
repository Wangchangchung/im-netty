package wcc.netty.serialize;

import wcc.netty.serialize.impl.JSONSerializer;

/**
 * @author charse
 * @create 2018-10-29
 * @Description:
 */
public interface Serializer {

    /**
     * 默认的序列化方法方式, fast JSON的方式
     */
    Serializer DEFAULT = new JSONSerializer();

    /**
     * 序列化算法
     * @return
     */
    byte getSerializerAlogrithm();

    /**
     * java 对象转换成二进制
     * @param object
     * @return
     */
    byte[] serialize(Object object);

    /**
     * 二进制转换成Java对象
     * @param clazz
     * @param bytes
     * @param <T>
     * @return
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);


}
