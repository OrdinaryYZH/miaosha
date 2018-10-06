package com.genericyzh.miaosha.access;

import com.genericyzh.miaosha.redis.RedisClient;
import com.genericyzh.miaosha.redis.key.MiaoshaUserKey;
import com.genericyzh.miaosha.utils.CookieUtil;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author genericyzh
 * @date 2018/10/6 16:13
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    public static final String LOGIN_ADDRESS = "/login/to_login";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Cookie不存在token的话，跳到登陆页面
        String token = CookieUtil.readLoginToken(request);
        if (token == null) {
            response.sendRedirect(LOGIN_ADDRESS);
            return false;
        }

        // 缓存中不存在token的话，也跳到登陆页面
        Boolean flag = RedisClient.execute(jedis -> jedis.exists(MiaoshaUserKey.token.appendPrefix(token)));
        if (flag == false) {
            response.sendRedirect(LOGIN_ADDRESS);
            return false;
        }

        // 延长token时间
        RedisClient.execute(jedis -> jedis.expire(token, MiaoshaUserKey.token.expireSeconds()));
        CookieUtil.writeLoginToken(response, token);

        return true;
    }
}
