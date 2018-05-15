package com.wat.springmvc.web.utils.redisutils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

/**
 *  基础redis
 */
public class JedisUtil {

    public static Jedis jedis;

    private JedisUtil() {
    }

    static {
        if (jedis == null) {
            jedis = new Jedis("47.91.212.82", 6379);
        }

    }

    public static Jedis getSingletonJedis() {
        return jedis;
    }

    public static void main(String[] args) {

        System.out.println(jedis.get("str2"));
        jedis.close();
        System.out.println(jedis.get("str2"));
        jedis.close();

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            Pipeline pipeline = jedis.pipelined();
            for (int k = i * 100; k < (i + 1) * 100; k++) {
                pipeline.hset("key" + k, "keyfield" + k, "keyvalue" + k);
            }
            pipeline.syncAndReturnAll();

        }

        System.out.println("共计用时：：" + (System.currentTimeMillis() - startTime) + " ms");


        long startTime1 = System.currentTimeMillis();
        for (int k = 0; k < 1000; k++) {
            jedis.hset("key" + k, "keyfiled" + k, "keyvalue" + k);
            jedis.close();
        }
        System.out.println("不使用pipeline 共用时：：" + (System.currentTimeMillis() - startTime1) + " ms");


    }
}
