package com.genericyzh.miaosha.utils;


import com.genericyzh.miaosha.redis.key.MiaoshaUserKey;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {

    /**
     * logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CookieUtil.class);

    private static final String COOKIE_NAME = "token";

    public static String readLoginToken(HttpServletRequest request) {
        Cookie[] cks = request.getCookies();
        if (cks != null) {
            for (Cookie ck : cks) {
                if (StringUtils.equals(ck.getName(), COOKIE_NAME)) {
                    return ck.getValue();
                }
            }
        }
        return null;
    }

    //X:domain=".happymmall.com"
    //a:A.happymmall.com            cookie:domain=A.happymmall.com;path="/"
    //b:B.happymmall.com            cookie:domain=B.happymmall.com;path="/"
    //c:A.happymmall.com/test/cc    cookie:domain=A.happymmall.com;path="/test/cc"
    //d:A.happymmall.com/test/dd    cookie:domain=A.happymmall.com;path="/test/dd"
    //e:A.happymmall.com/test       cookie:domain=A.happymmall.com;path="/test"

    public static void writeLoginToken(HttpServletResponse response, String token) {
        Cookie ck = new Cookie(COOKIE_NAME, token);
//        ck.setDomain();
        ck.setPath("/");//代表设置在根目录
        ck.setHttpOnly(true);
        ck.setMaxAge(MiaoshaUserKey.token.expireSeconds());
        response.addCookie(ck);
    }


    public static void delLoginToken(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cks = request.getCookies();
        if (cks != null) {
            for (Cookie ck : cks) {
                if (StringUtils.equals(ck.getName(), COOKIE_NAME)) {
//                    ck.setDomain();
                    ck.setPath("/");
                    ck.setMaxAge(0);
                    response.addCookie(ck);
                    return;
                }
            }
        }
    }


}
