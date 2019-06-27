package com.xim.common.redis;

import org.junit.Test;

public class JedisUtilsTest {

    @Test
    public void set() {
        JedisUtils.set("123", new Boolean(true));
        Person cax = new Person("cax");
        JedisUtils.set("cax", cax);
    }

    @Test
    public void get() {
        Boolean b = JedisUtils.get("123", Boolean.class);
        Person cax = JedisUtils.get("cax", Person.class);
        System.out.println(b);
        System.out.println(cax);
    }

    @Test
    public void delete() {
        System.out.println(JedisUtils.delete("123"));
    }

    @Test
    public void exist() {
        System.out.println(JedisUtils.exist("cax"));
    }
}