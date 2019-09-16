package com.example.services.impl;

import com.example.entity.User;
import com.example.mapper.UserMapper;
import com.example.services.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Administrator
 * @date 2019/9/9 15:57
 */
@Service(value = "userServices")
public class UserImpl implements IUser {

    @Resource
    private UserMapper userMapper;
    @Override
    public User getUserByName(String name) {
        return userMapper.queryUserByName(name);
    }
}
