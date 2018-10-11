package com.genericyzh.miaosha.rabbitmq;


import com.genericyzh.miaosha.user.model.UserInfo;

public class MiaoshaMessage {
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
