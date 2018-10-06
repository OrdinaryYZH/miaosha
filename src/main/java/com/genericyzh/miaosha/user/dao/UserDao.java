package com.genericyzh.miaosha.user.dao;

import com.genericyzh.miaosha.user.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDao {

    UserInfo getById(@Param("id") String id);

//    @Update("update miaosha_user set password = #{password} where id = #{id}")
//    public void update(MiaoshaUser toBeUpdate);
}