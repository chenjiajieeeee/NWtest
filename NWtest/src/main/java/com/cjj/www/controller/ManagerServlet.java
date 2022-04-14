package com.cjj.www.controller;

import com.cjj.www.pojo.Note;
import com.cjj.www.pojo.User;
import com.cjj.www.pojo.UserStatusAndUsername;
import com.cjj.www.service.*;
import com.cjj.www.util.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@WebServlet("/manager/*")
public class ManagerServlet extends BaseServlet{
         ManagerService managerService=new ManagerServiceImpl();
    public void chargeNote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        根据分区查找笔记，笔记分页，其实和首页一样，但是不能点进去查看详情。
         */
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String root =request.getParameter("root");
        PagingService pagingService=new PagingServiceImpl();
        HttpServletRequest req = pagingService.paging(request,root,response,1);
        req.setAttribute("root",root);
        req.setAttribute("username",username);
        req.setAttribute("password",password);
        req.getRequestDispatcher("/root/managerhome.jsp").forward(req,response);
    }
    public void reNote(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        Integer noteId = WebUtil.toInteger(request.getParameter("noteId"));
        NoteService noteService=new NoteServiceImpl();
        Note note = noteService.queryNoteByNoteId(noteId);
        String action = request.getParameter("action");
        if ("删除".equals(action)) {
            boolean check = managerService.deleteNoteByArea(noteId);
            if (check) {
                request.setAttribute("deleteNoteMsg", "删除标题为"+note.getTitle()+"的笔记成功！");
            } else {
                request.setAttribute("deleteMsg", "服务器出问题了！");
            }
            chargeNote(request, response);
        }else if("同意".equals(action)){
            String releaseStatus = request.getParameter("releaseStatus");
            boolean check = managerService.setNoteReleaseStatus(noteId, releaseStatus);
            if(check){
                request.setAttribute("agreeMsg","笔记已发布！");
            }else {
                request.setAttribute("agreeMsg","服务器出问题了！");
            }
            chargeNote(request,response);
        }
    }
    public void chargeUser(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        /*
        查询用户的姓名、id
        执行操作：
         */
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String root = request.getParameter("root");
        Map<Integer, String> userStatus = managerService.queryZoomStatus(root);
        List<UserStatusAndUsername> userStatusAndUsernames=new ArrayList<>();
        UserStatusAndUsername userStatusAndUsername=new UserStatusAndUsername();
        UserService userService=new UserServiceImpl();
        Set<Integer> keys = userStatus.keySet();
        for (Integer key:keys){
            User user = userService.queryUserByUserId(key);
            userStatusAndUsername.setUsername(user.getUsername());
            userStatusAndUsername.setUserId(user.getId());
            userStatusAndUsername.setUserStatus(userStatus.get(key));
            userStatusAndUsernames.add(userStatusAndUsername);
            userStatusAndUsername=new UserStatusAndUsername();
        }
        request.setAttribute("userMessage",userStatusAndUsernames);
        request.setAttribute("username",username);
        request.setAttribute("password",password);
        request.setAttribute("root",root);
        request.getRequestDispatcher("/root/chargeUser.jsp").forward(request,response);
    }
    public void setUserStatus(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        Integer userId = WebUtil.toInteger(request.getParameter("userId"));
        String userStatus = request.getParameter("userStatus");
        if(userStatus.equals("1")){
            userStatus="0";
        }else {
            userStatus="1";
        }
        String root = request.getParameter("root");
        boolean check = managerService.changeUserStatus(userId, userStatus, root);
        if(check){
            request.setAttribute("Msg","禁用/解封用户成功！");
        }else {
            request.setAttribute("Msg","服务器出问题了！");
        }
        chargeUser(request,response);
    }
}
