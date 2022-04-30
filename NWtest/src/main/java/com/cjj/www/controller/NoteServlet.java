package com.cjj.www.controller;


import com.cjj.www.service.*;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@WebServlet("/note/*")
public class NoteServlet extends BaseServlet{
    NoteService noteService=new NoteServiceImpl();
    CommentService commentService=new CommentServiceImpl();
    LikeActService likeActService=new LikeActServiceImpl();
    CollectService collectService=new CollectServiceImpl();
    UserService userService=new UserServiceImpl();
    public void detail(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
       noteService.detail(request,response);
    }
    public void likeAct(HttpServletRequest request,HttpServletResponse response){
        likeActService.likeAct(request,response);
    }
    public void publishComment(HttpServletRequest request,HttpServletResponse response){
        commentService.publishComment(request,response);
    }
    public void collectAct(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        collectService.collectAct(request,response);
    }
    public void history(HttpServletRequest request,HttpServletResponse response)  {
        userService.history(request,response);
    }
    public void report(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        UserRoleService userRoleService=new UserRoleServiceImpl();
        userRoleService.report(request,response);
    }
}
