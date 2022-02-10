package com.wj.springcloud.mapper;

import com.wj.springcloud.common.BaseCrud;
import com.wj.springcloud.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * @author ：actor
 * @date ：Created in 2021/8/5 15:49
 * @description：用户mapper
 * @modified By：
 * @version: $
 */
@Mapper
public interface UsersMapper extends BaseCrud<User> {

    List<User> getUserList();

}
