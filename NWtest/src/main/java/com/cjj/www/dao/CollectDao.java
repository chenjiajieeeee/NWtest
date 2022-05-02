package com.cjj.www.dao;

import com.cjj.www.pojo.Collect;

import java.util.List;

public interface CollectDao {
    /**
     *
     * @param collect 封装好collect对象，里面包含收藏信息
     */
    void addCollect(Collect collect);

    /**
     *
     * @param noteId 取消收藏的笔记的id
     * @param userId 进行该操作的用户的id
     */
    void cancelCollect(Integer noteId, Integer userId);

    /**
     *
     * @param noteId 进行判断的笔记的id
     * @param userId 进行判断的用户的id
     * @return ture为该用户已经收藏，false为未收藏
     */
    boolean judgeCollectOrNot(Integer noteId,Integer userId);

    /**
     *
     * @param userId 进行查询操作的用户的id
     * @return list集合，为已经收藏了的的笔记的id
     */
    List<Integer> queryCollectNoteByUserId(Integer userId);
}
