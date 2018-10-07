package com.genericyzh.miaosha.good.service.impl;

import com.genericyzh.miaosha.good.dao.GoodDao;
import com.genericyzh.miaosha.good.model.vo.GoodVo;
import com.genericyzh.miaosha.good.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodServiceImpl implements GoodService {

    @Autowired
    GoodDao goodsDao;

    public List<GoodVo> listGoodsVo() {
        return goodsDao.listGoodsVo();
    }

    public GoodVo getGoodsVoByGoodsId(long goodsId) {
        return goodsDao.getGoodsVoByGoodsId(goodsId);
    }

    public boolean reduceStock(GoodVo goods) {
        return false;
    }

    public void resetStock(List<GoodVo> goodsList) {
        return;
    }


}
