package com.genericyzh.miaosha.order.controller;

import com.genericyzh.miaosha.common.exception.BusinessException;
import com.genericyzh.miaosha.good.model.Good;
import com.genericyzh.miaosha.good.service.GoodService;
import com.genericyzh.miaosha.order.model.OrderInfo;
import com.genericyzh.miaosha.order.model.vo.OrderDetailVo;
import com.genericyzh.miaosha.order.service.OrderService;
import com.genericyzh.miaosha.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    UserService userService;

    @Autowired
    OrderService orderService;

    @Autowired
    GoodService goodService;

    @RequestMapping("/detail")
    @ResponseBody
    public OrderDetailVo info(
            @RequestParam("orderId") long orderId) {
        OrderInfo order = orderService.getOrderById(orderId);
        if (order == null) {
            throw new BusinessException("订单不存在");
        }
        long goodsId = order.getGoodsId();
        Good good = goodService.getGood(goodsId);
        OrderDetailVo vo = new OrderDetailVo();
        vo.setOrder(order);
        vo.setGood(good);
        return vo;
    }

}
