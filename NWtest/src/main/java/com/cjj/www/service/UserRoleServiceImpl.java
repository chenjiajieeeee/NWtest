package com.cjj.www.service;

import com.cjj.www.dao.*;
import com.cjj.www.pojo.User;
import com.cjj.www.util.Encryption;

public class UserRoleServiceImpl implements UserRoleService{
    @Override
    public String updateUsername(String oldName,String newName) {
        UserRoleDao userRoleDao=new UserRoleDaoImpl();
        if(newName==""){
            return "用户名不能为空!";
        }
        else {
            UserDao userDao=new UserDaoImpl();
            User user = userDao.queryUserByUserName(newName);
            if(user.getUsername()==null){
                userRoleDao.updateUserName(oldName,newName);
                return "修改用户名成功!";
            }else {
                return "该用户名已经存在了!";
            }
        }
    }

    @Override
    public boolean updatePassword(String username,String oldPassword, String newPassword) {
        if(newPassword==""){
            return false;
        }
        else {
            UserRoleDao userRoleDao = new UserRoleDaoImpl();
            Encryption encryption=new Encryption();
            String OldPassword = encryption.encryptMD5(oldPassword);
            String NewPassword = encryption.encryptMD5(newPassword);
            return userRoleDao.updatePassword(username, OldPassword, NewPassword);
        }
    }

    @Override
    public boolean addFriend(Integer userId, Integer friendId) {
        UserDao userDao=new UserDaoImpl();
        return userDao.addFriend(userId,friendId);
    }

}
