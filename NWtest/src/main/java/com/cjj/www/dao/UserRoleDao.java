package com.cjj.www.dao;


import java.util.List;

public interface UserRoleDao {
    /**
     * 更新用户名
     * @param oldUsername 用户本来的用户名
     * @param newUsername 用户新的用户名
     */
    void updateUserName(String oldUsername, String newUsername);

    /**
     * 更新密码
     * @param username 需要更新密码的用户
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @param newSalt 新密码盐
     * @return ture为修改成功，false为修改失败
     */
    boolean updatePassword(String username,String oldPassword,String newPassword,String newSalt);

    /**
     *
     * @param username 用户名，进行查找的对象
     * @return list集合，元素为report对象中的noteId
     */
    List<Integer> queryReportNoteByUsername(String username);
}
