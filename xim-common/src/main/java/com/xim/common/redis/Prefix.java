package com.xim.common.redis;

/**
 * @author noodle
 * @date 2019/6/28 21:57
 */
public interface Prefix {

    /**
     * 存储在redis中的 username -> password 的username 的 key
     */
    String USER = "USER-";
}
