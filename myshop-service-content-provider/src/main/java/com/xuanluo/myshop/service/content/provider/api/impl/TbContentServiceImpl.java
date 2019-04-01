package com.xuanluo.myshop.service.content.provider.api.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageInfo;
import com.xuanluo.myshop.commons.domain.TbContent;
import com.xuanluo.myshop.commons.mapper.TbContentMapper;
import com.xuanluo.myshop.commons.model.impl.BaseCrudServiceImpl;
import com.xuanluo.myshop.service.content.api.TbContentService;
import org.apache.commons.lang3.StringUtils;
import tk.mybatis.mapper.entity.Example;

/**
 * 内容服务
 * @author xuanluo
 */
@Service(version = "${services.versions.content.v1}")
public class TbContentServiceImpl extends BaseCrudServiceImpl<TbContent, TbContentMapper> implements TbContentService {

    @Override
    public PageInfo<TbContent> page(TbContent tbContent, int offset, int limit) {
        Example example = new Example(TbContent.class);
        Example.Criteria criteria = example.createCriteria();
        fuzzyQuery(criteria, "title", tbContent.getTitle());
        fuzzyQuery(criteria, "subTitle", tbContent.getSubTitle());
        fuzzyQuery(criteria, "titleDesc", tbContent.getTitleDesc());
        PageInfo<TbContent> pageInfo = new PageInfo<>(mapper.selectByExample(example));
        return pageInfo;
    }

    /**
     * 模糊查询
     *
     * @param criteria
     * @param attributes 查询的属性
     * @param value      查询的值
     */
    private void fuzzyQuery(Example.Criteria criteria, String attributes, String value) {
        if (StringUtils.isNotBlank(value)) {
            criteria.andLike(attributes, "%" + value + "%");
        }
    }
}
