package com.cjj.www.controller;

import com.cjj.www.pojo.Note;
import com.cjj.www.pojo.Paging;
import com.cjj.www.service.NoteService;
import com.cjj.www.service.NoteServiceImpl;
import com.cjj.www.service.UserService;
import com.cjj.www.service.UserServiceImpl;
import com.cjj.www.util.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        UserService userService=new UserServiceImpl();
        boolean check = userService.userLogin(username, password);
        if (check){
            /*
              登录成功后在首页显示所有的笔记
              把全部图书保存在request域中
              将请求转发到首页所在的页面中，遍历显示所有的笔记！
              使用jstl来写会更加的简洁
             */
            NoteService noteService=new NoteServiceImpl();
            req.setAttribute("username",username);
            req.setAttribute("password",password);
            String pageNo = req.getParameter("pageNo");
            Paging paging=new Paging();
            if(pageNo==null){
                paging.setPageNo(1);
            }
            else {
                paging.setPageNo(WebUtil.toInteger(pageNo));
            }
            Integer begin=(paging.getPageNo()-1)*paging.getPageSize();
            List<Note> notes = noteService.queryNotePaging(begin, paging.getPageSize());
            Integer record=noteService.queryNoteTotalPage();
            Integer pageTotal = record % paging.getPageSize()>0 ? record/4+1:record/4;
            if(pageTotal==0){
                pageTotal=1;
            }
            req.setAttribute("record",record);
            req.setAttribute("pageTotal",pageTotal);
            req.setAttribute("note",notes);
            req.setAttribute("pageNo",paging.getPageNo());
            req.getRequestDispatcher("/note/homepage.jsp").forward(req,resp);
        }
        else {
            req.setAttribute("msg","Wrong username or password!");
            req.setAttribute("username",username);
            //请求转发
            req.getRequestDispatcher("/User/page/login.jsp").forward(req,resp);
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
