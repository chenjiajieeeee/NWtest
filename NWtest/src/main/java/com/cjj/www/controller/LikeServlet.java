package com.cjj.www.controller;

import com.cjj.www.dao.UserDao;
import com.cjj.www.dao.UserDaoImpl;
import com.cjj.www.pojo.Comment;
import com.cjj.www.pojo.Note;
import com.cjj.www.pojo.Tag;
import com.cjj.www.pojo.User;
import com.cjj.www.service.*;
import com.cjj.www.util.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/LikeServlet")
public class LikeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Integer noteId = WebUtil.toInteger(req.getParameter("noteId"));
        /*
        找出笔记，喜欢成功后返回，还要判断是不是已经点过赞了
         */
        CommentService commentService=new CommentServiceImpl();
        List<Comment> comments = commentService.queryCommentByNoteId(noteId);
        User user=new User();
        UserDao userDao=new UserDaoImpl();
        user=userDao.queryUserByUserName(username);
        NoteService noteService=new NoteServiceImpl();
        LikeActService likeActService=new LikeActServiceImpl();
        boolean result = likeActService.LikeAct(noteId, user.getId());
        Note note = noteService.queryNoteByNoteId(noteId);
        req.setAttribute("username",username);
        req.setAttribute("password",password);
        req.setAttribute("note",note);
        req.setAttribute("comments",comments);
        if(result){
            req.setAttribute("likeMsg","点赞成功！");
        }
        else {
            req.setAttribute("likeMsg","取消点赞成功！");
        }
        List<Tag> tags = noteService.queryTagByNoteId(noteId);
        req.setAttribute("tags",tags);
        req.getRequestDispatcher("/note/notedetail.jsp").forward(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
