package com.genericyzh.miaosha.user.service;

import com.genericyzh.miaosha.login.model.query.LoginQuery;
import com.genericyzh.miaosha.user.model.bo.UserBO;
import com.genericyzh.miaosha.user.model.view.UserDBView;
import com.genericyzh.miaosha.user.model.vo.UserVO;

/**
 * @author genericyzh
 * @date 2018/10/5 15:42
 */
public interface UserService {

    public UserDBView getById(long id);

    public boolean updatePassword(String token, long id, String formPass);


    public UserVO getByToken(String token);


    public String login(LoginQuery loginQuery);

}
