package com.genericyzh.miaosha.goods.service.impl;

import com.genericyzh.miaosha.goods.dao.GoodsDao;
import com.genericyzh.miaosha.goods.model.Goods;
import com.genericyzh.miaosha.goods.model.MiaoshaGoods;
import com.genericyzh.miaosha.goods.model.vo.GoodsDetailVO;
import com.genericyzh.miaosha.goods.model.vo.GoodsVO;
import com.genericyzh.miaosha.goods.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    GoodsDao goodsDao;

    @Override
    public List<GoodsVO> listGoodsVo() {
        return goodsDao.listGoodsVo();
    }

    @Override
    public List<Goods> listGoods() {
        return goodsDao.listGoods();
    }

    @Override
    public List<MiaoshaGoods> listMiaoshaGoods() {
        return goodsDao.listMiaoshaGoods();
    }

    @Override
    public GoodsDetailVO.MiaoshaGoodsDetail getMiaoshaGoodDetail(long goodsId) {
        return goodsDao.getGoodsVoByGoodsId(goodsId);
    }

    @Override
    public MiaoshaGoods getMiaoshaGood(long goodsId) {
        return goodsDao.getMiaoshaGoods(goodsId);
    }

    @Override
    public Goods getGoods(long goodsId) {
        return goodsDao.getGoods(goodsId);
    }

    @Override
    public boolean reduceStock(long goodId) {
        MiaoshaGoods g = new MiaoshaGoods();
        g.setGoodsId(goodId);
        int ret = goodsDao.reduceStock(g);
        return ret > 0;
    }

    @Override
    public void resetStock(List<MiaoshaGoods> goodsList) {
        for (MiaoshaGoods miaoshaGoods : goodsList) {
            goodsDao.resetStock(miaoshaGoods);
        }
    }


}
