package com.cjj.www.controller;


import com.cjj.www.dao.UserRoleDao;
import com.cjj.www.dao.UserRoleDaoImpl;
import com.cjj.www.pojo.Note;
import com.cjj.www.service.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/user/*")
public class UserServlet extends BaseServlet{
    private final UserService userService=new UserServiceImpl();
    NoteService noteService=new NoteServiceImpl();

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        userService.userLogin(request,response);
    }
    public void register(HttpServletRequest request,HttpServletResponse response){
          userService.userRegister(request, response);
    }
    public void updateUserInformation(HttpServletRequest request,HttpServletResponse response){
        UserRoleService roleService=new UserRoleServiceImpl();
        roleService.updateUserInformation(request,response);
    }
    public void updateNote(HttpServletRequest request,HttpServletResponse response){
        NoteService noteService=new NoteServiceImpl();
        noteService.updateNote(request,response);
    }
    public void publishNote(HttpServletRequest request,HttpServletResponse response){
        noteService.publishNote(request,response);
    }
    public void fileUpLoad(HttpServletRequest request,HttpServletResponse response){
       noteService.uploadFile(request,response);
    }
    public void addFriend(HttpServletRequest request,HttpServletResponse response){
        userService.addFriend(request,response);
    }
    public void chat(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        UserRoleService userRoleService=new UserRoleServiceImpl();
        userRoleService.chat(request,response);
    }
    public void confirm(HttpServletRequest request,HttpServletResponse response){
        userService.confirm(request,response);
    }
    public void loginpage(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        PagingService pagingService=new PagingServiceImpl();
        pagingService.loginPage(request,response);
    }
    public void registerCheck(HttpServletRequest request,HttpServletResponse response) {
         userService.checkUsername(request,response);
    }
    public void viewReport(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String root = request.getParameter("root");
        UserRoleDao userRoleDao=new UserRoleDaoImpl();
        List<Integer> notesId = userRoleDao.queryReportNoteByUsername(username);
        List<Note> notes=new ArrayList<>();
        for (Integer id:notesId){
            notes.add(noteService.queryNoteByNoteId(id));
        }
        request.setAttribute("username",username);
        request.setAttribute("root",root);
        request.setAttribute("notes",notes);
        request.getRequestDispatcher("/User/page/viewReport.jsp").forward(request,response);
    }
}
