package com.xim.common.serialize.impl;

import com.alibaba.fastjson.JSON;
import com.xim.common.serialize.Serializer;
import com.xim.common.serialize.SerializerAlgorithm;

/**
 * json 序列化器
 * @author noodle
 * @date 2019/6/23 15:25
 */
public class JSONSerializer implements Serializer {


    @Override
    public byte getSerializerAlgorithmId() {
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
