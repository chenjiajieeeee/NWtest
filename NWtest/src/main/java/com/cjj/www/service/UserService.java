package com.cjj.www.service;

import com.cjj.www.pojo.User;

import java.util.List;

public interface UserService {
    boolean userLogin(String username,String password);
    boolean userRegister(String username,String password);
    User queryUserByUserName(String username);
    User queryUserByUserId(Integer userId);
    String addFriend(Integer userId,Integer friendId);
    List<User> viewFriend(Integer userId);
    List<User> viewFans(Integer userId);
}
