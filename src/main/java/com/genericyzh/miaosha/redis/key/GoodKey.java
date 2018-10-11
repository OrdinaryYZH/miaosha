package com.genericyzh.miaosha.redis.key;

public class GoodKey extends BasePrefix {

    private GoodKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static GoodKey goodsList = new GoodKey(60, "gl");
    public static GoodKey goodsDetail = new GoodKey(60, "gd");
    public static GoodKey miaoshaGoodsStock = new GoodKey(0, "gs");
}
