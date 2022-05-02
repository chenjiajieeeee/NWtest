package com.cjj.www.service;

import com.cjj.www.dao.*;
import com.cjj.www.pojo.Comment;
import com.cjj.www.pojo.Note;
import com.cjj.www.pojo.Tag;
import com.cjj.www.pojo.User;
import com.cjj.www.util.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CommentServiceImpl implements CommentService{
    @Override
    public void publishComment(HttpServletRequest request, HttpServletResponse response) {
        LikeActService likeActService=new LikeActServiceImpl();
        CollectService collectService=new CollectServiceImpl();
        CommentService commentService=new CommentServiceImpl();
        String username=request.getParameter("username");

        UserDao userDao=new UserDaoImpl();
        User user=userDao.queryUserByUserName(username);
        request.setAttribute("root",user.getRoot());
        Integer noteId = WebUtil.toInteger(request.getParameter("noteId"));
        boolean check = likeActService.judgeLikeOrNot(noteId, user.getId());
        String commentMain=request.getParameter("commentMain");
        Comment comment=new Comment();
        comment.setMain(commentMain);
        comment.setUserId(user.getId());
        comment.setNoteId(noteId);
        boolean result = collectService.judgeCollectOrNot(noteId, user.getId());
        request.setAttribute("result",result);
        request.setAttribute("username",username);

        request.setAttribute("check",check);
        NoteService noteService=new NoteServiceImpl();
        Note note = noteService.queryNoteByNoteId(noteId);
        request.setAttribute("note",note);
        List<Tag> tags = noteService.queryTagByNoteId(noteId);
        request.setAttribute("tags",tags);
        boolean result1 = commentService.publishComment(comment);
        List<Comment> comments = commentService.queryCommentByNoteId(noteId);
        request.setAttribute("comments",comments);
        if(result1){
            request.setAttribute("publishMsg","发布评论成功！");
        }else {
            request.setAttribute("publishMsg","评论不能为空！");
        }
        try {
            request.getRequestDispatcher("/notebook/notedetail.jsp").forward(request,response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

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
