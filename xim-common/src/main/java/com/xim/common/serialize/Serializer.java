package com.xim.common.serialize;

import com.xim.common.serialize.impl.JSONSerializer;

/**
 * Java 对象序列化操作
 *
 * @author noodle
 * @date 2019/6/23 15:19
 */
public interface Serializer {
    /**
     * 默认系列化器：json序列化器
     */
    Serializer DEFAULT = new JSONSerializer();

    /**
     * 序列化算法
     *
     * @return
     */
    byte getSerializerAlgorithmId();

    /**
     * java 对象转换成二进制
     *
     * @param object
     * @return
     */
    byte[] serialize(Object object);

    /**
     * 二进制转换成 java 对象
     *
     * @param clazz
     * @param bytes
     * @param <T>
     * @return
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);
}
