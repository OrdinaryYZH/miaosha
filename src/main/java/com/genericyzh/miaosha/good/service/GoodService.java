package com.genericyzh.miaosha.good.service;

import com.genericyzh.miaosha.good.model.vo.GoodVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GoodService {


    public List<GoodVo> listGoodsVo();

    public GoodVo getGoodsVoByGoodsId(long goodsId);

    public boolean reduceStock(GoodVo goods);

    public void resetStock(List<GoodVo> goodsList);


}
