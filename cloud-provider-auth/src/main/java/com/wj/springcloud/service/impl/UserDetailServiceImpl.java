package com.wj.springcloud.service.impl;

import com.wj.springcloud.entities.Permission;
import com.wj.springcloud.entities.User;
import com.wj.springcloud.mapper.PermissionMapper;
import com.wj.springcloud.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author ：actor
 * @date ：Created in 2021/7/26 17:02
 * @description：
 * @modified By：
 * @version: $
 */
@Component("myUserDetailsService")
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //User是security内部的对象，UserDetails的实现类 ，
        //用来封装用户的基本信息（用户名，密码，权限列表）
        //public User(String username, String password, Collection<? extends GrantedAuthority> authorities)


        User userFromDB = userMapper.selectByUsername(username);
        if(null == userFromDB){
            throw new RuntimeException("无效的用户");
        }

        //模拟存储在数据库的用户的密码：123
        //String password = passwordEncoder.encode("123");
        String password = userFromDB.getPassword();

        //查询用户的权限
        List<Permission> permission = permissionMapper.selectPermissionsByUserId(userFromDB.getId());

        //用户的权限列表,暂时为空
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        permission.forEach(e->{
            System.out.println("用户："+userFromDB.getUserName()+" 加载权限："+e.getExpression());
            authorities.add(new SimpleGrantedAuthority(e.getExpression()));
        });
        //注意：这里的User是Security的User不是我们自己的User
        org.springframework.security.core.userdetails.User  user = new org.springframework.security.core.userdetails.User (username,password, authorities);

        return user;
    }
}
