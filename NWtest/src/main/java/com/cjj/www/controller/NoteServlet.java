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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
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
        /*
        历史记录：将查看的笔记id以及userid送过去，userid用于辨别这个笔记是谁查看过的
         */
        Cookie[] cookies = request.getCookies();
        Cookie cookieByName = WebUtil.findCookieByName(cookies, user.getId().toString());
        if(cookieByName==null){
            Cookie cookie=new Cookie(user.getId().toString(),request.getParameter("noteId"));
            response.addCookie(cookie);
        }else {
            String noteId1 = request.getParameter("noteId");
            //获取当前记录
            String Ids=cookieByName.getValue();
            //去除分隔符-
            String[] split = Ids.split("-");
            //将其转化为数组进行操作
            LinkedList<String> list=new LinkedList<>(Arrays.asList(split));
            //如果新增笔记已经在别的地方已经浏览过了
            if(list.contains(noteId1)){
                //将原纪录去除，并将新纪录添加到第一个
                list.remove(noteId1);
            }
            list.addFirst(noteId1);
            //将数组取出加上分隔符保存进cookie
            StringBuilder stringBuilder=new StringBuilder();
            for (String string:list){
                stringBuilder.append(string).append("-");
            }
            String newIds = stringBuilder.substring(0,stringBuilder.length()-1);
            Cookie cookie=new Cookie(user.getId().toString(),newIds);
            response.addCookie(cookie);
        }
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
    public void history(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        String root = request.getParameter("root");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userService.queryUserByUserName(username);
        Cookie[] cookies = request.getCookies();
        Cookie cookieByName = WebUtil.findCookieByName(cookies, user.getId().toString());
        List<Note> notes=new ArrayList<>();
        if(cookieByName==null){
            request.setAttribute("historyMsg","历史记录为空！");
        }else {
            String value = cookieByName.getValue();
            String[] split = value.split("-");
            List<Integer> ids=new ArrayList<>();
            for (String id: split) {
                ids.add(WebUtil.toInteger(id));
            }
            for (Integer id:ids){
                Note note = noteService.queryNoteByNoteId(id);
                if(note!=null){
                    notes.add(note);
                }
            }
        }
        request.setAttribute("username",username);
        request.setAttribute("password",password);
        request.setAttribute("notes",notes);
        request.setAttribute("root",root);
        request.getRequestDispatcher("/notebook/history.jsp").forward(request,response);
    }
}
