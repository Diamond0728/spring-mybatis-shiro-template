package com.group2.project.service;

import com.group2.project.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User[] getAllUser();
    User getOneByUsername(String username);
    User getOneByUid(Integer u_id);
    List<String> findPermissionByUser(User user);
    int register(String username, String password);
}
