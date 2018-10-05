package com.genericyzh.miaosha.user.model.bo;

import com.genericyzh.miaosha.user.model.vo.UserVO;

/**
 * @author genericyzh
 * @date 2018/10/5 16:11
 */
public class UserBO {
    private String token;

    private UserVO userVO;

    public UserBO() {
    }

    public UserBO(String token, UserVO userVO) {
        this.token = token;
        this.userVO = userVO;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserVO getUserVO() {
        return userVO;
    }

    public void setUserVO(UserVO userVO) {
        this.userVO = userVO;
    }
}
