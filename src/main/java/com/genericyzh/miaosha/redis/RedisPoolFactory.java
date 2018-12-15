package com.genericyzh.miaosha.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author genericyzh
 * @date 2018/10/2 16:08
 */
@Component("RedisPoolFactory")
public class RedisPoolFactory {

    private static RedisPoolFactory redisPoolClient;

//    private RedisPoolFactory() {
//        // 不能这么使用，因为这时的redisConfig还没实例化
//        //initPool();
//        RedisPoolFactory.redisPoolClient = this;
//    }

    @PostConstruct
    private void init() {
        RedisPoolFactory.redisPoolClient = this;
    }

    @PreDestroy
    private void closePool() {
        if (this.pool != null) {
            pool.close();
        }
    }

    /**
     * 连接池
     */
    private JedisPool pool;


    @Autowired
    private RedisConfig redisConfig;

    public static RedisPoolFactory getInstance() {
        return redisPoolClient;
    }

    public Jedis getJedis() {
        if (pool == null) {
            synchronized (JedisPool.class) {
                if (pool == null) {
                    initPool();
                }
            }
        }
        try (Jedis resource = pool.getResource()) {
            return resource;
        }
    }

    private void initPool() {
        // redis配置信息
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(redisConfig.getPoolMaxIdle());
        poolConfig.setMaxTotal(redisConfig.getPoolMaxTotal());
        poolConfig.setMaxWaitMillis(redisConfig.getPoolMaxWait() * 1000);

        // redis连接信息
        pool = new JedisPool(poolConfig,
                redisConfig.getHost(),
                redisConfig.getPort(),
                redisConfig.getTimeout() * 1000,
                redisConfig.getPassword(),
                0);
    }

}
