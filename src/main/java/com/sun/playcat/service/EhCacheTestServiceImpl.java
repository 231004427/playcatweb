package com.sun.playcat.service;

import com.sun.playcat.dao.EhCacheTestDao;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Created by sunlin on 2017/10/29.
 */
@Service("ehCacheTestService")
public class EhCacheTestServiceImpl implements EhCacheTestDao {
    @Override
    @Cacheable(value="cacheTest",key="#param")
    public String getTimestamp(String param) {
        Long timestamp = System.currentTimeMillis();
        return timestamp.toString();
    }
    @Override
    @CacheEvict(value="cacheTest",key="#param")
    public void insertTimestamp(String param){
    }
}
