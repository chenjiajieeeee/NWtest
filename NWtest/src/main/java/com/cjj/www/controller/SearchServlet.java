package com.cjj.www.controller;


import com.cjj.www.service.PagingService;
import com.cjj.www.service.PagingServiceImpl;




import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/search/result")
public class SearchServlet extends BaseServlet {
    public void result(HttpServletRequest request,HttpServletResponse response) {
        PagingService pagingService=new PagingServiceImpl();
        pagingService.searchResult(request,response);
    }

}
