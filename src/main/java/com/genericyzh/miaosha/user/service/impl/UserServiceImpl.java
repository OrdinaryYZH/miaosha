package com.genericyzh.miaosha.user.service.impl;

import com.genericyzh.miaosha.common.exception.BusinessException;
import com.genericyzh.miaosha.login.model.query.LoginQuery;
import com.genericyzh.miaosha.redis.RedisClient;
import com.genericyzh.miaosha.redis.key.MiaoshaUserKey;
import com.genericyzh.miaosha.user.dao.UserDao;
import com.genericyzh.miaosha.user.model.UserInfo;
import com.genericyzh.miaosha.user.model.vo.UserVO;
import com.genericyzh.miaosha.user.service.UserService;
import com.genericyzh.miaosha.utils.MD5Util;
import com.genericyzh.miaosha.utils.SerializeUtil;
import com.genericyzh.miaosha.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author genericyzh
 * @date 2018/10/5 15:54
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    RedisClient redisClient;

    @Autowired
    private UserDao userDao;

    @Override
    public UserInfo getById(String id) {
        return userDao.getById(id);
    }

    @Override
    public boolean updatePassword(String token, long id, String formPass) {
        return false;
    }

    @Override
    public UserVO getByToken(String token) {
        return null;
    }

    @Override
    public String login(LoginQuery loginQuery) {
        String mobile = loginQuery.getMobile();
        String formPass = loginQuery.getPassword();
        //判断手机号是否存在
        UserInfo user = getById(mobile);
        if (user == null) {
            throw new BusinessException("用户不存在或者密码错误");
        }
        //验证密码
        String dbPass = user.getPassword();
        String saltDB = user.getSalt();
        String calcPass = MD5Util.formPassToDBPass(formPass, saltDB);
        if (!calcPass.equals(dbPass)) {
            throw new BusinessException("用户不存在或者密码错误");
        }
        //生成cookie
        String token = UUIDUtil.uuid();
        redisClient.execute(jedis -> jedis.set(MiaoshaUserKey.token.appendPrefix(token),
                SerializeUtil.beanToString(user),
                "nx",
                "ex",
                MiaoshaUserKey.token.expireSeconds()));
        return token;
    }
}
