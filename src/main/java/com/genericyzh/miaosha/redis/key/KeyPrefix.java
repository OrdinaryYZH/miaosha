package com.genericyzh.miaosha.redis.key;

/**
 * @author genericyzh
 * @date 2018/10/1 23:37
 */
public interface KeyPrefix {

    String SPLIT = ":";

    String getPrefix();

    int expireSeconds();

    /**
     * 返回模块前缀+value
     *
     * @param value
     * @return
     */
    String appendPrefix(String... value);
}
