package com.cjj.www.service;

import com.cjj.www.dao.UserDao;
import com.cjj.www.dao.UserDaoImpl;
import com.cjj.www.pojo.User;

public class UserServiceImpl implements UserService{
    @Override
    public boolean userLogin(String username, String password) {
        UserDao userDao=new UserDaoImpl();
        return userDao.check(username, password);
    }

    @Override
    public boolean userRegister(String username, String password) {
        boolean result=true;
        UserDao ud = new UserDaoImpl();
        User user1 = ud.queryUserByUserName(username);
        if(user1.getUsername()==null){
            User user=new User();
            user.setUsername(username);
            user.setPassword(password);
            ud.saveUser(user);
            result=false;
        }
        return result;
    }

    @Override
    public User queryUserByUserName(String username) {
        UserDao userDao=new UserDaoImpl();
        return userDao.queryUserByUserName(username);

    }

    @Override
    public User queryUserByUserId(Integer userId) {
        UserDao userDao=new UserDaoImpl();
        return userDao.queryUserByUserId(userId);
    }
}
