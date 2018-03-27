package com.group2.project.service;

import com.group2.project.bean.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User[] getAllUser();
}
