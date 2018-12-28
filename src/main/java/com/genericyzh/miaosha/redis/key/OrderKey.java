package com.genericyzh.miaosha.redis.key;

import com.genericyzh.miaosha.redis.key.common.BasePrefix;

public class OrderKey extends BasePrefix {

    public OrderKey(String prefix) {
        super(prefix);
    }

    public static OrderKey MiaoshaOrderByUidGid = new OrderKey("miaoshaOrder");
}
