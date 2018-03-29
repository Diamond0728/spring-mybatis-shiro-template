package com.group2.project.controller.user;

import com.group2.project.common.EncodeUtil;
import com.group2.project.common.ResponseDomain;
import com.group2.project.entity.Role;
import com.group2.project.entity.User;
import com.group2.project.service.RoleService;
import com.group2.project.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    final String SALT = "SGKAERHUNLSIONF";
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

    @RequestMapping(value = "/login")
    public ResponseDomain<User> login(@RequestBody UserPara userPara) {
        ResponseDomain<User> responseDomain = new ResponseDomain<>();
        String username = userPara.getUsername();
        System.out.println(username);
        String password = userPara.getPassword();
        password = EncodeUtil.MD5(password + SALT);
        System.out.println(password);
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        Subject currentUser = SecurityUtils.getSubject();
        try {
            currentUser.login(token);
        } catch (UnknownAccountException uae) {
            responseDomain.fail("用户名或密码错误");
        }
        if (!responseDomain.isSuccess()) {
            token.clear();
        }
        return responseDomain;
    }

    @RequestMapping(value = "/register")
    public ResponseDomain<User> register(@RequestBody UserPara userPara) {
        ResponseDomain<User> responseDomain = new ResponseDomain<>();
        String username = userPara.getUsername();
        if(userService.getOneByUsername(username) == null) {
            String password = userPara.getPassword();
            password = EncodeUtil.MD5(password + SALT);
            try {
                //暂时有点问题的样子
                System.out.println(userService.register(username,password));
            } catch (Exception e) {
                responseDomain.fail("注册失败");
            }
            if (!responseDomain.isSuccess()) {
                return responseDomain;
            }
            return login(userPara);
        } else {
            responseDomain.fail("该用户名已存在");
            return responseDomain;
        }
    }

    @RequestMapping(value = "/index")
    public User index () {
        return userService.getOneByUid(1);
    }

    @RequestMapping(value = "/roles")
    public List<Role> roles() {
        return roleService.findByUserId(1);
    }

    @RequestMapping(value = "/api/main")
    @RequiresRoles("admin")
    public String main () {
        return "main!";
    }

    @RequestMapping(value = "/users")
    public User[] users () {
        User user = new User();
        user.setU_id(1);
        user.setUsername("diamond");
        User[] users = userService.getAllUser();
        return users;
    }
}
