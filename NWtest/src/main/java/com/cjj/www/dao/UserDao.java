package com.cjj.www.dao;

import com.cjj.www.pojo.User;

import java.util.List;

public interface UserDao {
    /**
     * 保存用户信息
     * 用户信息包括：用户名，密码
     */
    boolean saveUser(User user);
    /**
     * 检查是否有这个用户的信息
     * 用户名是否存在
     * 密码是否正确
     * 返回true or false 来确认
     */
     boolean check(String username,String password);
    /**
     * 修改用户自己的个人信息（除了id号）
     * 且要保证用户名不能重复
     * 也是返回true 或者 false来确认是否修改成功
     * 因为只可能是用户名重复
     */
    /**
     * 查询用户信息
     * 登录之后用来显示用户的个人信息
     * 返回User类
     */
    User queryUserByUserName(String username);
    User queryUserByUserId(Integer userId);
    Integer countUser();
    //申诉之后对应管理员被申诉的次数加一
    //添加好友的操作：
    boolean addFriend(Integer userId,Integer friendId);
    //显示好友列表：
    List<Integer> queryFriend(Integer userId);
}
