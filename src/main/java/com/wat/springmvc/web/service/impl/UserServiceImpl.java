package com.wat.springmvc.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wat.springmvc.web.entity.User;
import com.wat.springmvc.web.mapper.UserMapper;
import com.wat.springmvc.web.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {
    
	@Autowired
	UserMapper mapper;
	
	public User getUserById(Long id) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(id);
	}

}
