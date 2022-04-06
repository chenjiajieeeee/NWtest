package com.cjj.www.service;

import com.cjj.www.dao.CommentDao;
import com.cjj.www.dao.CommentDaoImpl;
import com.cjj.www.dao.NoteDao;
import com.cjj.www.dao.NoteDaoImpl;
import com.cjj.www.pojo.Comment;
import com.cjj.www.pojo.Note;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CommentServiceImpl implements CommentService{
    @Override
    public boolean publishComment(Comment comment) {
        CommentDao commentDao=new CommentDaoImpl();
        if(comment.getMain().equals("")){
            return false;
        }
        else {
            return commentDao.saveComment(comment);
        }
    }

    @Override
    public List<Comment> queryCommentByNoteId(Integer noteId) {
        CommentDao commentDao=new CommentDaoImpl();
        return commentDao.queryCommentByNoteId(noteId);
    }

    @Override
    public List<Comment> queryCommentByUserId(Integer userId) {
        CommentDao commentDao=new CommentDaoImpl();
        return commentDao.queryCommentByUserId(userId);
    }

    @Override
    public List<Note> queryCommentNoteByUserId(Integer userId) {
        CommentDao commentDao=new CommentDaoImpl();
        NoteDao noteDao=new NoteDaoImpl();
        Set<Integer> noteIds = commentDao.queryCommentUser(userId);
        List<Note> notes=new ArrayList<>();
        Note note=new Note();
        for (Integer noteId:noteIds){
             note=noteDao.queryNoteByNoteId(noteId);
             notes.add(note);
             note=new Note();
        }
        return notes;
    }
}
