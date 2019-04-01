package com.xuanluo.myshop.commons.model.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xuanluo.myshop.commons.model.AbstractBaseDomain;
import com.xuanluo.myshop.commons.model.BaseCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.MyMapper;
import tk.mybatis.mapper.entity.Example;

@Transactional(readOnly = true, rollbackFor = Exception.class)
public class BaseCrudServiceImpl<T extends AbstractBaseDomain, M extends MyMapper<T>> implements BaseCrudService<T> {

    @Autowired
    protected M mapper;

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void save(T entity) {
        if (entity.preSave()) {
            //新增用户
            mapper.insert(entity);
        } else {
            //编辑用户
            mapper.updateByPrimaryKey(entity);
        }
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void delete(T entity) {
        mapper.deleteByPrimaryKey(entity);
    }

    @Override
    public T getById(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo page(T entity, int offset, int limit) {
        Example example = new Example(entity.getClass());
        Example.Criteria criteria = example.createCriteria();
        example.orderBy("updated").desc();

        PageHelper.offsetPage(offset, limit);
        PageInfo<T> pageInfo = new PageInfo<>(mapper.selectByExample(example));
        return pageInfo;
    }
}
