package com.cjj.www.service;

import com.cjj.www.pojo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface UserService {

    String userRegister(String username,String password,String mail);
    void userRegister(HttpServletRequest request, HttpServletResponse response);
    void confirm(HttpServletRequest request,HttpServletResponse response);

    String userLogin(String username, String password);
    void userLogin(HttpServletRequest request,HttpServletResponse response);

    User queryUserByUserName(String username);
    User queryUserByUserId(Integer userId);

    void addFriend(HttpServletRequest request,HttpServletResponse response);
    String addFriend(Integer userId,Integer friendId);


    List<User> viewFriend(Integer userId);
    List<User> viewFans(Integer userId);
    boolean activateUser(String username);

    void history(HttpServletRequest request,HttpServletResponse response);
}
