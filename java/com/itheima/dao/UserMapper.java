package com.itheima.dao;

import com.itheima.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert("INSERT INTO tab_user VALUES(null,#{username},#{password},#{name},#{birthday},#{sex},#{telephone},#{email})")
     int insert(User user);
    @Select("SELECT * FROM tab_user WHERE username =#{username} AND password =#{password}")
     User queryByUserNameAndPassword(@Param("username") String username, @Param("password") String password);
}
