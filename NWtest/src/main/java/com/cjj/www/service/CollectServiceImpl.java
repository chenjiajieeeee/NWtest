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

public class CollectServiceImpl implements CollectService{

    @Override
    public boolean judgeCollectOrNot(Integer noteId, Integer userId) {
        CollectDao collectDao=new CollectDaoImpl();
        return collectDao.judgeCollectOrNot(noteId,userId);
    }

    @Override
    public void collectAct(HttpServletRequest request, HttpServletResponse response) {
        CommentService commentService=new CommentServiceImpl();
        CollectService collectService=new CollectServiceImpl();
        LikeActService likeActService=new LikeActServiceImpl();
        String username = request.getParameter("username");

        Integer noteId = WebUtil.toInteger(request.getParameter("noteId"));
        /*
        同点赞的操作一样
         */
        List<Comment> comments = commentService.queryCommentByNoteId(noteId);
        UserDao userDao=new UserDaoImpl();
        User user=userDao.queryUserByUserName(username);
        request.setAttribute("root",user.getRoot());
        NoteService noteService=new NoteServiceImpl();
        boolean result = collectService.collectAct(noteId, user.getId());
        Note note = noteService.queryNoteByNoteId(noteId);
        boolean check = likeActService.judgeLikeOrNot(noteId, user.getId());
        request.setAttribute("result",result);
        request.setAttribute("check",check);
        request.setAttribute("username",username);

        request.setAttribute("note",note);
        request.setAttribute("comments",comments);
        List<Tag> tags = noteService.queryTagByNoteId(noteId);
        request.setAttribute("tags",tags);
        if(!result){
            request.setAttribute("CollectMsg","收藏成功！");
        }
        else {
            request.setAttribute("CollectMsg","取消收藏成功！");
        }
        try {
            request.getRequestDispatcher("/notebook/notedetail.jsp").forward(request,response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean collectAct(Integer noteId, Integer userId) {
        CollectDao collectDao=new CollectDaoImpl();
        boolean check = collectDao.judgeCollectOrNot(noteId, userId);
        if(check){
            Collect collect=new Collect();
            collect.setNoteId(noteId);
            collect.setUserId(userId);
            collectDao.addCollect(collect);
            return false;
        }else {
            collectDao.cancelCollect(noteId,userId);
            return true;
        }
    }

    @Override
    public List<Note> queryCollectNoteByUserId(Integer userId) {
        CollectDao collectDao=new CollectDaoImpl();
        List<Integer> collectNoteIds = collectDao.queryCollectNoteByUserId(userId);
        List<Note> notes=new ArrayList<>();
        Note note=new Note();
        NoteDao noteDao=new  NoteDaoImpl();
        for (Integer collectNoteId:collectNoteIds){
            note=noteDao.queryNoteByNoteId(collectNoteId);
            notes.add(note);
            note=new Note();
        }
        return notes;
    }
}
