package com.xuanluo.myshop.commons.mapper;

import com.xuanluo.myshop.commons.domain.TbUser;
import com.xuanluo.myshop.commons.utils.RedisCache;
import org.apache.ibatis.annotations.CacheNamespace;
import tk.mybatis.mapper.MyMapper;

@CacheNamespace(implementation = RedisCache.class)
public interface TbUserMapper extends MyMapper<TbUser> {
}