package com.genericyzh.miaosha.order.service;


import com.genericyzh.miaosha.good.model.MiaoshaGood;
import com.genericyzh.miaosha.miaosha.model.MiaoshaOrder;
import com.genericyzh.miaosha.order.model.OrderInfo;
import com.genericyzh.miaosha.user.model.UserInfo;

public interface OrderService {


    public MiaoshaOrder getMiaoshaOrderByUserIdGoodsId(String userId, long goodsId);

    public OrderInfo getOrderById(long orderId);

    public OrderInfo createOrder(UserInfo user, MiaoshaGood good);

    public void deleteOrders();

}
