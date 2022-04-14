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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/note/*")
public class NoteServlet extends BaseServlet{
    NoteService noteService=new NoteServiceImpl();
    CommentService commentService=new CommentServiceImpl();
    LikeActService likeActService=new LikeActServiceImpl();
    CollectService collectService=new CollectServiceImpl();
    UserService userService=new UserServiceImpl();
    public void detail(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserDao userDao=new UserDaoImpl();
        User user = userDao.queryUserByUserName(username);
        request.setAttribute("root",user.getRoot());
        Integer noteId = WebUtil.toInteger(request.getParameter("noteId"));
        boolean check = likeActService.judgeLikeOrNot(noteId, user.getId());
        List<Comment> comments = commentService.queryCommentByNoteId(noteId);
        List<Tag> tags = noteService.queryTagByNoteId(noteId);
        request.setAttribute("comments",comments);
        Note note = noteService.queryNoteByNoteId(noteId);
        boolean result = collectService.judgeCollectOrNot(noteId, user.getId());
        request.setAttribute("result",result);
        request.setAttribute("username", username);
        request.setAttribute("password", password);
        request.setAttribute("note", note);
        request.setAttribute("tags",tags);
        request.setAttribute("check",check);
        request.getRequestDispatcher("/notebook/notedetail.jsp").forward(request, response);
    }
    public void likeAct(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
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
        request.getRequestDispatcher("/notebook/notedetail.jsp").forward(request,response);
    }
    public void publishComment(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");
        String password = request.getParameter("password");
        User user=new User();
        UserDao userDao=new UserDaoImpl();
        user=userDao.queryUserByUserName(username);
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
        request.setAttribute("password",password);
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
            request.getRequestDispatcher("/notebook/notedetail.jsp").forward(request,response);
    }
    public void collectAct(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Integer noteId = WebUtil.toInteger(request.getParameter("noteId"));
        /*
        同点赞的操作一样
         */
        List<Comment> comments = commentService.queryCommentByNoteId(noteId);
        User user=new User();
        UserDao userDao=new UserDaoImpl();
        user=userDao.queryUserByUserName(username);
        request.setAttribute("root",user.getRoot());
        NoteService noteService=new NoteServiceImpl();
        boolean result = collectService.collectAct(noteId, user.getId());
        Note note = noteService.queryNoteByNoteId(noteId);
        boolean check = likeActService.judgeLikeOrNot(noteId, user.getId());
        request.setAttribute("result",result);
        request.setAttribute("check",check);
        request.setAttribute("username",username);
        request.setAttribute("password",password);
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
        request.getRequestDispatcher("/notebook/notedetail.jsp").forward(request,response);

    }
}
