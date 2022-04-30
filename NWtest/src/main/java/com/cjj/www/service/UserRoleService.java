package com.cjj.www.service;

import com.cjj.www.pojo.Note;
import com.cjj.www.pojo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserRoleService {
    /**
     * 用户可以修改自己的个人信息
     * 修改完成后，请求发送到登录页面，让用户重新登录
     * 修改又可以分为：修改用户名、修改用户密码、修改用户名和用户密码
     * 修改成功就返回true
     */
    void updateUserInformation(HttpServletRequest request, HttpServletResponse response);


     String updateUsername(String oldUsername,String newUsername);


     boolean updatePassword(String username,String oldPassword,String newPassword);
    /**
     * 用户可以修改自己已经发表的笔记
     * 修改的话不能为空，如果发现修改后的笔记为空则提醒用户是否选择删除
     */
     boolean addFriend(Integer userId,Integer friendId);

    /**
     * 聊天的业务：用于跳转页面
     */
     void chat(HttpServletRequest request,HttpServletResponse response);

    /**
     * 举报业务
     */
    void report(HttpServletRequest request,HttpServletResponse response);
}
