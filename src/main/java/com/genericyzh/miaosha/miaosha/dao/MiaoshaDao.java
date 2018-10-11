package com.genericyzh.miaosha.miaosha.dao;

import com.genericyzh.miaosha.miaosha.model.bo.MiaoshaGoodBO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author genericyzh
 * @date 2018/10/11 15:12
 */
@Mapper
public interface MiaoshaDao {

    MiaoshaGoodBO getMiaoshaGoodBO(String goodId);

}
