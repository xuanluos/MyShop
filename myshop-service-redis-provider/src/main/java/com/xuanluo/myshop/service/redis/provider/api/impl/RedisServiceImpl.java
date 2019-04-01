package com.xuanluo.myshop.service.redis.provider.api.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.xuanluo.myshop.service.redis.api.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

@Service(version = "${services.versions.redis.v1}")
public class RedisServiceImpl implements RedisService {
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void put(String key, Object value) {
        redisTemplate.opsForValue().set(key,value);
    }

    @Override
    public void put(String key, Object value, int second) {
        redisTemplate.opsForValue().set(key,value,second, TimeUnit.SECONDS);
    }

    @Override
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void delete(String key) {
        redisTemplate.delete(key);
    }
}
