package com.cjj.www.dao;

import com.cjj.www.pojo.Like;

import java.util.List;

public interface LikeActDao {
    /**
     *
     * @param like 点赞的信息对象
     * @return ture为添加成功，false为添加失败
     */
    boolean addLike(Like like);

    /**
     *
     * @param noteId 需要取消点赞的笔记的id
     * @param userId 进行该操作的用户的id
     * @return ture为取消成功，false为取消失败
     */
    boolean cancelLike(Integer noteId,Integer userId);

    /**
     *
     * @param noteId 进行判断的笔记的id
     * @param UserId 进行判断的用户的id
     * @return ture为该用户已经点赞 false为该用户已经取消点赞
     */
    boolean judgeLikeOrNot(Integer noteId,Integer UserId);

    /**
     *
     * @param userId 进行查询操作的用户的id
     * @return list集合，为该用户点赞了的所有的笔记
     */
    List<Integer> queryLikeNoteByUserId(Integer userId);
}
