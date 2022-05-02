package com.cjj.www.dao;

import com.cjj.www.pojo.Comment;

import java.util.List;
import java.util.Set;

public interface CommentDao {
    /**
     *
     * @param comment 保存的评论对象
     * @return ture为保存成功 false为保存失败
     */
    boolean saveComment(Comment comment);

    /**
     *
     * @param noteId 查询评论的笔记的id
     * @return list集合 为该笔记评论对象的集合
     */
    List<Comment> queryCommentByNoteId(Integer noteId);

    /**
     *
     * @param userId 进行查询操作
     * @return set集合，表示评论过的笔记的id，不重复
     */
    Set<Integer> queryCommentUser(Integer userId);
}
