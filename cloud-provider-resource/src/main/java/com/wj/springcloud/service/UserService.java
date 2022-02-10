package com.wj.springcloud.service;

import com.wj.springcloud.common.BaseServise;
import com.wj.springcloud.mapper.UsersMapper;
import com.wj.springcloud.pojo.User;

import java.util.List;

/**
 * @author ：actor
 * @date ：Created in 2021/8/5 15:48
 * @description：用户类
 * @modified By：
 * @version: $
 */
public interface UserService extends BaseServise<UsersMapper,User> {

    List<User> getUserList();
}
