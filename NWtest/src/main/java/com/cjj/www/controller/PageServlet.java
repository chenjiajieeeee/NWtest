package com.cjj.www.controller;

import com.cjj.www.service.NoteService;
import com.cjj.www.service.NoteServiceImpl;
import com.cjj.www.service.PagingService;
import com.cjj.www.service.PagingServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/page/*")
public class PageServlet extends BaseServlet {
        private final NoteService noteService=new NoteServiceImpl();
        PagingService pagingService=new PagingServiceImpl();
        public void findPage(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
            pagingService.findPage(request,response);
        }
        public void sort(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
            HttpServletRequest req = noteService.sort(request);
            req.getRequestDispatcher("/notebook/sort.jsp").forward(request,response);
        }
}
