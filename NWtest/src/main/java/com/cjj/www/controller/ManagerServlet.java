package com.cjj.www.controller;

import com.cjj.www.dao.NoteDao;
import com.cjj.www.dao.NoteDaoImpl;
import com.cjj.www.pojo.Note;
import com.cjj.www.service.ManagerService;
import com.cjj.www.service.ManagerServiceImpl;
import com.cjj.www.util.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/ManagerServlet")
public class ManagerServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String action=req.getParameter("action");
        String account=req.getParameter("account");
        String password=req.getParameter("password");
        req.setAttribute("account",account);
        req.setAttribute("password",password);
        if("登录".equals(action)){
            ManagerService managerService=new ManagerServiceImpl();
            boolean check = managerService.login(account, password);
            if(check){
                String zoomName= managerService.searchZoom(account);
                req.setAttribute("zoomName",zoomName);
                List<Note> notes = managerService.queryNoteByArea(zoomName);
                req.setAttribute("notes",notes);
                Map<Integer, String> allUserZoomStatus = managerService.queryZoomStatus(zoomName);
                req.setAttribute("allUserZoomStatus",allUserZoomStatus);
                req.getRequestDispatcher("/manager/managerhome.jsp").forward(req,resp);
                /*
                  搜索该区域的所有笔记
                 */
                /*
                  将所有用户的信息以及是否在这个区被禁的信息也发过去

                 */
            }
            else {
                req.setAttribute("errMsg","账号或密码错误");
                req.getRequestDispatcher("/manager/manager.jsp").forward(req,resp);
            }
        }
        else {
            if("删除笔记".equals(action)){
                /*
                一样也要转笔记id为整形
                 */
                Integer noteId = WebUtil.toInteger(req.getParameter("noteId"));
                String zoomName = req.getParameter("zoomName");
                req.getParameter("releaseStatus");
                ManagerService managerService=new ManagerServiceImpl();
                boolean check = managerService.deleteNoteByArea(noteId);
                List<Note> notes = managerService.queryNoteByArea(zoomName);
                req.setAttribute("zoomName",zoomName);
                req.setAttribute("notes",notes);
                Map<Integer, String> allUserZoomStatus = managerService.queryZoomStatus(zoomName);
                req.setAttribute("allUserZoomStatus",allUserZoomStatus);
                if(check){
                    req.setAttribute("deleteMsg","删除笔记成功！");
                }else {
                    req.setAttribute("deleteMsg","由于不可抗力删除失败！");
                }
                req.getRequestDispatcher("/manager/managerhome.jsp").forward(req,resp);
            }else if("同意".equals(action)){
                /*
                同意之后设置releaseStatus为1同时提示设置成功，如果已经为1就返回已经同意该笔记发布了！
                 */
                Integer noteId=WebUtil.toInteger(req.getParameter("noteId"));
                String ReleaseStatus=req.getParameter("releaseStatus");
                String zoomName = req.getParameter("zoomName");
                ManagerService managerService=new ManagerServiceImpl();
                List<Note> notes = managerService.queryNoteByArea(zoomName);
                req.setAttribute("zoomName",zoomName);
                req.setAttribute("notes",notes);
                Map<Integer, String> allUserZoomStatus = managerService.queryZoomStatus(zoomName);
                req.setAttribute("allUserZoomStatus",allUserZoomStatus);
                boolean check = managerService.setNoteReleaseStatus(noteId, ReleaseStatus);
                if(check){
                    req.setAttribute("checkMsg","设置笔记发布状态成功！");
                }
                else {
                    req.setAttribute("checkMsg","已经同意笔记发布了！");
                }
                req.getRequestDispatcher("/manager/managerhome.jsp").forward(req,resp);
            }else if("禁用/解封该用户".equals(action)){
                String zoomName = req.getParameter("zoomName");
                String userStatus=req.getParameter("userStatus");
                if(userStatus.equals("1")){
                    userStatus="0";
                }
                else {
                    userStatus="1";
                }
                System.out.println(userStatus);
                ManagerService managerService=new ManagerServiceImpl();
                Integer userId = WebUtil.toInteger(req.getParameter("userId"));
                List<Note> notes = managerService.queryNoteByArea(zoomName);
                req.setAttribute("zoomName",zoomName);
                req.setAttribute("notes",notes);
                boolean check = managerService.changeUserStatus(userId, userStatus, zoomName);
                Map<Integer, String> allUserZoomStatus = managerService.queryZoomStatus(zoomName);
                req.setAttribute("allUserZoomStatus",allUserZoomStatus);
                if(check){
                    req.setAttribute("forbidMsg","禁用/解封该用户成功！");
                }else {
                    req.setAttribute("forbidMsg","啊哦，系统出现问题了");
                }
                req.getRequestDispatcher("/manager/managerhome.jsp").forward(req,resp);
            }else if("总管理员登录".equals(action)){
                if(account.equals("root")&&password.equals("admin")){
                    NoteDao noteDao=new NoteDaoImpl();
                    List<Note> notes1 = noteDao.queryNote();
                    req.setAttribute("notes",notes1);
                    /*
                      搜索该区域的所有笔记
                     */
                    /*
                      将所有用户的信息以及是否在这个区被禁的信息也发过去
                     */
                    req.getRequestDispatcher("/manager/totalDelete.jsp").forward(req,resp);
                }
                else {
                    req.setAttribute("errMsg","账号或密码错误");
                    req.getRequestDispatcher("/manager/totalAdministrator.jsp").forward(req,resp);
                }
            }else if("删除".equals(action)){
                Integer noteId = WebUtil.toInteger(req.getParameter("noteId"));
                ManagerService managerService=new ManagerServiceImpl();
                boolean check = managerService.deleteNoteByArea(noteId);
                NoteDao noteDao=new NoteDaoImpl();
                if(check){
                    req.setAttribute("deleteMsg","删除成功！");
                    List<Note> notes1 = noteDao.queryNote();
                    req.setAttribute("notes",notes1);
                }else {
                    List<Note> notes1 = noteDao.queryNote();
                    req.setAttribute("notes",notes1);
                    req.setAttribute("deleteMsg","啊哦！系统出错了！");
                }
                req.getRequestDispatcher("/manager/totalDelete.jsp").forward(req,resp);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
