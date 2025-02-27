package com.jinglinling.web.manager;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Auther 谢骏晖
 * @Date 2025/2/9
 * @Description 多级缓存
 */
@Component
public class CacheManager {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    //本地缓存
    Cache<String, Object> localCache = Caffeine.newBuilder()
            .expireAfterWrite(100, TimeUnit.MINUTES)
            .maximumSize(10_000)
            .build();

    /**
     * 写缓存
     * @param key
     * @param value
     */
    public void put(String key, Object value) {
        localCache.put(key, value);
        redisTemplate.opsForValue().set(key, value, 100, TimeUnit.MINUTES);
    }

    /**
     * 读缓存
     * @param key
     * @return
     */
    public Object get(String key) {
        //先从本地缓存中尝试获取
        Object value = localCache.getIfPresent(key);
        if (value != null) {
            return value;
        }

        //本地缓存未命中，从Redis中尝试获取
        value = redisTemplate.opsForValue().get(key);
        if (value != null) {
            //将从Redis获取的值放入本地缓存
            localCache.put(key, value);
        }

        return value;
    }

    /**
     * 移除缓存
     * @param key
     */
    public void delete(String key) {
        localCache.invalidate(key);
        redisTemplate.delete(key);
    }
}
