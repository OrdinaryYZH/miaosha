package com.genericyzh.miaosha.order.service;


import com.genericyzh.miaosha.goods.model.MiaoshaGoods;
import com.genericyzh.miaosha.miaosha.model.MiaoshaOrder;
import com.genericyzh.miaosha.order.model.OrderInfo;
import com.genericyzh.miaosha.user.model.UserInfo;

public interface OrderService {


    public MiaoshaOrder getMiaoshaOrderByUserIdGoodsId(String userId, long goodsId);

    public OrderInfo getOrderById(long orderId);

    public OrderInfo createOrder(UserInfo user, MiaoshaGoods good);

    public void deleteOrders();

}
