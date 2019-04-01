package com.xuanluo.myshop.service.redis.api;

public interface RedisService {
    /**
     * 设置缓存
     * @param key
     * @param value
     */
    void put(String key,Object value);

    /**
     * 设置缓存
     * @param key
     * @param value
     * @param second  有效期
     */
    void put(String key,Object value,int second);

    /**
     * 获取缓存值
     * @param key
     * @return
     */
    Object get(String key);

    /**
     * 删除缓存
     * @param key
     */
    void delete(String key);
}
