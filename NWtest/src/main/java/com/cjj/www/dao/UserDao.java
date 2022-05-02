package com.cjj.www.dao;

import com.cjj.www.pojo.User;

import java.util.List;

public interface UserDao {
    /**
     * 保存用户信息
     * @param user user对象，包含用户信息
     * @return ture为保存成功，false为保存失败
     */
    boolean saveUser(User user);

    /**
     * 校验用户
     * @param username 校验的用户名
     * @param password 校验的用户密码
     * @return ture为校验一致，false为校验不一致
     */
     boolean check(String username,String password);

    /**
     * 根据用户名查询用户信息
     * @param username 查询的用户名
     * @return user对象，为用户的信息
     */
    User queryUserByUserName(String username);

    /**
     * 根据用户id查询用户信息
     * @param userId 查询的用户id
     * @return user对象，为用户的信息
     */
    User queryUserByUserId(Integer userId);

    /**
     * 查询注册用户注册的总数
     * @return count，注册用户总数
     */
    Integer countUser();

    /**
     * 添加好友，将关系存入数据库
     * @param userId 进行添加的用户的id
     * @param friendId 被添加的用户的id
     * @return ture为添加成功，false为添加失败
     */
    boolean addFriend(Integer userId,Integer friendId);

    /**
     * 查询好友
     * @param userId 进行查询的用户的id
     * @return list集合 好友id的集合
     */
    List<Integer> queryFriend(Integer userId);

    /**
     * 查询粉丝
     * @param friendId 进行查询的用户的id
     * @return list集合 粉丝id的集合
     */
    List<Integer> queryFans(Integer friendId);

    /**
     * 激活用户
     * @param username 被激活的用户的id
     */
    void activateUser(String username);
}
