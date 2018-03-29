package com.group2.project.mapper;

import com.group2.project.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    @Select("SELECT * FROM user")
    public User[] getAllUser();

    @Select("SELECT * FROM user WHERE username = #{username}")
    public User getOneByUsername(String username);

    @Select("SELECT * FROM user WHERE u_id = #{u_id}")
    public User getOneByUid(Integer u_id);

    @Insert("INSERT INTO user(username, password) VALUES(#{username},#{password})")
    public int insert(String username, String password);

//
//    @Select("SELECT * FROM tb_user WHERE id = #{id}")
//    User getUserById(Integer id);
//
//    @Select("SELECT * FROM tb_user")
//    public List<User> getUserList();
//
//    @Insert("insert into tb_user(username, age, ctm) values(#{username}, #{age}, now())")
//    public int add(User user);
//
//    @Update("UPDATE tb_user SET username = #{user.username} , age = #{user.age} WHERE id = #{id}")
//    public int update(@Param("id") Integer id, @Param("user") User user);
//
//    @Delete("DELETE from tb_user where id = #{id} ")
//    public int delete(Integer id);
}
