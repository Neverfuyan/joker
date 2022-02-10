package com.wj.springcloud.mapper;

import com.wj.springcloud.entities.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ：actor
 * @date ：Created in 2021/7/26 17:00
 * @description：
 * @modified By：
 * @version: $
 */
@Mapper
public interface UserMapper {

    User selectByUsername(String username);
}
