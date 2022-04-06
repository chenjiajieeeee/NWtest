package com.cjj.www.controller;

import com.cjj.www.service.UserService;
import com.cjj.www.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/UserRegister")
public class UserRegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        //获取请求参数并将其转换为字符串,去掉空格
        String username = req.getParameter("username");
        username=username.replaceAll(" ", "");
        String password = req.getParameter("password");
        password=password.replaceAll(" ", "");
        //调用业务层对应的方法
        UserService userService=new UserServiceImpl();
        boolean result=userService.userRegister(username,password);
        //处理结果跳转相应页面
        if(result){
                req.setAttribute("msg","The user name already exists!");
                req.getRequestDispatcher("/User/page/register.jsp").forward(req,resp);
        }
        else {
                resp.sendRedirect("http://localhost:8080/nw/User/page/login.jsp");
        }


    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
