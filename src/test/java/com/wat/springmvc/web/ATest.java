package com.wat.springmvc.web;


import com.wat.springmvc.web.utils.redisutils.JedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import redis.clients.jedis.Jedis;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring/spring-context.xml"})
public class ATest {

    @Test
    public void getTest() {
        Jedis jedis = JedisUtil.getSingletonJedis();

        System.out.println("dd::"+ jedis.get("1"));

    }

}
