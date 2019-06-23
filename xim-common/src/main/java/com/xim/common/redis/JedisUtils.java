package com.xim.common.redis;

import com.alibaba.fastjson.JSON;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * redis utils
 * TODO 需要完善
 *
 * @author noodle
 * @date 2019/6/23 20:43
 */
public class JedisUtils {

    private static JedisPool pool;

    static {
        // 加载配置文件
        InputStream in = JedisUtils.class.getClassLoader().getResourceAsStream("redis.properties");
        Properties properties = new Properties();

        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 获得池子对象
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        // 最大闲置个数
        poolConfig.setMaxIdle(Integer.parseInt(properties.get("redis.maxIdle").toString()));
        // 最小闲置个数
        poolConfig.setMinIdle(Integer.parseInt(properties.get("redis.minIdle").toString()));
        // 最大连接数
        poolConfig.setMaxTotal(Integer.parseInt(properties.get("redis.maxTotal").toString()));

        pool = new JedisPool(poolConfig, properties.getProperty("redis.url"), Integer.parseInt(properties.get("redis.port").toString()));
    }


    public static <T> void set(String key, T value) {
        Jedis jedis = pool.getResource();
        String val = JSON.toJSONString(value);
        jedis.set(key, val);

        jedis.close();
    }

    public static <T> T get(String key, Class<T> clazz) {
        Jedis jedis = pool.getResource();
        String val = jedis.get(key);

        T result;

        if ((clazz == int.class) || (clazz == Integer.class))
            result = (T) Integer.valueOf(val);
        else if ((clazz == long.class) || (clazz == Long.class))
            result = (T) Long.valueOf(val);
        else if (clazz == boolean.class || clazz == Boolean.class)
            result = (T) Boolean.valueOf(val);
        else if (clazz == String.class)
            result = (T) val;
        else
            result = JSON.toJavaObject(JSON.parseObject(val), clazz);

        jedis.close();

        return result;
    }

}
