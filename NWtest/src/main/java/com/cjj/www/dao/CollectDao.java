package com.cjj.www.dao;

import com.cjj.www.pojo.Collect;

import java.util.List;

public interface CollectDao {
    /*
    点赞和收藏唯一的不同就是他不用算有多少个收藏的。。
     */
    boolean addCollect(Collect collect);
    boolean cancelCollect(Integer noteId,Integer userId);
    boolean judgeCollectOrNot(Integer noteId,Integer userId);
    List<Integer> queryCollectNoteByUserId(Integer userId);
}
