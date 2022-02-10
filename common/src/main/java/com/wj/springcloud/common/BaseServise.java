package com.wj.springcloud.common;

import com.wj.springcloud.common.entity.BaseEntity;
import com.wj.springcloud.common.entity.Page;
import java.util.List;

/**
 * @author ：actor
 * @date ：Created in 2021/8/5 19:09
 * @description：公共的crud
 * @modified By：
 * @version: $
 */
public interface BaseServise<M extends BaseCrud<T>,T extends BaseEntity<T>> {

    /**
     * 获取单条数据
     * @param id
     * @return
     */
    T getOneById(String id);

    /**
     * 获取单条数据
     * @param entity
     * @return
     */
    T getOneByParam(T entity);

    /**
     * 查询列表数据
     * @param entity
     * @return
     */
    List<T> findList(T entity);

    /**
     * 查询列表数据
     * @param entity
     * @return
     */
    List<T> findAllList(T entity);

    /**
     * 查询分页数据
     * @param page 分页对象
     * @param entity
     * @return
     */
    Page<T> findPage(Page<T> page, T entity);

    /**
     * 查询分页数据
     * @param page 分页对象
     * @param entity
     * @return
     */
    Page<T> findAllPage(Page<T> page, T entity);

    /**
     * 保存数据（插入或更新）
     * @param entity
     */
    void save(T entity);

    /**
     * 删除数据
     * @param entity
     */
    void delete(T entity);

}
