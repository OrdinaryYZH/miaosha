package com.genericyzh.miaosha.goods.dao;

import com.genericyzh.miaosha.goods.model.Goods;
import com.genericyzh.miaosha.goods.model.MiaoshaGoods;
import com.genericyzh.miaosha.goods.model.vo.GoodsDetailVO;
import com.genericyzh.miaosha.goods.model.vo.GoodsVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface GoodsDao {

    List<GoodsVO> listGoodsVo();

    List<Goods> listGoods();

    List<MiaoshaGoods> listMiaoshaGoods();

    MiaoshaGoods getMiaoshaGoods(long goodsId);

    Goods getGoods(long goodsId);

    GoodsDetailVO.MiaoshaGoodsDetail getGoodsVoByGoodsId(@Param("goodsId") long goodsId);

    @Update("update miaosha_goods set stock_count = stock_count - 1 where goods_id = #{goodsId} and stock_count > 0")
    int reduceStock(MiaoshaGoods g);

    @Update("update miaosha_goods set stock_count = #{stockCount} where goods_id = #{goodsId}")
    int resetStock(MiaoshaGoods g);

}
