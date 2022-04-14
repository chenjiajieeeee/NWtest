package com.cjj.www.dao;

import com.cjj.www.pojo.Comment;
import com.cjj.www.pojo.Like;

import java.util.List;
import java.util.Set;

public interface CommentDao {
    /*
    用户发表评论成功后将评论保存进数据库中
     */
    boolean saveComment(Comment comment);

    /*
    打开页面时查找该笔记对应的评论显示
    某个笔记时，要通过查询该笔记的id号
     */
    List<Comment> queryCommentByNoteId(Integer noteId);
    List<Comment> queryCommentByUserId(Integer userId);
    /*
    考核没要求删评论我就不写了，开摆！
     */
    /*
    拓展功能：
    进入个人主页之后，需要查询该用户发表了评论的笔记
    思路：表中每个评论都有一个noteid字段和userid字段
    先把评论中userid是该用户的评论字段找出来以及评论的noteid找出来，用list集合来接收
    如果用户重复评论了同一个笔记，那么可以不能接收该笔记，可以用continue跳过
    接收之后，根据noteid查找该笔记的内容
    因为Like刚刚好有这两个量，所以就浅浅的调用一下
     */
    Set<Integer> queryCommentUser(Integer userId);
}
