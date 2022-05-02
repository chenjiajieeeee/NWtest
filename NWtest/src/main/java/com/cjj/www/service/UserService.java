package com.cjj.www.service;

import com.cjj.www.pojo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface UserService {
    /**
     *
     * @param username 注册人的用户名
     * @param password 注册人的密码
     * @param mail 注册人的邮箱
     * @return “邮箱格式不正确” “用户名存在” “注册成功”
     */
    String userRegister(String username,String password,String mail);

    /**
     * 对应servlet的转移
     * @param request 对应servlet的req
     * @param response 对应servlet的resp
     */
    void userRegister(HttpServletRequest request, HttpServletResponse response);

    /**
     *
     * @param request 对应servlet的req
     * @param response 对应servlet的resp
     */
    void confirm(HttpServletRequest request,HttpServletResponse response);

    /**
     *
     * @param username 用于校验的用户名
     * @param password 用于校验的密码
     * @return “登录成功” “账号未激活” “账号或密码错误”
     */
    String userLogin(String username, String password);

    /**
     *
     * @param request 对应servlet的req
     * @param response 对应servlet的resp
     */
    void userLogin(HttpServletRequest request,HttpServletResponse response);

    /**
     *
     * @param username 查询的用户的用户名
     * @return user对象，包含该用户的信息
     */
    User queryUserByUserName(String username);

    /**
     *
     * @param userId 查询的用户的用户id
     * @return user对象，包含该用户的信息
     */
    User queryUserByUserId(Integer userId);

    /**
     * 添加好友的servlet
     * @param request 对应servlet的req
     * @param response 对应servlet的resp
     */
    void addFriend(HttpServletRequest request,HttpServletResponse response);

    /**
     *
     * @param userId 执行添加操作的用户id
     * @param friendId 被添加的用户的id
     * @return “添加成功！” “不要重复添加”
     */
    String addFriend(Integer userId,Integer friendId);

    /**
     * 查找好友
     * @param userId 进行查找操作的用户的id
     * @return list集合，用户对象的集合
     */
    List<User> viewFriend(Integer userId);

    /**
     * 查找粉丝
     * @param userId 进行查找操作的用户id
     * @return list集合，用户对象集合
     */
    List<User> viewFans(Integer userId);

    /**
     * 激活用户
     * @param  username 需要进行激活的用户的用户名
     */
    void activateUser(String username);

    /**
     * 查找历史记录的servlet
     * @param request 对应servlet的req
     * @param response 对应servlet的resp
     */
    void history(HttpServletRequest request,HttpServletResponse response);
}
