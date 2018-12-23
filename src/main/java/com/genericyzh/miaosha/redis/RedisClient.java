package com.genericyzh.miaosha.redis;

import com.genericyzh.miaosha.utils.SerializeUtil;
import redis.clients.jedis.Jedis;

import java.util.function.Function;

/**
 * @author genericyzh
 * @date 2018/10/2 12:59
 */
public class RedisClient {

    private RedisPoolFactory redisPoolFactory;

    public RedisClient(RedisPoolFactory redisPoolFactory) {
        this.redisPoolFactory = redisPoolFactory;
    }

    public <R> R execute(Function<Jedis, R> fun) {
        Jedis jedis = redisPoolFactory.getJedis();
        return fun.apply(jedis);
    }

    public <R, S> R execute(Function<Jedis, S> fun, Class<R> clz) {
        Jedis jedis = redisPoolFactory.getJedis();
        S apply = fun.apply(jedis);
        return SerializeUtil.stringToBean((String) apply, clz);
    }

}
