package com.group2.project.service.impl;

import com.group2.project.bean.User;
import com.group2.project.mapper.UserMapper;
import com.group2.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User[] getAllUser() {
        System.out.println("serviceimpl");
        return userMapper.getAllUser();
    }
}
