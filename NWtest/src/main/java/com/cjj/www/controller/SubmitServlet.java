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

@WebServlet("/SubmitServlet")
public class SubmitServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String username=req.getParameter("username");
        User user=new User();
        UserDao userDao=new UserDaoImpl();
        user=userDao.queryUserByUserName(username);
        String password=req.getParameter("password");
        Integer noteId = WebUtil.toInteger(req.getParameter("noteId"));
        String action=req.getParameter("action");
        String commentMain=req.getParameter("commentMain");
        Comment comment=new Comment();
        comment.setMain(commentMain);
        comment.setUserId(user.getId());
        comment.setNoteId(noteId);
        req.setAttribute("username",username);
        req.setAttribute("password",password);
        NoteService noteService=new NoteServiceImpl();
        Note note = noteService.queryNoteByNoteId(noteId);
        req.setAttribute("note",note);
        List<Tag> tags = noteService.queryTagByNoteId(noteId);
        req.setAttribute("tags",tags);
        if("评论".equals(action)){
            CommentService commentService=new CommentServiceImpl();
            boolean check = commentService.publishComment(comment);
            List<Comment> comments = commentService.queryCommentByNoteId(noteId);
            req.setAttribute("comments",comments);
            if(check){
                req.setAttribute("publishMsg","发布评论成功！");
            }else {
                req.setAttribute("publishMsg","评论不能为空！");
            }
            req.getRequestDispatcher("/note/notedetail.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
