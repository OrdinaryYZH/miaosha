package com.genericyzh.miaosha.good.service.impl;

import com.genericyzh.miaosha.good.dao.GoodDao;
import com.genericyzh.miaosha.good.model.vo.GoodDetailVO;
import com.genericyzh.miaosha.good.model.vo.GoodVO;
import com.genericyzh.miaosha.good.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodServiceImpl implements GoodService {

    @Autowired
    GoodDao goodsDao;

    public List<GoodVO> listGoodsVo() {
        return goodsDao.listGoodsVo();
    }

    public GoodDetailVO.MiaoshaGoodDetail getGoodsVoByGoodsId(long goodsId) {
        return goodsDao.getGoodsVoByGoodsId(goodsId);
    }

    public boolean reduceStock(GoodVO goods) {
        return false;
    }

    public void resetStock(List<GoodVO> goodsList) {
        return;
    }


}
