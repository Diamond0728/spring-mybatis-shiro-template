package com.group2.project.mapper;

import com.group2.project.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserRoleMapper {
    @Select("SELECT * FROM user_role")
    public UserRole[] getAllUerRole();

    @Select("SELECT * FROM user_role WHERE u_id = #{u_id}")
    public UserRole[] getUserRoleByUid(Integer u_id);

    @Select("SELECT * FROM user_role WHERE p_id = #{p_id}")
    public UserRole[] getUserRoleByPid(Integer p_id);
}
