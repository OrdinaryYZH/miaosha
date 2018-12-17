package com.genericyzh.miaosha.order.model.vo;


import com.genericyzh.miaosha.goods.model.Goods;
import com.genericyzh.miaosha.order.model.OrderInfo;

public class OrderDetailVo {
    private Goods goods;
    private OrderInfo order;

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public OrderInfo getOrder() {
        return order;
    }

    public void setOrder(OrderInfo order) {
        this.order = order;
    }
}
