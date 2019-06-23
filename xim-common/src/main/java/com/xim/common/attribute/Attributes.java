package com.xim.common.attribute;

import io.netty.util.AttributeKey;

/**
 * Channel 绑定属性
 *
 * @author noodle
 * @date 2019/6/23 19:52
 */
public interface Attributes {
    /**
     * channel 登录成功属性key
     */
    AttributeKey<Boolean> LOGON = AttributeKey.newInstance("login");

}
