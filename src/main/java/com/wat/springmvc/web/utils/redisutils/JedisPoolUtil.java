package com.wat.springmvc.web.utils.redisutils;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * redis 线程池
 */
@Component
public class JedisPoolUtil {
    private final static byte[] temp_lock = new byte[1];
    private JedisPool jedisPool;

    private JedisPool JedisPoolUtil() {
        if (jedisPool == null) {
            synchronized (temp_lock) {
                if (jedisPool == null) {
                    jedisPool = new JedisPool(jedisPoolConfig(), "47.91.212.82", 6379);
                }
            }
        }
        return jedisPool;
    }

    private JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(8);
        jedisPoolConfig.setMaxTotal(20);
        jedisPoolConfig.setMaxWaitMillis(1000);
        return jedisPoolConfig;
    }

    public Jedis getJedis() {
        return JedisPoolUtil().getResource();
    }

}
