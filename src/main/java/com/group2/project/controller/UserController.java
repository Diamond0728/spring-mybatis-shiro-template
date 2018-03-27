package com.group2.project.controller;

import com.group2.project.bean.User;
import com.group2.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/hello")
    public String hello () {
        return "hello world!";
    }

    @RequestMapping(value = "/users")
    public User[] users () {
        User user = new User();
        user.setId("1");
        user.setName("diamond");
        User[] users = userService.getAllUser();
        return users;
    }
}
