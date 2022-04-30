package com.cjj.www.service;

import com.cjj.www.dao.*;
import com.cjj.www.pojo.*;
import com.cjj.www.util.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LikeActServiceImpl implements LikeActService{

    @Override
    public void likeAct(HttpServletRequest request, HttpServletResponse response) {
        LikeActService likeActService=new LikeActServiceImpl();
        NoteService noteService=new NoteServiceImpl();
        CollectService collectService=new CollectServiceImpl();
        CommentService commentService=new CommentServiceImpl();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Integer noteId = WebUtil.toInteger(request.getParameter("noteId"));
        UserDao userDao=new UserDaoImpl();
        User user = userDao.queryUserByUserName(username);
        request.setAttribute("root",user.getRoot());
        boolean check = likeActService.LikeAct(noteId, user.getId());
        if(!check){
            request.setAttribute("likeMsg","点赞成功！");
        }
        else {
            request.setAttribute("likeMsg","取消点赞成功！");
        }
        List<Tag> tags = noteService.queryTagByNoteId(noteId);
        boolean result = collectService.judgeCollectOrNot(noteId, user.getId());
        request.setAttribute("result",result);
        request.setAttribute("note",noteService.queryNoteByNoteId(noteId));
        request.setAttribute("tags",tags);
        request.setAttribute("username",username);
        request.setAttribute("password",password);
        request.setAttribute("check",check);
        List<Comment> comments = commentService.queryCommentByNoteId(noteId);
        request.setAttribute("comments",comments);
        try {
            request.getRequestDispatcher("/notebook/notedetail.jsp").forward(request,response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean judgeLikeOrNot(Integer noteId, Integer userId) {
        LikeActDao likeActDao=new LikeActDaoImpl();
        return likeActDao.judgeLikeOrNot(noteId, userId);
    }

    @Override
    public boolean LikeAct(Integer noteId, Integer userId) {
        LikeActDao likeActDao=new LikeActDaoImpl();
        boolean check = likeActDao.judgeLikeOrNot(noteId, userId);
        if(check){
            Like like=new Like();
            like.setNoteId(noteId);
            like.setUserId(userId);
            likeActDao.addLike(like);
            return false;
        }else {
            likeActDao.cancelLike(noteId,userId);
            return true;
        }
    }

    @Override
    public List<Note> queryLikeNoteByUserId(Integer userId) {
        LikeActDao likeActDao=new LikeActDaoImpl();
        List<Integer> likeNoteIds = likeActDao.queryLikeNoteByUserId(userId);
        List<Note> notes=new ArrayList<>();
        Note note=new Note();
        NoteDao noteDao=new  NoteDaoImpl();
        for (Integer likeNoteId:likeNoteIds){
            note=noteDao.queryNoteByNoteId(likeNoteId);
            notes.add(note);
            note=new Note();
        }
        return notes;
    }
}
