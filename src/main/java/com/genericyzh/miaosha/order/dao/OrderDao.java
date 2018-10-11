package com.genericyzh.miaosha.order.dao;

import com.genericyzh.miaosha.miaosha.model.MiaoshaOrder;
import com.genericyzh.miaosha.order.model.OrderInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderDao {

    public MiaoshaOrder getMiaoshaOrderByUserIdGoodsId(@Param("userId") long userId, @Param("goodsId") long goodsId);

    public long insert(OrderInfo orderInfo);

    public int insertMiaoshaOrder(MiaoshaOrder miaoshaOrder);

    public OrderInfo getOrderById(@Param("orderId") long orderId);

    public void deleteOrders();

    public void deleteMiaoshaOrders();


}
