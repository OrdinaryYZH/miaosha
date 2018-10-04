package com.genericyzh.miaosha.redis.key;

/**
 * @author genericyzh
 * @date 2018/10/4 17:46
 */
public class MiaoshaUserKey extends BasePrefix {

    private static final int TOKEN_EXPIRE = 3600 * 24 * 2;

    public MiaoshaUserKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static final MiaoshaUserKey token = new MiaoshaUserKey(TOKEN_EXPIRE, "token");
}
