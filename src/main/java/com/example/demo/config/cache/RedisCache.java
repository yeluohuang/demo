package com.example.demo.config.cache;

import com.example.demo.config.ioc.ApplicationContextHolder;
import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * redis的dao层，由于接入的mysql的二级缓存，所以需要失效cache接口；也可以直接使用redis进行缓存，对需要缓存的数据在service层进行mysql的更新操作
 * 使用方式：在mapper.xml里引入
 * <mapper namespace="com.example.demo.business.**.*Mapper">
 *     <cache type="com.example.demo.config.cache.RedisCache"/>
 * </mapper>
 * @author zhushj3
 * @date 2020/05/22
 */
public class RedisCache implements Cache {
    private static final Logger logger = LoggerFactory.getLogger(RedisCache.class);
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final String id;
    private RedisTemplate redisTemplate;
    // redis过期时间
    private static final long EXPIRE_TIME_IN_MINUTES = 30;
    public RedisCache(String id){
        if (id == null){
            throw  new IllegalArgumentException("Cache instance required an ID");
        }
        this.id = id;
    }
    @Override
    public String getId() {
        return id;
    }

    @Override
    public void putObject(Object key, Object value) {
        RedisTemplate redisTemplate = getRedisTemplate();
        ValueOperations opsForValue = redisTemplate.opsForValue();
        logger.info("存入当前数据"+key.toString()+":"+value.toString());
        opsForValue.set(key.toString(),value,EXPIRE_TIME_IN_MINUTES, TimeUnit.MINUTES);
    }

    @Override
    public Object getObject(Object key) {
        RedisTemplate redisTemplate = getRedisTemplate();
        ValueOperations opsForValue = redisTemplate.opsForValue();
        logger.info("取当前数据,key="+key.toString());
        return opsForValue.get(key.toString());
    }

    @Override
    public Object removeObject(Object key) {
        RedisTemplate redisTemplate = getRedisTemplate();
        redisTemplate.delete(key);
        logger.info("删除当前数据,key="+key.toString());
        return null;
    }

    @Override
    public void clear() {
        RedisTemplate redisTemplate = getRedisTemplate();
        redisTemplate.execute((RedisCallback) connection -> {
            connection.flushDb();
            return null;
        });
        logger.info("清除缓存");
    }

    @Override
    public int getSize() {
        Long size = (Long) redisTemplate.execute((RedisCallback) connection -> connection.dbSize());
        return size.intValue();
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return readWriteLock;
    }

    // 获取redisTemplate对象
    private RedisTemplate getRedisTemplate() {
        if(redisTemplate == null){
            redisTemplate = ApplicationContextHolder.getBean("redisTemplate");
        }
        return redisTemplate;
    }
}
