package com.xim.common.protocol;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 客户端与服务端通信的负载, 即数据包
 * 魔数：4 bytes
 * 版本号：1 byte
 * 序列化算法：1 byte
 * 指令：1 byte
 * 数据长度：4 bytes
 * 数据：N bytes
 *
 * @author noodle
 * @date 2019/6/23 14:52
 */
public abstract class Packet {

    /**
     * 协议版本；默认为 1
     */
    @JSONField(serialize = false, deserialize = false)
    private Byte version = 1;

    /**
     * 获取指令
     *
     * @return
     */
    @JSONField(serialize = false)
    public abstract Byte getCommand();

    public Byte getVersion() {
        return version;
    }

    public void setVersion(Byte version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Packet{" +
                "version=" + version +
                '}';
    }
}
