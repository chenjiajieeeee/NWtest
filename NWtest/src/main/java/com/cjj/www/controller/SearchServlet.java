package com.cjj.www.controller;

import com.cjj.www.pojo.User;
import com.cjj.www.service.PagingService;
import com.cjj.www.service.PagingServiceImpl;
import com.cjj.www.service.UserService;
import com.cjj.www.service.UserServiceImpl;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/search/result")
public class SearchServlet extends BaseServlet {
    public void result(HttpServletRequest request,HttpServletResponse response){
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String tag=request.getParameter("tag");
        UserService userService=new UserServiceImpl();
        User user = userService.queryUserByUserName(username);
        request.setAttribute("root",user.getRoot());
        PagingService pagingService=new PagingServiceImpl();
        HttpServletRequest req = pagingService.paging(request, tag, response);
        req.setAttribute("username",username);
        req.setAttribute("password",password);
        req.setAttribute("tag",tag);
        try {
            req.getRequestDispatcher("/notebook/search.jsp").forward(request,response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

}
