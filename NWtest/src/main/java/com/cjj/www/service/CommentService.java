package com.cjj.www.service;

import com.cjj.www.pojo.Comment;
import com.cjj.www.pojo.Note;

import java.util.List;
import java.util.Set;

public interface CommentService {
    /*
    对应Dao层的业务层
     */
    boolean publishComment(Comment comment);
    List<Comment> queryCommentByNoteId(Integer noteId);
    List<Comment> queryCommentByUserId(Integer userId);
    List<Note> queryCommentNoteByUserId(Integer userId);
}
