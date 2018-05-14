package com.wat.springmvc.web.utils;


import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class CacheUtil {

    @Autowired
    ShardedJedisPool  shardedJedisPool;



}
