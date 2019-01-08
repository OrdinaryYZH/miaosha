package com.genericyzh.miaosha.login.controller;

import com.genericyzh.miaosha.login.model.query.LoginQuery;
import com.genericyzh.miaosha.redis.key.MiaoshaUserKey;
import com.genericyzh.miaosha.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
        return "login";
    }

    @RequestMapping(value = "do_login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String login(HttpServletResponse response, @Valid LoginQuery loginQuery) {
        String token = userService.login(loginQuery);
        addCookie(response, token);
        return token;
    }

    private void addCookie(HttpServletResponse response, String token) {
        Cookie cookie = new Cookie(COOKI_NAME_TOKEN, token);
        cookie.setMaxAge(MiaoshaUserKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }

}
