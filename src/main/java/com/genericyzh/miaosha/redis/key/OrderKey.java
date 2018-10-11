package com.genericyzh.miaosha.redis.key;

public class OrderKey extends BasePrefix {

    public OrderKey(String prefix) {
        super(prefix);
    }

    public static OrderKey MiaoshaOrderByUidGid = new OrderKey("miaoshaOrder");
}
