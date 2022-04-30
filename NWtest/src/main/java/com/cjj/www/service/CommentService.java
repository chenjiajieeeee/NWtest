package com.cjj.www.service;

import com.cjj.www.pojo.Comment;
import com.cjj.www.pojo.Note;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

public interface CommentService {
    /*
    对应Dao层的业务层
     */
    void publishComment(HttpServletRequest request, HttpServletResponse response);
    boolean publishComment(Comment comment);
    List<Comment> queryCommentByNoteId(Integer noteId);

    List<Note> queryCommentNoteByUserId(Integer userId);
}
