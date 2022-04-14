package com.cjj.www.service;

import com.cjj.www.pojo.User;

public interface UserService {
    boolean userLogin(String username,String password);
    boolean userRegister(String username,String password);
    User queryUserByUserName(String username);
    User queryUserByUserId(Integer userId);
}
