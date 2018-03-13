package com.wat.springmvc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.wat.springmvc.web.entity.User;

@Mapper
public interface UserMapper {
	
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    List<User> getAllUser();
}