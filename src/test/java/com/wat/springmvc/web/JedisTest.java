package com.wat.springmvc.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wat.springmvc.web.entity.UserInfo;
import com.wat.springmvc.web.utils.SerializeUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring/spring-context.xml"})
public class JedisTest {

    @Autowired
    private ShardedJedisPool shardedJedisPool;

    @Test
    public void getTest() {

        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        System.out.println("AA get::" + shardedJedis.get("AA"));

        String [] a = {"a","b"};
        shardedJedis.lpush("aArray",a);

        List arrayList = new ArrayList<Integer>();
        arrayList.add(1);
        arrayList.add(2);
        shardedJedis.lpush("aArrayList",arrayList.toString());

        List<UserInfo> userInfos = new ArrayList<>();

        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("张三");
        userInfos.add(userInfo);

        UserInfo userInfo1 = new UserInfo();
        userInfo1.setUsername("李四");
        userInfos.add(userInfo1);

        shardedJedis.lpush("userInfos",JSON.toJSON(userInfos).toString());
        System.out.println("aArray size ::"+shardedJedis.llen("aArray"));

        shardedJedis.close();

    }

}
