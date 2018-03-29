package com.group2.project.service.impl;

import com.group2.project.entity.Permission;
import com.group2.project.entity.RolePermission;
import com.group2.project.entity.User;
import com.group2.project.entity.UserRole;
import com.group2.project.mapper.*;
import com.group2.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    UserRoleMapper userRoleMapper;
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    RolePermissionMapper rolePermissionMapper;
    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public User[] getAllUser() {
        System.out.println("serviceimpl");
        return userMapper.getAllUser();
    }

    @Override
    public User getOneByUsername(String username) {
        System.out.println(username);
        return userMapper.getOneByUsername(username);
    }
    @Override
    public User getOneByUid(Integer u_id) {
        return userMapper.getOneByUid(u_id);
    }

    @Override
    public List<String> findPermissionByUser(User user) {
        List<String> permissionList = new ArrayList<>();
        User user1 = this.getOneByUsername(user.getUsername());
        UserRole[] userRoles = userRoleMapper.getUserRoleByUid(user1.getU_id());
        for(int i=0; i<userRoles.length; i++) {
            RolePermission[] rolePermissions = rolePermissionMapper.getRolePermissionByRid(userRoles[i].getR_id());
            for(int j=0; j<rolePermissions.length; j++) {
                Permission permission = permissionMapper.getPermissionByPid(rolePermissions[i].getP_id());
                if(!permissionList.contains(permission.getPermissionname())) {
                    permissionList.add(permission.getPermissionname());
                }
            }
        }
        return permissionList;
    }

    @Override
    public int register(String username, String password) {
        System.out.println(username);
        System.out.println(password);
        return userMapper.insert(username, password);
    }

}
