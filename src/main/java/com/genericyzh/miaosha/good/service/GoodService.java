package com.genericyzh.miaosha.good.service;

import com.genericyzh.miaosha.good.model.Good;
import com.genericyzh.miaosha.good.model.MiaoshaGood;
import com.genericyzh.miaosha.good.model.vo.GoodDetailVO;
import com.genericyzh.miaosha.good.model.vo.GoodVO;

import java.util.List;

public interface GoodService {


    List<GoodVO> listGoodsVo();

    List<Good> listGoods();

    List<MiaoshaGood> listMiaoshaGoods();

    GoodDetailVO.MiaoshaGoodDetail getMiaoshaGoodDetail(long goodsId);

    MiaoshaGood getMiaoshaGood(long goodsId);

    Good getGood(long goodsId);

    boolean reduceStock(long goodId);

    /**
     * 设置秒杀商品库存
     *
     * @param goodsList
     */
    void resetStock(List<MiaoshaGood> goodsList);


}
