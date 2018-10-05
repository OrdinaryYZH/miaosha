package com.genericyzh.miaosha.user.dao;

import com.genericyzh.miaosha.user.model.view.UserDBView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDao {

    @Select("select * from miaosha_user where id = #{id}")
    UserDBView getById(@Param("id") long id);

//    @Update("update miaosha_user set password = #{password} where id = #{id}")
//    public void update(MiaoshaUser toBeUpdate);
}