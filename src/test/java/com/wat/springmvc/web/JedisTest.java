package com.wat.springmvc.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wat.springmvc.web.entity.UserInfo;
import com.wat.springmvc.web.utils.SerializeUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring/spring-context.xml"})
public class JedisTest {
    Logger logger = LoggerFactory.getLogger(JedisTest.class);
    @Autowired
    private ShardedJedisPool shardedJedisPool;

    @Test
    public void getTest() {

        ShardedJedis shardedJedis = shardedJedisPool.getResource();

        System.out.println("***********************字符串**************************");
        shardedJedis.set("str1", "测试test/1");
        shardedJedis.set("str2", "测试test/2");
        shardedJedis.set("str3", "测试test/3");
        shardedJedis.set("str4", "测试");
        shardedJedis.set("str5", "张三");
        shardedJedis.set("str6", "abcdef");

        System.out.println(" GET String ::" + shardedJedis.get("str1"));
        System.out.println(" update key ::" + shardedJedis.set("str1", "123"));
        System.out.println(" GET String ::" + shardedJedis.get("str1"));

        /**
         * 汉字占了3个字节位置
         */
        System.out.println(" GET SET (设置新的value 并且返回旧的value) ::" + shardedJedis.getSet("str1", "456张三232"));

        System.out.println("截取字符串：：" + shardedJedis.getrange("str1", 3, 8));
        System.out.println("截取字符串abcdef：：" + shardedJedis.getrange("str6", 0, 2));
        System.out.println("截取字符串abcdef：：" + shardedJedis.getrange("str6", 1, 2));
        System.out.println("设置有效期key::" + shardedJedis.setex("10秒过期key", 100, "123"));
        System.out.println("替换value内容从指定位置开始 ::" + shardedJedis.setrange("str2", 7, "111"));
        System.out.println("字符串“测试”长度 ::" + shardedJedis.strlen("str4"));
        System.out.println("字符串“张三”长度 ::" + shardedJedis.strlen("str4"));
        System.out.println("如果key不存在则存储 ::" + shardedJedis.setnx("isHave", "no"));
        System.out.println("如果key存在则不存储 ::" + shardedJedis.setnx("isHave", "yes"));
        System.out.println("如果key存在则删除 ::" + shardedJedis.del("isHave"));

        System.out.println("判断key 是否存在：：" + shardedJedis.exists("str1"));
        System.out.println("删除key：" + shardedJedis.del("str1"));
        System.out.println("判断key 是否存在：：" + shardedJedis.exists("str1"));
        System.out.println("key 不存在时返回的信息：：" + shardedJedis.get("keynoexits"));
        String rs = shardedJedis.set("LOCK:TestKey", "value", "NX", "EX", 1000);

        System.out.println("*********************** Hash **************************");
        logger.info("hash set :::" + shardedJedis.hset("hashKey", "a", "1"));
        logger.info("hash set :::" + shardedJedis.hset("hashKey", "b", "2.123"));
        logger.info("hash set :::" + shardedJedis.hset("hashKey", "d", "4.0000"));
        logger.info("hash set :::" + shardedJedis.hset("hashKey", "c", "3"));
        logger.info("hash get :::" + shardedJedis.hgetAll("hashKey"));
        logger.info("hash 获取所有的value :::" + shardedJedis.hvals("hashKey"));

        logger.info("hash hmget :::" + shardedJedis.hmget("hashKey", "b", "d"));

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("key1", "1");
        hashMap.put("key2", "2");
        logger.info("hash hmset :::" + shardedJedis.hmset("hashKey", hashMap));

        logger.info("hash hlen 长度 :::" + shardedJedis.hlen("hashKey"));
        /**
         * 为哈希表 key 中的指定字段的整数值加上增量 increment
         */
        logger.info("指定字段的整数值加上增量  :::" + shardedJedis.hincrBy("hashKey", "a", 1));
        logger.info("指定字段的浮点数值加上增量  :::" + shardedJedis.hincrByFloat("hashKey", "b", 1.221113));
        logger.info("指定字段的浮点数值加上增量  :::" + shardedJedis.hincrByFloat("hashKey", "d", 1.2));
        logger.info("删除指定字段  :::" + shardedJedis.hdel("hashKey", "d", "a"));
        logger.info("是否存在指定的字段  :::" + shardedJedis.hexists("hashKey", "a"));

        System.out.println("####################### List #######################");

        logger.info("删除list  :::" + shardedJedis.del("aList"));
        logger.info("删除list  :::" + shardedJedis.del("aaList"));

        logger.info("依次从左边（头部）插入list 列表中  :::" + shardedJedis.lpush("aList", "a", "c", "b"));
        logger.info("将一个值插入到已存在的列表头部  :::" + shardedJedis.lpushx("aList", "11"));
        logger.info("将一个值插入到已存在的列表头部( key 不存在时)  :::" + shardedJedis.lpushx("aaList", "22"));
        logger.info("移出并获取列表的第一个元素（左）  :::" + shardedJedis.blpop(100,"aList"));
        logger.info("移出并获取列表的最后一个元素（右）  :::" + shardedJedis.brpop(100,"aList"));
        logger.info("获得数组长度:::"+shardedJedis.llen("aList"));
        logger.info("获得数组内容:::"+shardedJedis.lrange("aList",0,-1));
        logger.info("获得指定下标value(如果没有则返回null)::"+shardedJedis.lindex("aList",2));
        logger.info("指定下标赋值（下标必须是在数组内）::"+shardedJedis.lset("aList",1,"dd"));
        logger.info("在列表中添加一个或多个值::"+shardedJedis.rpush("aaList","1","dd"));
        logger.info("为已存在的列表添加值::"+shardedJedis.rpushx("aList","22"));
        logger.info("为已存在的列表添加值(不存在时)::"+shardedJedis.rpushx("acList","12"));


        System.out.println("*********************** Set **************************");

        String[] a = {"a", "b"};
        shardedJedis.lpush("aArray", a);
        System.out.println("aArray size ::" + shardedJedis.llen("aArray"));

        List arrayList = new ArrayList<Integer>();
        arrayList.add(1);
        arrayList.add(2);
        shardedJedis.lpush("aArrayList", arrayList.toString());


        List<UserInfo> userInfos = new ArrayList<>();

        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("张三");
        userInfos.add(userInfo);

        UserInfo userInfo1 = new UserInfo();
        userInfo1.setUsername("李四");
        userInfos.add(userInfo1);

        shardedJedis.lpush("userInfos", JSON.toJSON(userInfos).toString());

        String userInfos1 = shardedJedis.lpop("userInfos");
        List<UserInfo> userInfos2 = JSON.parseArray(userInfos1, UserInfo.class);
        for (UserInfo info : userInfos2) {
            System.out.printf("info::" + info.getUsername());
        }


        shardedJedis.close();

    }

}
