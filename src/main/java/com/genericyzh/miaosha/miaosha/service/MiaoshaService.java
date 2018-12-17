package com.genericyzh.miaosha.miaosha.service;

import com.genericyzh.miaosha.goods.model.MiaoshaGoods;
import com.genericyzh.miaosha.order.model.OrderInfo;
import com.genericyzh.miaosha.rabbitmq.MiaoshaMessage;
import com.genericyzh.miaosha.user.model.UserInfo;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * @author genericyzh
 * @date 2018/10/10 20:28
 */
public interface MiaoshaService {


    long getMiaoshaResult(Long userId, long goodsId);

    void setGoodsOver(Long goodsId);

    boolean getGoodsOver(long goodsId);

//    void reset(List<GoodsVo> goodsList);

    boolean checkPath(UserInfo user, long goodsId, String path);

    String createMiaoshaPath(UserInfo user, long goodsId);

    /**
     * 生成验证码，并将答案存到redis中
     *
     * @param user
     * @param goodsId
     * @return
     */
    BufferedImage createVerifyCode(UserInfo user, long goodsId);

    boolean checkVerifyCode(UserInfo user, long goodsId, int verifyCode);

    void checkBeforeMiaosha(long goodsId, String path);

    OrderInfo miaosha(MiaoshaMessage message);

    void reset(List<MiaoshaGoods> goodsList);

}
