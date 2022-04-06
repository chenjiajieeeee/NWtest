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

@WebServlet("/CollectServlet")
public class CollectServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Integer noteId = WebUtil.toInteger(req.getParameter("noteId"));
        /*
        同点赞的操作一样
         */
        CommentService commentService=new CommentServiceImpl();
        List<Comment> comments = commentService.queryCommentByNoteId(noteId);
        User user=new User();
        UserDao userDao=new UserDaoImpl();
        user=userDao.queryUserByUserName(username);
        NoteService noteService=new NoteServiceImpl();
        CollectService collectService=new CollectServiceImpl();
        boolean result = collectService.collectAct(noteId, user.getId());
        Note note = noteService.queryNoteByNoteId(noteId);
        req.setAttribute("username",username);
        req.setAttribute("password",password);
        req.setAttribute("note",note);
        req.setAttribute("comments",comments);
        List<Tag> tags = noteService.queryTagByNoteId(noteId);
        req.setAttribute("tags",tags);
        if(result){
            req.setAttribute("CollectMsg","收藏成功！");
        }
        else {
            req.setAttribute("likeMsg","取消收藏成功！");
        }
        req.getRequestDispatcher("/note/notedetail.jsp").forward(req,resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
