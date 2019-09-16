package com.example.services;

import com.example.entity.User;

/**
 * @author Administrator
 * @date 2019/9/9 15:57
 */
public interface IUser {
    User getUserByName(String name);
}
