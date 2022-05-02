package com.cjj.www.service;

import com.cjj.www.pojo.Comment;
import com.cjj.www.pojo.Note;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface CommentService {
    /**
     *
     * @param request 对应servlet的req
     * @param response 对应servlet的resp
     * 用于将servlet转移到service中
     */
    void publishComment(HttpServletRequest request, HttpServletResponse response);

    /**
     *
     * @param comment 封装好的评论对象，将其插入到数据库中
     * @return ture为成功，false为失败
     */
    boolean publishComment(Comment comment);

    /**
     *
     * @param noteId 通过该笔记的id查询笔记下的所有评论
     * @return list集合，每一个都是该笔记的评论对象
     */
    List<Comment> queryCommentByNoteId(Integer noteId);

    /**
     *
     * @param userId 通过用户的id查询他评论过的笔记
     * @return list集合，每一个都是笔记对象
     */
    List<Note> queryCommentNoteByUserId(Integer userId);
}
