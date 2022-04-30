package com.cjj.www.dao;

import com.cjj.www.pojo.Collect;

import java.util.List;

public interface CollectDao {
    /*
    addCollect方法：
    当用户点击收藏时，将进行点赞的用户的id已经点赞的笔记id封装成collect对象，并将数据插入数据表中
     */
    boolean addCollect(Collect collect);
    /*
    cancelCollect方法：
    当用户点击取消收藏时，将进行点赞的用户的id以及点赞的笔记id传入删除该行数据
     */
    boolean cancelCollect(Integer noteId,Integer userId);
    /*
    judgeCollectOrNot方法：
    判断用户对某一笔笔记是否已经收藏
    noteId、userId用于核对是否有该行数据
     */
    boolean judgeCollectOrNot(Integer noteId,Integer userId);
    /*
    queryCollectNoteByUserId方法：
    根据用户的id来查找collect表中对应的noteID即为用户collect了的笔记的id
     */
    List<Integer> queryCollectNoteByUserId(Integer userId);
}
