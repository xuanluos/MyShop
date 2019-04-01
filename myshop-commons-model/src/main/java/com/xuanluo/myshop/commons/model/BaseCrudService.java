package com.xuanluo.myshop.commons.model;

import com.github.pagehelper.PageInfo;

public interface BaseCrudService <T extends AbstractBaseDomain>{
    /**
     * 保存
     */
    void save(T entity);

    /**
     * 删除
     *
     * @param entity
     */
    void delete(T entity);

    /**
     * 根据 ID 查询实例
     *
     * @param id
     * @return
     */
    T getById(Long id);

    /**
     * 分页查询
     *
     * @param entity
     * @param offset 数据起始位置
     * @param limit  每页显示笔数
     * @return
     */
    PageInfo<T> page(T entity, int offset, int limit);
}
