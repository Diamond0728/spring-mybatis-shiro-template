package com.group2.project.mapper;

import com.group2.project.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user")
    User[] getAllUser();
}
