package com.wj.springcloud.pojo;

import com.wj.springcloud.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：actor
 * @date ：Created in 2021/8/5 15:52
 * @description：
 * @modified By：
 * @version: $
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity<User> {

    private String userId;

    private String userName;
}
