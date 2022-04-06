package com.cjj.www.controller;

import com.cjj.www.pojo.Comment;
import com.cjj.www.pojo.Note;

import com.cjj.www.pojo.Tag;
import com.cjj.www.service.CommentService;
import com.cjj.www.service.CommentServiceImpl;
import com.cjj.www.service.NoteService;
import com.cjj.www.service.NoteServiceImpl;
import com.cjj.www.util.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/NoteDetailServlet")
public class NoteDetailServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String username = req.getParameter("username");
            String password = req.getParameter("password");
        Integer noteId = WebUtil.toInteger(req.getParameter("noteId"));
        CommentService commentService = new CommentServiceImpl();
            List<Comment> comments = commentService.queryCommentByNoteId(noteId);
            NoteService noteService = new NoteServiceImpl();
        /*
        通过noteId把笔记信息查找出来
         */
        /*
        拓展功能：查找标签
         */
            List<Tag> tags = noteService.queryTagByNoteId(noteId);
            req.setAttribute("comments",comments);
            Note note = noteService.queryNoteByNoteId(noteId);
            req.setAttribute("username", username);
            req.setAttribute("password", password);
            req.setAttribute("note", note);
            req.setAttribute("tags",tags);
            req.getRequestDispatcher("/note/notedetail.jsp").forward(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
