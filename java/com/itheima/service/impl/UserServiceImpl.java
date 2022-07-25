package com.itheima.service.impl;

import com.itheima.dao.UserMapper;
import com.itheima.domain.User;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public boolean register(User user) {
        int insertNum=userMapper.insert(user);
        if(insertNum!=0){
            return true;
        }
        else {
            return false;
        }
    }
    /*** 处理用户登录业务 * @param username * @param password * @return */
    @Override
    public User login(String username, String password) {
        //登录业务：调用dao层拿用户名和密码到数据库中查询数据
        // 查询数据前密码加密处理
        User user = userMapper.queryByUserNameAndPassword(username, password);
        return user;
    }
}
