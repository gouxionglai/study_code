package com.gxl.study.redis.mapper;

import com.gxl.study.redis.model.User;

import java.util.HashMap;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User findSelective(HashMap searchMap);
}