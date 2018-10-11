package com.genericyzh.miaosha.redis.key;

/**
 * @author genericyzh
 * @date 2018/10/4 17:46
 */
public class MiaoshaKey extends BasePrefix {

    private static final int TOKEN_EXPIRE = 3600 * 24 * 2;

    public MiaoshaKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static MiaoshaKey miaoshaPath = new MiaoshaKey(60, "mp");
    public static MiaoshaKey miaoshaVerifyCode = new MiaoshaKey(300, "vc");

}
