package com.genericyzh.miaosha.good.dao;

import com.genericyzh.miaosha.good.model.vo.GoodVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GoodDao {

    public List<GoodVo> listGoodsVo();

    public GoodVo getGoodsVoByGoodsId(@Param("goodsId") long goodsId);

//    @Update("update miaosha_goods set stock_count = stock_count - 1 where goods_id = #{goodsId} and stock_count > 0")
//    public int reduceStock(MiaoshaGoods g);
//
//    @Update("update miaosha_goods set stock_count = #{stockCount} where goods_id = #{goodsId}")
//    public int resetStock(MiaoshaGoods g);

}
