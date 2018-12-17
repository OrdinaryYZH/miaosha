package com.genericyzh.miaosha.goods.service;

import com.genericyzh.miaosha.goods.model.Goods;
import com.genericyzh.miaosha.goods.model.MiaoshaGoods;
import com.genericyzh.miaosha.goods.model.vo.GoodsDetailVO;
import com.genericyzh.miaosha.goods.model.vo.GoodsVO;

import java.util.List;

public interface GoodsService {


    List<GoodsVO> listGoodsVo();

    List<Goods> listGoods();

    List<MiaoshaGoods> listMiaoshaGoods();

    GoodsDetailVO.MiaoshaGoodsDetail getMiaoshaGoodDetail(long goodsId);

    MiaoshaGoods getMiaoshaGood(long goodsId);

    Goods getGoods(long goodsId);

    boolean reduceStock(long goodId);

    /**
     * 设置秒杀商品库存
     *
     * @param goodsList
     */
    void resetStock(List<MiaoshaGoods> goodsList);


}
