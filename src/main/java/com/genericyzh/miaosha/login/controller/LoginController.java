package com.genericyzh.miaosha.login.controller;

import com.genericyzh.miaosha.login.model.Token;
import com.genericyzh.miaosha.login.model.query.LoginQuery;
import com.genericyzh.miaosha.redis.key.MiaoshaUserKey;
import com.genericyzh.miaosha.user.model.vo.UserVO;
import com.genericyzh.miaosha.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author genericyzh
 * @date 2018/10/4 15:38
 */
@Controller
@RequestMapping("login")
public class LoginController {

    public static final String COOKI_NAME_TOKEN = "token";

    @Autowired
    private UserService userService;

    /**
     * logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("/to_login")
    public String toLogin() {
        LOGGER.info("进入了");
        return "login";
    }

    @RequestMapping("do_login")
    @ResponseBody
    public Token login(HttpServletResponse response, LoginQuery loginQuery) {
        // Spring MVC对于String的返回类型即使使用了@ResponseBody，Content-Type会变成text/html;目前没找到好的解决办法，只好封装一个类先，而且这样输出文档时也更加好
        String token = userService.login(loginQuery);
        addCookie(response, token);
        return new Token(token);
    }

    private void addCookie(HttpServletResponse response, String token) {
        Cookie cookie = new Cookie(COOKI_NAME_TOKEN, token);
        cookie.setMaxAge(MiaoshaUserKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }

}
