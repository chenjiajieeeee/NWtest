package com.cjj.www.controller;

import com.cjj.www.dao.UserDao;
import com.cjj.www.dao.UserDaoImpl;
import com.cjj.www.pojo.Note;
import com.cjj.www.pojo.Paging;
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

@WebServlet("/PageServlet")
public class PageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String action=req.getParameter("action");
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        req.setAttribute("username",username);
        req.setAttribute("password",password);
        NoteService noteService=new NoteServiceImpl();
        Paging paging=new Paging();
        String pageNo = req.getParameter("pageNo");
        if(pageNo==null){
            paging.setPageNo(1);
        }else {
            paging.setPageNo(WebUtil.toInteger(pageNo));
        }
        Integer begin= (paging.getPageNo()-1)* paging.getPageSize();
        List<Note> notes = noteService.queryNotePaging(begin, paging.getPageSize(),action);
        Integer record=noteService.queryNoteTotalPage(action);
        Integer pageTotal = record % paging.getPageSize()>0 ? record/4+1:record/4;
        if(pageTotal==0){
            pageTotal=1;
        }
        req.setAttribute("pageTotal",pageTotal);
        req.setAttribute("pageNo",paging.getPageNo());
        req.setAttribute("notes",notes);
        req.setAttribute("record",record);
        if("游戏区".equals(action)){
            req.getRequestDispatcher("/note/gamezoom.jsp").forward(req,resp);
        }
        else if("科技区".equals(action)){
            req.getRequestDispatcher("/note/it.jsp").forward(req,resp);
        }
        else if("学习区".equals(action)){
            req.getRequestDispatcher("/note/study.jsp").forward(req,resp);
        }
        else if("美食区".equals(action)){
            req.getRequestDispatcher("/note/food.jsp").forward(req,resp);
        }
        else if("动漫区".equals(action)){
            req.getRequestDispatcher("/note/cartoon.jsp").forward(req,resp);
        }
        else if("个人主页".equals(action)){
            List<Note> notes4 = noteService.queryNoteByUsername(username);
            req.setAttribute("notes",notes4);
            CommentService commentService=new CommentServiceImpl();
            User user=new User();
            UserDao userDao=new UserDaoImpl();
            user=userDao.queryUserByUserName(username);
            List<Note> notes1 = commentService.queryCommentNoteByUserId(user.getId());
            req.setAttribute("notes1",notes1);
            LikeActService likeActService=new LikeActServiceImpl();
            List<Note> notes2 = likeActService.queryLikeNoteByUserId(user.getId());
            req.setAttribute("notes2",notes2);
            CollectService collectService=new CollectServiceImpl();
            List<Note> notes3 = collectService.queryCollectNoteByUserId(user.getId());
            req.setAttribute("notes3",notes3);
            req.getRequestDispatcher("/User/page/home.jsp").forward(req,resp);
        }
        else if("首页".equals(action)){
            UserLoginServlet userLoginServlet=new UserLoginServlet();
            userLoginServlet.doPost(req,resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
