package com.wat.springmvc.web.service.impl;

import com.wat.springmvc.web.entity.UserInfo;
import com.wat.springmvc.web.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring/spring-context.xml"})
public class UserServiceImplTest {

    @Autowired
    IUserService iUserService;

    @Test
    public void getUserById() {
     UserInfo userInfo =  iUserService.getUserById(1);
        System.out.printf("UserInfo"+userInfo.getLoginName());
    }
}