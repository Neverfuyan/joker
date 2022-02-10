package com.wj.springcloud.service.impl;

import com.wj.springcloud.common.impl.BaseServiseImpl;
import com.wj.springcloud.mapper.UsersMapper;
import com.wj.springcloud.pojo.User;
import com.wj.springcloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：actor
 * @date ：Created in 2021/8/5 15:48
 * @description：用户实现类
 * @modified By：
 * @version: $
 */
@Service
public class UserServiceImpl  extends BaseServiseImpl<UsersMapper,User> implements UserService{

    @Autowired
    private UsersMapper userMapper;

    @Override
    public List<User> getUserList() {
        return userMapper.getUserList();
    }
}
