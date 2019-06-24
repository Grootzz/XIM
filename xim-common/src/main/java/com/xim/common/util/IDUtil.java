package com.xim.common.util;

import java.util.UUID;

/**
 * @author noodle
 * @date 2019/6/24 21:13
 */
public class IDUtil {

    public static String randomId() {
        return UUID.randomUUID().toString().split("-")[0];
    }
}
