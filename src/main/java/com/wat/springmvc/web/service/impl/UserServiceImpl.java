package com.wat.springmvc.web.service.impl;

import com.wat.springmvc.web.entity.UserInfo;
import com.wat.springmvc.web.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.wat.springmvc.web.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {
    
	@Autowired
	UserInfoMapper userInfoMapper;
	
	public UserInfo getUserById(Integer id) {
		// TODO Auto-generated method stub
		return userInfoMapper.selectByPrimaryKey(id);
	}

}
