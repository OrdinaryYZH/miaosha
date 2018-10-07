package com.genericyzh.miaosha.redis.key;

public class GoodKey extends BasePrefix {

    private GoodKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static GoodKey getGoodsList = new GoodKey(60, "gl");
    public static GoodKey getGoodsDetail = new GoodKey(60, "gd");
    public static GoodKey getMiaoshaGoodsStock = new GoodKey(0, "gs");
}
