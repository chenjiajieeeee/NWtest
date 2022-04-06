package com.cjj.www.controller;

import com.cjj.www.pojo.Note;
import com.cjj.www.pojo.Paging;
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

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        String tag=req.getParameter("tag");
        NoteService noteService=new NoteServiceImpl();
        Paging paging=new Paging();
        String pageNo = req.getParameter("pageNo");
        if(pageNo==null){
            paging.setPageNo(1);
        }else {
            paging.setPageNo(WebUtil.toInteger(pageNo));
        }
        Integer begin= (paging.getPageNo()-1)* paging.getPageSize();
        List<Note> notes = noteService.queryNoteByTag(tag,begin);
        Integer record = noteService.queryNoteTotalPageSearchByTag(tag);
        Integer pageTotal = record % paging.getPageSize()>0 ? record/4+1:record/4;
        if(pageTotal==0){
            pageTotal=1;
        }
        req.setAttribute("tag",tag);
        req.setAttribute("notes",notes);
        req.setAttribute("username",username);
        req.setAttribute("password",password);
        req.setAttribute("pageNo",paging.getPageNo());
        req.setAttribute("pageTotal",pageTotal);
        req.setAttribute("record",record);
        req.getRequestDispatcher("/note/search.jsp").forward(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
