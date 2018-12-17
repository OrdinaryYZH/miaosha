package com.genericyzh.miaosha.rabbitmq;


import com.genericyzh.miaosha.user.model.UserInfo;

import java.io.Serializable;

public class MiaoshaMessage implements Serializable {
    private UserInfo user;
    private long goodsId;

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }

    public long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(long goodsId) {
        this.goodsId = goodsId;
    }
}
