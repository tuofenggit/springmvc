package com.wat.springmvc.web.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.concurrent.atomic.AtomicInteger;

public class LockUtil {


    // 获取redis
    static JedisPool jedisPool;

    static {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(100);
        config.setMaxIdle(5);
        config.setMaxWaitMillis(1000);
        config.setTestOnBorrow(false);

        // 构造池
        jedisPool = new JedisPool(config, "47.91.212.82", 6379, 1000);
    }

    // redis键值前缀标识
    private final static String PREFIX = "LOCK-FLAG-";
    // 默认获取锁的等待时间
    private final static Integer WAITTIME = 200;

    /**
     * 获取锁
     *
     * @param uid：锁的唯一标识
     * @param expire：锁自动释放时间
     * @return true：获得锁；false:锁已被占用
     */
    public static Boolean getLock(String uid, Integer expire) {
        if (expire < 0) {
            return false;
        }
        Long beginTime = System.currentTimeMillis();
        do {
            Jedis jedis = jedisPool.getResource();

            //防止程序出错设置键值不失效
            if (jedis.ttl(PREFIX + uid) == -1) {
                jedis.expire(PREFIX + uid, expire);
            }

            Long res = jedis.incr(PREFIX + uid);
            System.out.println("res :::" + res);

            if (res == 1) {
                jedis.expire(PREFIX + uid, expire);

                if (jedis != null) {
                    try {
                        jedis.close();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }

                return true;
            }

            if (jedis != null) {
                try {
                    jedis.close();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print(".");
        } while ((System.currentTimeMillis() - beginTime) < WAITTIME);

        return false;
    }

    /**
     * 获取锁
     *
     * @param uid：锁的唯一标识
     * @param expire：锁自动释放时间
     * @param timeout：超时等待时间
     * @return true：获得锁；false:锁已被占用
     */
    public static Boolean getLock(String uid, Integer expire, Integer timeout) {
        if (expire < 0) {
            return false;
        }
        Long beginTime = System.currentTimeMillis();
        do {
            Jedis jedis = jedisPool.getResource();

            //防止程序出错设置键值不失效
            if (jedis.ttl(PREFIX + uid) == -1) {
                jedis.expire(PREFIX + uid, expire);
            }

            Long res = jedis.incr(PREFIX + uid);


            if (res == 1) {
                jedis.expire(PREFIX + uid, expire);

                if (jedis != null) {
                    try {
                        jedis.close();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }

                return true;
            }

            if (jedis != null) {
                try {
                    jedis.close();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print(".");
        } while ((System.currentTimeMillis() - beginTime) < timeout);

        return false;
    }

    /**
     * 手动释放锁
     *
     * @param uid：锁的唯一标识
     */
    public static void returnLock(String uid) {
        Jedis jedis = jedisPool.getResource();
        jedis.del(PREFIX + uid);
        if (jedis != null) {
            try {
                jedis.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        System.out.println("手动释放锁：" + uid);
    }

    /**
     * 测试
     *
     * @param args
     */
    public static void main(String[] args) {

        final AtomicInteger ai = new AtomicInteger(1);
        final AtomicInteger count = new AtomicInteger(0);
        for (int i = 0; i < 30; i++) {
            new Thread() {
                @Override
                public void run() {
                    String uid = "a" + ai.incrementAndGet() % 10; // 获取锁，并设置10秒失效
                    Boolean lock = LockUtil.getLock(uid, 10);
                    System.out.println(uid + "取锁结果：" + lock);
                    if (lock) {
                        // dosomething..
                        count.incrementAndGet();
                    }
                    System.out.println("当前获取锁的数量：" + count);
                }
            }.start();
        }

        // 手动解锁测试,超时测试
        //Boolean lock = LockUtil.getLock("testReturnKey", 1);
        //System.out.println("testReturnKey" + "取锁结果：" + lock);
        // LockUtil.returnLock("testReturnKey");

    }


}
