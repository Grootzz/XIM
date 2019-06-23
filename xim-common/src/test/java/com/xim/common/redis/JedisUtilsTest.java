package com.xim.common.redis;

import org.junit.Test;

import static org.junit.Assert.*;

public class JedisUtilsTest {

    @Test
    public void set() {
        JedisUtils.set("123", new Boolean(true));
    }

    @Test
    public void get() {
        Boolean b = JedisUtils.get("123", Boolean.class);
        System.out.println(b);
    }
}