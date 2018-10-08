package com.genericyzh.miaosha.good.service;

import com.genericyzh.miaosha.good.model.vo.GoodDetailVO;
import com.genericyzh.miaosha.good.model.vo.GoodVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GoodService {


    public List<GoodVO> listGoodsVo();

    public GoodDetailVO.MiaoshaGoodDetail getGoodsVoByGoodsId(long goodsId);

    public boolean reduceStock(GoodVO goods);

    public void resetStock(List<GoodVO> goodsList);


}
