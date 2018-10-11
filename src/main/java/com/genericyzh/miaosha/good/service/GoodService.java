package com.genericyzh.miaosha.good.service;

import com.genericyzh.miaosha.good.model.Good;
import com.genericyzh.miaosha.good.model.MiaoshaGood;
import com.genericyzh.miaosha.good.model.vo.GoodDetailVO;
import com.genericyzh.miaosha.good.model.vo.GoodVO;

import java.util.List;

public interface GoodService {


    List<GoodVO> listGoodsVo();

    List<MiaoshaGood> listGoods();

    GoodDetailVO.MiaoshaGoodDetail getMiaoshaGoodDetail(long goodsId);

    MiaoshaGood getMiaoshaGood(long goodsId);

    boolean reduceStock(long goodId);

    void resetStock(List<GoodVO> goodsList);


}
