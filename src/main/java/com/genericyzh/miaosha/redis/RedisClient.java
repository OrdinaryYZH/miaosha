package com.genericyzh.miaosha.redis;

import com.genericyzh.miaosha.utils.SerializeUtil;
import redis.clients.jedis.Jedis;

import java.util.function.Function;

/**
 * @author genericyzh
 * @date 2018/10/2 12:59
 */
public class RedisClient {

    public static <R> R execute(Function<Jedis, R> fun) {
        Jedis jedis = RedisPoolFactory.getInstance().getJedis();
        return fun.apply(jedis);
    }

    public static <R, S> R execute(Function<Jedis, S> fun, Class<R> clz) {
        Jedis jedis = RedisPoolFactory.getInstance().getJedis();
        S apply = fun.apply(jedis);
        return SerializeUtil.stringToBean((String) apply, clz);
    }

}
