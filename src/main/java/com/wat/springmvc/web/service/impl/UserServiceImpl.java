package com.wat.springmvc.web.service.impl;

import com.wat.springmvc.web.annotation.MyAnnotation;
import com.wat.springmvc.web.controller.MyTaskController;
import com.wat.springmvc.web.entity.UserInfo;
import com.wat.springmvc.web.mapper.UserInfoMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.wat.springmvc.web.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

    Logger logger = LogManager.getLogger(UserServiceImpl.class);

    @Autowired
    UserInfoMapper userInfoMapper;

    @MyAnnotation(tableName = "zhangsan")
    public UserInfo getUserById(Integer id) {
        // TODO Auto-generated method stub
        logger.info("测试分包");
        return userInfoMapper.selectByPrimaryKey(id);
    }

}
