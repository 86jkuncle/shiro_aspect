package com.example.mapper;

import com.example.entity.User;


/**
 * @author Administrator
 * @date 2019/9/9 9:48
 */
public interface UserMapper {
    User queryUserByName(String username);
}
