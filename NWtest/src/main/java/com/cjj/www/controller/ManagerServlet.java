package com.cjj.www.controller;

import com.cjj.www.pojo.*;
import com.cjj.www.service.*;
import com.cjj.www.util.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
<<<<<<< HEAD

=======
>>>>>>> 983e94e (ninth)
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@WebServlet("/manager/*")
public class ManagerServlet extends BaseServlet{
         ManagerService managerService=new ManagerServiceImpl();
         NoteService noteService=new NoteServiceImpl();
    public void chargeNote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        根据分区查找笔记，笔记分页，其实和首页一样，但是不能点进去查看详情。
         */
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String root =request.getParameter("root");

            PagingService pagingService = new PagingServiceImpl();
            HttpServletRequest req = pagingService.paging(request, root, response, 1);
            req.setAttribute("root", root);
            req.setAttribute("username", username);
            req.setAttribute("password", password);
            req.getRequestDispatcher("/root/managerhome.jsp").forward(req, response);

    }
    public void reNote(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        Integer noteId = WebUtil.toInteger(request.getParameter("noteId"));
        NoteService noteService=new NoteServiceImpl();
        Note note = noteService.queryNoteByNoteId(noteId);
        String action = request.getParameter("action");
        Appeal appeal=new Appeal();
        String username = request.getParameter("username");
        UserService userService=new UserServiceImpl();
        User user = userService.queryUserByUserName(username);
        appeal.setManagerId(user.getId());
        appeal.setNoteId(noteId);
        if ("删除".equals(action)) {
            boolean check = managerService.deleteNoteByArea(noteId);
            managerService.saveOperation(appeal);
            if (check) {
                request.setAttribute("deleteNoteMsg", "删除标题为"+note.getTitle()+"的笔记成功！");
            } else {
                request.setAttribute("deleteMsg", "服务器出问题了！");
            }
            chargeNote(request, response);
        }else if("同意".equals(action)){
            String releaseStatus = request.getParameter("releaseStatus");
            boolean check = managerService.setNoteReleaseStatus(noteId, releaseStatus);
            managerService.saveOperation(appeal);
            if(check){
                request.setAttribute("agreeMsg","笔记已发布！");
            }else {
                request.setAttribute("agreeMsg","服务器出问题了！");
            }
            chargeNote(request,response);
        }else if("驳回".equals(action)){
            boolean check = managerService.backNoteReleaseStatus(noteId);
            managerService.saveOperation(appeal);
            if(check){
                request.setAttribute("backMsg","已驳回");
            }else {
                request.setAttribute("backMsg","服务器出问题了");
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
    public void chargeNoteBatch(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String root = request.getParameter("root");
        /*
        查找该分区的所有笔记显示，不分页。
        将笔记分为三类：
        已发布：对应批量删除
        审核中：对应批量同意或批量驳回
        已驳回：对应查看
         */
        List<Note> notes = managerService.queryNoteByZoom(root);
        //分出来的正在审核的笔记
        List<Note> notes1 = noteService.checkingNote(notes);
        request.setAttribute("notes1",notes1);
        //分出来的已经发布的笔记
        List<Note> notes2 = noteService.checkPublishNote(notes);
        request.setAttribute("notes2",notes2);
        //分出来的已驳回的笔记
        List<Note> notes3 = noteService.turnBackNote(notes);
        request.setAttribute("notes3",notes3);
        //传递值
        request.setAttribute("password",password);
        request.setAttribute("username",username);
        request.setAttribute("root",root);
        request.getRequestDispatcher("/root/batchOperation.jsp").forward(request,response);
    }
    public void chargeNoteBatchs(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String[] noteIds = request.getParameterValues("noteId");
        String action = request.getParameter("action");
        List<Integer> ids=new ArrayList<>();
        Appeal appeal=new Appeal();
        UserService userService=new UserServiceImpl();
        User user=userService.queryUserByUserName(username);
        for (String id:noteIds){
            ids.add(WebUtil.toInteger(id));
        }
        switch (action){
            case "批量删除":{
                for (Integer id:ids){
                    managerService.deleteNoteByArea(id);
                    appeal.setNoteId(id);
                    appeal.setManagerId(user.getId());
                    managerService.saveOperation(appeal);
                    appeal=new Appeal();
                }
                request.setAttribute("batchDelete","批量删除成功");
                break;
            }
            case "批量同意":{
                for (Integer id:ids){
                    managerService.setNoteReleaseStatus(id,"0");
                }
                request.setAttribute("batchAgree","批量同意成功");
                break;
            }
            case "批量驳回":{
                for (Integer id:ids){
                    managerService.backNoteReleaseStatus(id);
                    appeal.setNoteId(id);
                    appeal.setManagerId(user.getId());
                    managerService.saveOperation(appeal);
                    appeal=new Appeal();
                }
                request.setAttribute("batchBack","批量驳回成功");
                break;
            }
        }
        chargeNoteBatch(request,response);
    }
    public void dealReportedNote(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        //接收参数
        String root = request.getParameter("root");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //传递参数
        request.setAttribute("root",root);
        request.setAttribute("username",username);
        request.setAttribute("password",password);


            //查询管理员需要处理的笔记
            List<Report> reports = managerService.queryReportedNote(root);
            List<Note> notes=new ArrayList<>();
            for (Report report:reports){
                //对应的笔记
                notes.add(noteService.queryNoteByNoteId(report.getNoteId()));
            }
            //传递参数
            request.setAttribute("reports",reports);
            request.setAttribute("notes",notes);
            request.getRequestDispatcher("/root/report.jsp").forward(request,response);

    }public void dealingReportNote(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String action = request.getParameter("action");
        Integer noteId = WebUtil.toInteger(request.getParameter("noteId"));
        UserService userService=new UserServiceImpl();
        User user=userService.queryUserByUserName(username);
        Appeal appeal=new Appeal();
        appeal.setManagerId(user.getId());
        appeal.setNoteId(noteId);
        switch (action) {
            case "忽略":
                //根据笔记id以及举报人的用户名删除对应的举报信息
                managerService.deleteReportMsg(noteId, username);
                //提示
                request.setAttribute("reportResult", "已忽略");
                break;
            case "驳回":
                //驳回
                managerService.backNoteReleaseStatus(noteId);
                managerService.deleteReportMsg(noteId);
                managerService.saveOperation(appeal);
                //删除笔记id对应的举报信息
                request.setAttribute("reportResult", "已驳回");
                break;
            case "删除":
                //删除
                managerService.deleteNoteByArea(noteId);
                managerService.deleteReportMsg(noteId);
                //删除信息
                managerService.saveOperation(appeal);
                request.setAttribute("reportResult", "已删除");
                break;
        }
        dealReportedNote(request,response);
    }
    public void chargeAllNote(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //调用分页业务
        PagingService pagingService=new PagingServiceImpl();
        HttpServletRequest req = pagingService.superManagerPaging(request);
        UserService userService=new UserServiceImpl();
        User user = userService.queryUserByUserName(username);
        req.setAttribute("username",username);
        req.setAttribute("password",password);
        req.setAttribute("root",user.getRoot());
        req.getRequestDispatcher("/root/totalDelete.jsp").forward(request,response);
    }
    public void deleteNote(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        Integer noteId = WebUtil.toInteger(request.getParameter("noteId"));
        managerService.deleteNote(noteId);
        request.setAttribute("deleteMsg","删除笔记成功！");
        chargeAllNote(request,response);
    }
    public void batchDeleteNote(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String root = request.getParameter("root");
        request.setAttribute("username",username);
        request.setAttribute("password",password);
        request.setAttribute("root",root);
        List<Note> notes = managerService.queryNote();
        request.setAttribute("notes",notes);
        request.getRequestDispatcher("/root/totalBatch.jsp").forward(request,response);
    }
    public void confirm(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        String[] noteIds = request.getParameterValues("noteId");
        for (String id:noteIds){
            Integer Id = WebUtil.toInteger(id);
            managerService.deleteNote(Id);
        }
        request.setAttribute("batchMsg","批量删除成功！");
        batchDeleteNote(request,response);
    }
    public void chargeManagerUser(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        //获取参数&传递参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String root = request.getParameter("root");
        request.setAttribute("username",username);
        request.setAttribute("password",password);
        request.setAttribute("root",root);
        //查找每个分区的管理员
        List<User> users4 = managerService.queryUserByRoot("游戏区");
        List<User> users3 = managerService.queryUserByRoot("动漫区");
        List<User> users2 = managerService.queryUserByRoot("学习区");
        List<User> users1 = managerService.queryUserByRoot("美食区");
        List<User> users = managerService.queryUserByRoot("科技区");
        //传递数据
        request.setAttribute("users",users);
        request.setAttribute("users1",users1);
        request.setAttribute("users2",users2);
        request.setAttribute("users3",users3);
        request.setAttribute("users4",users4);
            request.getRequestDispatcher("/root/chargeAll.jsp").forward(request,response);
    }
    public void resetManager(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        Integer userId=WebUtil.toInteger(request.getParameter("userId"));
        managerService.resetUser(userId);
        UserService userService=new UserServiceImpl();
        User user=userService.queryUserByUserId(userId);
        managerService.resetAppeal(user.getUsername());
        request.setAttribute("resetMsg","解除身份成功！");
        chargeManagerUser(request,response);
    }
<<<<<<< HEAD
=======
    public void changeUser(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        Integer userId=WebUtil.toInteger(request.getParameter("userId"));
        UserService userService=new UserServiceImpl();
        User user = userService.queryUserByUserId(userId);
        String zoom = request.getParameter("zoom");
        boolean check = managerService.changeUserZoom(userId, zoom);
        if(check){
            request.setAttribute("changeMsg","修改"+user.getUsername()+"身份成功！");
        }else {
            request.setAttribute("changeMsg","他已经是"+zoom+"的区域管理员了！");
        }
        chargeManagerUser(request,response);
    }
    public void notice(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String root = request.getParameter("root");
        //跳转页面
        request.setAttribute("username",username);
        request.setAttribute("password",password);
        request.setAttribute("root",root);
        request.getRequestDispatcher("/root/notice.jsp").forward(request,response);
    }
    public void publishNotice(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        String title = request.getParameter("title");
        String main = request.getParameter("main");
        String root = request.getParameter("root");
        //存入数据库
        String result = managerService.publishNotice(main, title);
        request.setAttribute("resultMsg",result);
        notice(request,response);
    }
>>>>>>> 983e94e (ninth)
}
