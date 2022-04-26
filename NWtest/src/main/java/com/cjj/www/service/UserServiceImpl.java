package com.cjj.www.service;

import com.cjj.www.dao.UserDao;
import com.cjj.www.dao.UserDaoImpl;
import com.cjj.www.pojo.User;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public String addFriend(Integer userId, Integer friendId) {
        if(userId.equals(friendId)){
            return "不要添加自己为好友！";
        }else {
            UserDao userDao=new UserDaoImpl();
            List<Integer> friendsId = userDao.queryFriend(userId);
            for (Integer id:friendsId){
                if(id.equals(friendId)){
                    return "已经添加过这个用户了！";
                }
            }
            userDao.addFriend(userId,friendId);
            return "添加好友成功！";
        }
    }

    @Override
    public List<User> viewFriend(Integer userId) {
        UserDao userDao=new UserDaoImpl();
        //找到好友的id
        List<Integer> friends = userDao.queryFriend(userId);
        //根据好友id查询好友信息如好友的名字
        List<User> users=new ArrayList<>();
        for (Integer id:friends){
            users.add(userDao.queryUserByUserId(id));
        }
        return users;
    }

    @Override
    public List<User> viewFans(Integer userId) {
        UserDao userDao=new UserDaoImpl();
        List<Integer> fans = userDao.queryFans(userId);
        List<User> users=new ArrayList<>();
        for (Integer id:fans){
            users.add(userDao.queryUserByUserId(id));
        }
        return users;
    }
}
