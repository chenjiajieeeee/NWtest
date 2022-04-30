package com.cjj.www.dao;

import com.cjj.www.pojo.Comment;
import com.cjj.www.pojo.Like;

import java.util.List;
import java.util.Set;

public interface CommentDao {
    /*
    saveComment方法：
    用户发表评论成功后将评论保存进数据库中
    comment对象包括
    评论内容、评论者id 、评论的笔记id
     */
    boolean saveComment(Comment comment);

    /*
    queryCommentByNoteId && queryCommentByUserId
    打开页面时查找该笔记对应的评论显示
    第二个方法用于在个人主页查询已经评论的笔记
     */
    List<Comment> queryCommentByNoteId(Integer noteId);
    List<Comment> queryCommentByUserId(Integer userId);
    /*
    拓展功能：
    进入个人主页之后，需要查询该用户发表了评论的笔记
    思路：表中每个评论都有一个noteid字段和userid字段
    先把评论中userid是该用户的评论字段找出来以及评论的noteid找出来，用list集合来接收
    如果用户重复评论了同一个笔记，那么可以不能接收该笔记，可以用continue跳过
    接收之后，根据noteid查找该笔记的内容
     */
    Set<Integer> queryCommentUser(Integer userId);
}
