package com.cjj.www.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserRoleService {
    /**
     * 对应servlet的转移
     * @param request 对应servlet的req
     * @param response 对应servlet的resp
     */
    void updateUserInformation(HttpServletRequest request, HttpServletResponse response);

    /**
     *
     * @param oldUsername 该用户旧的用户名
     * @param newUsername 该用户新的用户名
     * @return “不能为空” “修改成功”
     */
     String updateUsername(String oldUsername,String newUsername);

    /**
     *
     * @param username 该用户的用户名
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return ture为修改成功 false为修改失败
     */
     boolean updatePassword(String username,String oldPassword,String newPassword);

    /**
     *
     * @param userId 执行添加操作的用户的id
     * @param friendId 被添加的用户的id
     * @return ture为添加成功，false为添加失败
     */
     boolean addFriend(Integer userId,Integer friendId);

     /**
     *
     * @param request 对应servlet的req
     * @param response 对应servlet的resp
     */
     void chat(HttpServletRequest request,HttpServletResponse response);

    /**
     *
     * @param request 对应servlet的req
     * @param response 对应servlet的resp
     */
    void report(HttpServletRequest request,HttpServletResponse response);
}
