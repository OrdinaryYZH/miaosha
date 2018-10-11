package com.genericyzh.miaosha.good.service.impl;

import com.genericyzh.miaosha.good.dao.GoodDao;
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

    public List<GoodVO> listGoodsVo() {
        return goodDao.listGoodsVo();
    }

    @Override
    public List<MiaoshaGood> listGoods() {
        return null;
    }

    public GoodDetailVO.MiaoshaGoodDetail getMiaoshaGoodDetail(long goodsId) {
        return goodDao.getGoodsVoByGoodsId(goodsId);
    }

    @Override
    public MiaoshaGood getMiaoshaGood(long goodsId) {
        return goodDao.getMiaoshaGood(goodsId);
    }

    @Override
    public boolean reduceStock(long goodId) {
        return false;
    }

    public void resetStock(List<GoodVO> goodsList) {
        return;
    }


}
