package com.wj.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：actor
 * @date ：Created in 2021/7/26 17:04
 * @description：
 * @modified By：
 * @version: $
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String id;

    private String userName;

    private String password;
}
