package com.wj.springcloud.mapper;

import com.wj.springcloud.entities.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author ：actor
 * @date ：Created in 2021/7/26 17:01
 * @description：
 * @modified By：
 * @version: $
 */
@Mapper
public interface PermissionMapper {

    List<Permission> selectPermissionsByUserId(String id);
}
