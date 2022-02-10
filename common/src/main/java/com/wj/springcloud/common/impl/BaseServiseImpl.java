package com.wj.springcloud.common.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;
import com.wj.springcloud.common.BaseServise;
import com.wj.springcloud.common.BaseCrud;
import com.wj.springcloud.common.entity.BaseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import com.wj.springcloud.common.entity.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;


/**
 * @author ：actor
 * @date ：Created in 2021/8/5 19:12
 * @description：基础包实现类
 * @modified By：
 * @version: $
 */
@Service
@Slf4j
public class BaseServiseImpl<M extends BaseCrud<T>,T extends BaseEntity<T>> implements BaseServise<M ,T> {
    /**
     * 持久层对象
     */
    @Autowired
    protected M mapper;

    /**
     * 获取单条数据
     * @param id
     * @return
     */
    @Override
    public T getOneById(String id) {
        return mapper.getOneById(id);
    }

    /**
     * 获取单条数据
     * @param entity
     * @return
     */
    @Override
    public T getOneByParam(T entity) {
        return mapper.getOneByParam(entity);
    }

    /**
     * 查询列表数据
     * @param entity
     * @return
     */
    @Override
    public List<T> findList(T entity) {
        return mapper.findList(entity);
    }

    /**
     * 查询列表数据
     * @param entity
     * @return
     */
    @Override
    public List<T> findAllList(T entity) {
        return mapper.findAllList(entity);
    }

    /**
     * 查询分页数据
     * @param page 分页对象
     * @param entity
     * @return
     */
    @Override
    public Page<T> findPage(com.wj.springcloud.common.entity.Page<T> page, T entity) {
        com.github.pagehelper.Page<T> pageInfo = PageHelper.startPage(page.getPageNo(), page.getPageSize());
        entity.setPage(page);
        page.setData(mapper.findList(entity));
        page.resetByPageInfo(pageInfo);
        return page;
    }

    /**
     * 查询分页数据
     * @param page 分页对象
     * @param entity
     * @return
     */
    @Override
    public Page<T> findAllPage(Page<T> page, T entity) {
        com.github.pagehelper.Page<T> pageInfo = PageHelper.startPage(page.getPageNo(), page.getPageSize());
        entity.setPage(page);
        page.setData(mapper.findAllList(entity));
        page.resetByPageInfo(pageInfo);
        return page;
    }

    /**
     * 保存数据（插入或更新）
     * @param entity
     */
    @Override
    @Transactional(readOnly = false)
    public void save(T entity) {
        entity.setCreateTime(new Date());
        if (StringUtil.isEmpty(entity.getId())){
            entity.setId(UUID.randomUUID().toString());
            mapper.insert(entity);
        }else{
            mapper.update(entity);
        }
    }

    /**
     * 删除数据
     * @param entity
     */
    @Override
    @Transactional(readOnly = false)
    public void delete(T entity) {
        mapper.delete(entity);
    }
}
