package com.cjj.www.dao;

import com.cjj.www.pojo.Like;

import java.util.List;

public interface LikeActDao {
    /*
     * 思路：用户点击喜欢，将目前的点赞数加一
     * 用户再次点击喜欢，将目前的点赞数减一，表示取消点赞
     * 同时，如果是喜欢：like表中插入点赞的笔记的id，点赞的用户的id。
     *      如果是取消：like表中删除对应noteId以及userId的字段
     *
     */
    boolean addLike(Like like);
    boolean cancelLike(Integer noteId,Integer userId);
    /*
    判断用户是否点过赞了
    没点赞：调用addlike
    点过赞：调用cancelike
    由service完成
     */
    boolean judgeLikeOrNot(Integer noteId,Integer UserId);
    List<Integer> queryLikeNoteByUserId(Integer userId);
}
