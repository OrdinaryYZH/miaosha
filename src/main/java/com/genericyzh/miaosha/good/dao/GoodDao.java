package com.genericyzh.miaosha.good.dao;

import com.genericyzh.miaosha.good.model.Good;
import com.genericyzh.miaosha.good.model.MiaoshaGood;
import com.genericyzh.miaosha.good.model.vo.GoodDetailVO;
import com.genericyzh.miaosha.good.model.vo.GoodVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface GoodDao {

    List<GoodVO> listGoodsVo();

    List<Good> listGoods();

    List<MiaoshaGood> listMiaoshaGoods();

    MiaoshaGood getMiaoshaGood(long goodsId);

    Good getGood(long goodsId);

    GoodDetailVO.MiaoshaGoodDetail getGoodsVoByGoodsId(@Param("goodId") long goodId);

    @Update("update miaosha_goods set stock_count = stock_count - 1 where goods_id = #{goodsId} and stock_count > 0")
    int reduceStock(MiaoshaGood g);

    @Update("update miaosha_goods set stock_count = #{stockCount} where goods_id = #{goodsId}")
    int resetStock(MiaoshaGood g);

}
