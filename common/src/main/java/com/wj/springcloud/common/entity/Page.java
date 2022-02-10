package com.wj.springcloud.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：actor
 * @date ：Created in 2021/8/5 22:17
 * @description：分页
 * @modified By：
 * @version: $
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Page<T> {

    private Integer pageNo = 1;

    private Integer pageSize = 10;

    private List<T> data = new ArrayList<>();

    private Long count;

    public void resetByPageInfo(com.github.pagehelper.Page<T> pageInfo) {
        this.pageNo = pageInfo.getPageNum();
        this.pageSize = pageInfo.getPageSize();
        this.count = Long.parseLong(String.valueOf(data.size())) ;
    }

}
