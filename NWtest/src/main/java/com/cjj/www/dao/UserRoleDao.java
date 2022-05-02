package com.cjj.www.dao;

import com.cjj.www.pojo.Note;

public interface UserRoleDao {
    boolean updateUserName(String oldUsername,String newUsername);
    boolean updatePassword(String username,String oldPassword,String newPassword,String newSalt);
}
