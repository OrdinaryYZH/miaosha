package com.genericyzh.miaosha.order.service.impl;

import com.genericyzh.miaosha.goods.model.MiaoshaGoods;
import com.genericyzh.miaosha.miaosha.model.MiaoshaOrder;
import com.genericyzh.miaosha.order.dao.OrderDao;
import com.genericyzh.miaosha.order.model.OrderInfo;
import com.genericyzh.miaosha.order.service.OrderService;
import com.genericyzh.miaosha.redis.key.OrderKey;
import com.genericyzh.miaosha.user.model.UserInfo;
import com.genericyzh.miaosha.utils.SerializeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static com.genericyzh.miaosha.redis.RedisClient.execute;

/**
 * @author genericyzh
 * @date 2018/10/11 16:46
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public MiaoshaOrder getMiaoshaOrderByUserIdGoodsId(String userId, long goodsId) {
        MiaoshaOrder miaoshaOrder = execute(jedis -> jedis.get(OrderKey.MiaoshaOrderByUidGid.appendPrefix(userId, String.valueOf(goodsId))),
                MiaoshaOrder.class);
        return miaoshaOrder;
    }

    @Override
    public OrderInfo getOrderById(long orderId) {
        return orderDao.getOrderById(orderId);
    }

    @Transactional
    @Override
    public OrderInfo createOrder(UserInfo user, MiaoshaGoods miaoshaGoods) {
        // todo 对miaoshaGood进行校验？？？

        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setCreateDate(new Date());
        orderInfo.setDeliveryAddrId(0L);
        orderInfo.setGoodsCount(1);
        orderInfo.setGoodsId(miaoshaGoods.getId());
        orderInfo.setGoodsName(miaoshaGoods.getGoodsName());
        orderInfo.setGoodsPrice(miaoshaGoods.getMiaoshaPrice());
        orderInfo.setOrderChannel(1);
        orderInfo.setStatus(0);
        orderInfo.setUserId(user.getId());
        orderDao.insert(orderInfo);
        MiaoshaOrder miaoshaOrder = new MiaoshaOrder();
        miaoshaOrder.setGoodsId(miaoshaGoods.getId());
        miaoshaOrder.setOrderId(orderInfo.getId());
        miaoshaOrder.setUserId(user.getId());
        orderDao.insertMiaoshaOrder(miaoshaOrder);

        execute(jedis -> jedis.set(OrderKey.MiaoshaOrderByUidGid.appendPrefix(user.getId(), String.valueOf(miaoshaGoods.getId()))
                , SerializeUtil.beanToString(miaoshaOrder)));
        return orderInfo;
    }

    @Override
    public void deleteOrders() {
        orderDao.deleteOrders();
        orderDao.deleteMiaoshaOrders();
    }
}
