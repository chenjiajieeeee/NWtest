package com.cjj.www.service;

import com.cjj.www.dao.UserDao;
import com.cjj.www.dao.UserDaoImpl;
import com.cjj.www.pojo.User;
import com.cjj.www.util.CodeUtil;
import com.cjj.www.util.Encryption;
import com.cjj.www.util.MailUtil;


import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService{
    @Override
    public String userLogin(String username, String password) {
        UserDao userDao=new UserDaoImpl();
        User user = userDao.queryUserByUserName(username);
        if(userDao.check(username, password)){
            if(user.getActivateStatus().equals("0")){
                return "该账号未激活，请先激活！";
            }else {
                return "登录成功！";
            }
        }else {
            return "账号或密码错误";
        }
    }

    @Override
    public String userRegister(String username, String password,String mail) {
        if (!mail.matches("^\\w+@(\\w+\\.)+\\w+$")) {
            return "邮箱格式不正确！";
        }
        UserDao ud = new UserDaoImpl();
        User user1 = ud.queryUserByUserName(username);
        if (user1.getUsername() == null) {
            //生成随机激活码
            String code = CodeUtil.generateUniqueCode();
            User user = new User();
            user.setUsername(username);
            Encryption encryption=new Encryption();
            String newPassword=encryption.encryptMD5(password);
            user.setPassword(newPassword);
            user.setMail(mail);
            user.setCode(code);
            if(ud.saveUser(user)){
                new Thread(new MailUtil(mail,code)).start();
                return "正在跳转页面";
            }else {
                return "服务器出问题了，该死！";
            }
        } else {
            return "用户名已存在！";
        }
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

    @Override
    public boolean activateUser(String username) {
        UserDao userDao=new UserDaoImpl();
        return userDao.activateUser(username);
    }
}
