package com.genericyzh.miaosha.login.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author genericyzh
 * @date 2018/10/4 15:38
 */
@Controller
@RequestMapping("Login")
public class LoginController {

    /**
     * logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("/to_login")
    public String toLogin() {
        return "login";
    }



}
