package com.genericyzh.miaosha.order.model.vo;


import com.genericyzh.miaosha.good.model.Good;
import com.genericyzh.miaosha.order.model.OrderInfo;

public class OrderDetailVo {
    private Good good;
    private OrderInfo order;

    public Good getGood() {
        return good;
    }

    public void setGood(Good good) {
        this.good = good;
    }

    public OrderInfo getOrder() {
        return order;
    }

    public void setOrder(OrderInfo order) {
        this.order = order;
    }
}
