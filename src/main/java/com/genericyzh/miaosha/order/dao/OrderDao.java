package com.genericyzh.miaosha.order.dao;

import com.genericyzh.miaosha.miaosha.model.MiaoshaOrder;
import com.genericyzh.miaosha.order.model.OrderInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderDao {

    MiaoshaOrder getMiaoshaOrderByUserIdGoodsId(@Param("userId") long userId, @Param("goodsId") long goodsId);

    long insert(OrderInfo orderInfo);

    int insertMiaoshaOrder(MiaoshaOrder miaoshaOrder);

    OrderInfo getOrderById(@Param("orderId") long orderId);

    void deleteOrders();

    void deleteMiaoshaOrders();


}
