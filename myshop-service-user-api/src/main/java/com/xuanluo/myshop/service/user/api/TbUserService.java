package com.xuanluo.myshop.service.user.api;

import com.github.pagehelper.PageInfo;
import com.xuanluo.myshop.commons.domain.TbUser;
import com.xuanluo.myshop.commons.model.BaseCrudService;

import java.util.List;

public interface TbUserService extends BaseCrudService<TbUser> {
    List<TbUser> selectAll();
}
