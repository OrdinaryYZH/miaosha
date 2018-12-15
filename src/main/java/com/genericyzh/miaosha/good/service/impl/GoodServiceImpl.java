package com.genericyzh.miaosha.good.service.impl;

import com.genericyzh.miaosha.good.dao.GoodDao;
import com.genericyzh.miaosha.good.model.Good;
import com.genericyzh.miaosha.good.model.MiaoshaGood;
import com.genericyzh.miaosha.good.model.vo.GoodDetailVO;
import com.genericyzh.miaosha.good.model.vo.GoodVO;
import com.genericyzh.miaosha.good.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodServiceImpl implements GoodService {

    @Autowired
    GoodDao goodDao;

    @Override
    public List<GoodVO> listGoodsVo() {
        return goodDao.listGoodsVo();
    }

    @Override
    public List<Good> listGoods() {
        return goodDao.listGoods();
    }

    @Override
    public List<MiaoshaGood> listMiaoshaGoods() {
        return goodDao.listMiaoshaGoods();
    }

    @Override
    public GoodDetailVO.MiaoshaGoodDetail getMiaoshaGoodDetail(long goodsId) {
        return goodDao.getGoodsVoByGoodsId(goodsId);
    }

    @Override
    public MiaoshaGood getMiaoshaGood(long goodsId) {
        return goodDao.getMiaoshaGood(goodsId);
    }

    @Override
    public Good getGood(long goodsId) {
        return null;
    }

    @Override
    public boolean reduceStock(long goodId) {
        MiaoshaGood g = new MiaoshaGood();
        g.setId(goodId);
        int ret = goodDao.reduceStock(g);
        return ret > 0;
    }

    @Override
    public void resetStock(List<MiaoshaGood> goodsList) {
        for (MiaoshaGood miaoshaGood : goodsList) {
            goodDao.resetStock(miaoshaGood);
        }
    }


}
