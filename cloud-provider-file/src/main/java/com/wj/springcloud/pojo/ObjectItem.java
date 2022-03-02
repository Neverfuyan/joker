package com.wj.springcloud.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：actor
 * @date ：Created in 2022/2/23 13:56
 * @description：通用item
 * @modified By：
 * @version: $
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ObjectItem {
    /**
     * 文件名称
     */
    private String objectName;

    /**
     * 文件大小
     */
    private Long size;

}
