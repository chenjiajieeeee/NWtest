package com.cjj.www.service;

import com.cjj.www.dao.*;
import com.cjj.www.pojo.*;
import com.cjj.www.util.LogUtil;
import com.cjj.www.util.WebUtil;
import com.cjj.www.websocket.PublishNotice;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManagerServiceImpl implements ManagerService{


    @Override
    public List<Note> queryNoteByAreaPaging(Integer begin,Integer end,String zoomName) {
        ManagerDao managerDao=new ManagerDaoImpl();
        return managerDao.queryNoteByAreaPaging(begin,end,zoomName);
    }



    @Override
    public boolean changeUserStatus(Integer userId, String userStatus, String zoomName) {
        UserStatusDao userStatusDao=new UserStatusDaoImpl();
        return   userStatusDao.setUserStatus(zoomName,userStatus,userId);
    }

    @Override
    public boolean deleteNoteByArea(Integer noteId) {
        ManagerDao managerDao=new ManagerDaoImpl();
        return managerDao.deleteNoteByNoteId(noteId);
    }

    @Override
    public boolean setNoteReleaseStatus(Integer noteId, String releaseStatus) {
        if(releaseStatus.equals("1")){
            return false;
        }else {
            releaseStatus="1";
        }
        ManagerDao managerDao=new ManagerDaoImpl();
        return managerDao.setNoteReleaseStatus(noteId,releaseStatus);
    }



    @Override
    public Map<Integer,String> queryZoomStatus(String zoomName) {
        ManagerDao managerDao=new ManagerDaoImpl();
        List<UserStatus> userStatuses = managerDao.queryZoomStatus();
        Map<Integer,String>allUserZoomStatus=new HashMap<>();
        for (UserStatus userStatus:userStatuses){
            switch (zoomName) {
                case "游戏区":
                    allUserZoomStatus.put(userStatus.getUserId(), userStatus.getGameZoomStatus());
                    break;
                case "科技区":
                    allUserZoomStatus.put(userStatus.getUserId(), userStatus.getItStatus());
                    break;
                case "学习区":
                    allUserZoomStatus.put(userStatus.getUserId(), userStatus.getStudyStatus());
                    break;
                case "美食区":
                    allUserZoomStatus.put(userStatus.getUserId(), userStatus.getFoodStatus());
                    break;
                case "动漫区":
                    allUserZoomStatus.put(userStatus.getUserId(), userStatus.getCartoonStatus());
                    break;
            }
        }
        return allUserZoomStatus;
    }

    @Override
    public Integer queryTotalPage(String zoomName) {
        ManagerDao managerDao=new ManagerDaoImpl();
        return managerDao.queryNoteTotalPage(zoomName);
    }

    @Override
    public Integer queryTotalPage() {
        ManagerDao managerDao=new ManagerDaoImpl();
        return managerDao.queryNoteTotalPage();
    }

    @Override
    public boolean backNoteReleaseStatus(Integer noteId) {
        ManagerDao managerDao=new ManagerDaoImpl();
        return managerDao.backNoteReleaseStatus(noteId);
    }

    @Override
    public boolean changeNoteReleaseStatus(Integer noteId) {
        ManagerDao managerDao=new ManagerDaoImpl();
        return managerDao.setNoteReleaseStatus(noteId, "0");
    }

    @Override
    public List<Note> queryNoteByZoom(String zoomName) {
        NoteDao noteDao=new NoteDaoImpl();
        return noteDao.queryNoteByZoom(zoomName);
    }

    @Override
    public List<Report> queryReportedNote(String zoomName) {
        ManagerDao managerDao=new ManagerDaoImpl();
        return managerDao.queryReportedNoteMsg(zoomName);
    }

    @Override
    public boolean deleteReportMsg(Integer noteId, String username) {
        ManagerDao managerDao=new ManagerDaoImpl();
        return managerDao.deleteReportMsg(noteId,username);
    }

    @Override
    public boolean deleteReportMsg(Integer noteId) {
        ManagerDao managerDao=new ManagerDaoImpl();
        return managerDao.deleteReportMsg(noteId);
    }

    @Override
    public List<Note> queryNotePage(Integer begin, Integer end) {
        ManagerDao managerDao=new ManagerDaoImpl();
        return managerDao.queryNotePage(begin,end);
    }

    @Override
    public boolean deleteNote(Integer noteId) {
        ManagerDao managerDao=new ManagerDaoImpl();
        return managerDao.deleteNoteByManager(noteId);
    }

    @Override
    public List<Note> queryNote() {
        NoteDao noteDao=new NoteDaoImpl();
        return noteDao.queryNote();
    }

    @Override
    public boolean saveOperation(Appeal appeal) {
        ManagerDao managerDao=new ManagerDaoImpl();
        return managerDao.saveOperation(appeal);
    }

    @Override
    public boolean deleteOperation(Integer noteId) {
        ManagerDao managerDao=new ManagerDaoImpl();
        return managerDao.deleteOperation(noteId);
    }

    @Override
    public boolean addAppeal(String username) {
        ManagerDao managerDao=new ManagerDaoImpl();
        return managerDao.addAppeal(username);
    }

    @Override
    public boolean resetAppeal(String username) {
        ManagerDao managerDao=new ManagerDaoImpl();
        return managerDao.resetAppeal(username);
    }

    @Override
    public User findManager(Integer noteId) {
        ManagerDao managerDao=new ManagerDaoImpl();
        Integer managerId = managerDao.findManager(noteId);
        UserService userService=new UserServiceImpl();
        return userService.queryUserByUserId(managerId);
    }

    @Override
    public List<User> queryUserByRoot(String root) {
        ManagerDao managerDao=new ManagerDaoImpl();
        return managerDao.queryUserByRoot(root);
    }

    @Override
    public boolean resetUser(Integer userId) {
        ManagerDao managerDao=new ManagerDaoImpl();
        return managerDao.resetUser(userId);
    }

    @Override
    public boolean changeUserZoom(Integer userId, String zoom) {
        UserService userService=new UserServiceImpl();
        User user = userService.queryUserByUserId(userId);
        if(user.getRoot().equals(zoom)){
            return false;
        }else {
            ManagerDao managerDao=new ManagerDaoImpl();
            return managerDao.changeZoom(userId,zoom);
        }

    }

    @Override
    public String publishNotice(String main,String title) {
        ManagerDao managerDao=new ManagerDaoImpl();
        if(main.equals("")||title.equals("")){
            return "标题或内容不能为空！";
        }
        else {
            if(managerDao.publishNotice(main,title)){
                return "发布公告成功！";
            }
            else return "服务器出问题了！";
        }
    }
    @Override
    public Notice queryNotices(){
        ManagerDao managerDao=new ManagerDaoImpl();
        return managerDao.queryNotice();
    }

    @Override
    public void chargeNote(HttpServletRequest request, HttpServletResponse response) {
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
        try {
            req.getRequestDispatcher("/root/managerhome.jsp").forward(req, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void reNote(HttpServletRequest request, HttpServletResponse response) {
        ManagerService managerService=new ManagerServiceImpl();
        String root = request.getParameter("root");
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
                //将行为记录到日志文件中：
                LogUtil logUtil=new LogUtil();
                Logger logger = null;
                try {
                    logger = logUtil.WriteLog("com.cjj.www.service.ManagerService");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                assert logger != null;
                logger.log(Level.INFO,"{0}的 管理员 {1} 对标题为 {2} 的笔记执行了删除操作！",new Object[]{root,username,note.getTitle()});
                logUtil.getFileHandler().close();
            } else {
                request.setAttribute("deleteMsg", "服务器出问题了！");
            }
            chargeNote(request, response);
        }else if("同意".equals(action)){
            String releaseStatus = request.getParameter("releaseStatus");
            boolean check = managerService.setNoteReleaseStatus(noteId, releaseStatus);
            managerService.saveOperation(appeal);
            if(check){
                //将行为记录到日志文件中：
                LogUtil logUtil=new LogUtil();
                Logger logger = null;
                try {
                    logger = logUtil.WriteLog("com.cjj.www.service.ManagerService");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                assert logger != null;
                logger.log(Level.INFO,"{0}的 管理员 {1} 通过了标题为 {2} 的笔记的审核！",new Object[]{root,username,note.getTitle()});
                logUtil.getFileHandler().close();
                request.setAttribute("agreeMsg","笔记已发布！");
            }else {
                request.setAttribute("agreeMsg","服务器出问题了！");
            }
            chargeNote(request,response);
        }else if("驳回".equals(action)){
            boolean check = managerService.backNoteReleaseStatus(noteId);
            managerService.saveOperation(appeal);
            if(check){
                //将行为记录到日志文件中：
                LogUtil logUtil=new LogUtil();
                Logger logger = null;
                try {
                    logger = logUtil.WriteLog("com.cjj.www.service.ManagerService");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                assert logger != null;
                logger.log(Level.INFO,"{0}的 管理员 {1} 驳回了标题为 {2} 的笔记！",new Object[]{root,username,note.getTitle()});
                logUtil.getFileHandler().close();
                request.setAttribute("backMsg","已驳回");
            }else {
                request.setAttribute("backMsg","服务器出问题了");
            }
            chargeNote(request,response);
        }
    }

    @Override
    public void chargeUser(HttpServletRequest request, HttpServletResponse response) {
        /*
        查询用户的姓名、id
        执行操作：
         */
        ManagerService managerService=new ManagerServiceImpl();
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
        try {
            request.getRequestDispatcher("/root/chargeUser.jsp").forward(request,response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setUserStatus(HttpServletRequest request, HttpServletResponse response) {
        ManagerService managerService=new ManagerServiceImpl();
        String username = request.getParameter("username");
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
            UserService userService=new UserServiceImpl();
            String username1 = userService.queryUserByUserId(userId).getUsername();
            //将行为记录到日志文件中：
            LogUtil logUtil=new LogUtil();
            Logger logger = null;
            try {
                logger = logUtil.WriteLog("com.cjj.www.service.ManagerService");
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert logger != null;
            logger.log(Level.INFO,"{0}的 管理员 {1} 禁用/解封了 {2} 用户！",new Object[]{root,username,username1});
            logUtil.getFileHandler().close();
            request.setAttribute("Msg","禁用/解封用户成功！");
        }else {
            request.setAttribute("Msg","服务器出问题了！");
        }
        chargeUser(request,response);
    }

    @Override
    public void chargeNoteBatch(HttpServletRequest request, HttpServletResponse response) {
        ManagerService managerService=new ManagerServiceImpl();
        NoteService noteService=new NoteServiceImpl();
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
        try {
            request.getRequestDispatcher("/root/batchOperation.jsp").forward(request,response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void chargeNoteBatchs(HttpServletRequest request, HttpServletResponse response) {
        ManagerService managerService=new ManagerServiceImpl();
        NoteService noteService=new NoteServiceImpl();
        String root = request.getParameter("root");
        String username = request.getParameter("username");
        String[] noteIds = request.getParameterValues("noteId");
        if(noteIds==null){
            request.setAttribute("batchOperaMsg","请先勾选笔记！");
        }else {
            String action = request.getParameter("action");
            List<Integer> ids = new ArrayList<>();
            Appeal appeal = new Appeal();
            UserService userService = new UserServiceImpl();
            User user = userService.queryUserByUserName(username);
            for (String id : noteIds) {
                ids.add(WebUtil.toInteger(id));
            }
            switch (action) {
                case "批量删除": {
                    for (Integer id : ids) {
                        managerService.deleteNoteByArea(id);
                        appeal.setNoteId(id);
                        appeal.setManagerId(user.getId());
                        managerService.saveOperation(appeal);
                        appeal = new Appeal();
                        LogUtil logUtil=new LogUtil();
                        Logger logger = null;
                        try {
                            logger = logUtil.WriteLog("com.cjj.www.service.ManagerService");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        String title = noteService.queryNoteByNoteId(id).getTitle();
                        assert logger != null;
                        logger.log(Level.INFO,"{0}的 管理员 {1} 对标题为 {2} 的笔记执行了删除操作！",new Object[]{root,username,title});
                        logUtil.getFileHandler().close();
                    }
                    request.setAttribute("batchDelete", "批量删除成功");
                    break;
                }
                case "批量同意": {
                    for (Integer id : ids) {
                        managerService.setNoteReleaseStatus(id, "0");
                        LogUtil logUtil=new LogUtil();
                        Logger logger = null;
                        try {
                            logger = logUtil.WriteLog("com.cjj.www.service.ManagerService");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        String title = noteService.queryNoteByNoteId(id).getTitle();
                        assert logger != null;
                        logger.log(Level.INFO,"{0}的 管理员 {1} 通过了标题为 {2} 的笔记发布！",new Object[]{root,username,title});
                        logUtil.getFileHandler().close();
                    }
                    request.setAttribute("batchAgree", "批量同意成功");
                    break;
                }
                case "批量驳回": {
                    for (Integer id : ids) {
                        managerService.backNoteReleaseStatus(id);
                        appeal.setNoteId(id);
                        appeal.setManagerId(user.getId());
                        managerService.saveOperation(appeal);
                        appeal = new Appeal();
                        LogUtil logUtil=new LogUtil();
                        Logger logger = null;
                        try {
                            logger = logUtil.WriteLog("com.cjj.www.service.ManagerService");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        String title = noteService.queryNoteByNoteId(id).getTitle();
                        assert logger != null;
                        logger.log(Level.INFO,"{0}的 管理员 {1} 驳回了标题为 {2} 的笔记！",new Object[]{root,username,title});
                        logUtil.getFileHandler().close();
                    }
                    request.setAttribute("batchBack", "批量驳回成功");
                    break;
                }
            }
        }
        chargeNoteBatch(request,response);
    }

    @Override
    public void dealReportNote(HttpServletRequest request, HttpServletResponse response) {
        ManagerService managerService=new ManagerServiceImpl();
        NoteService noteService=new NoteServiceImpl();
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
        try {
            request.getRequestDispatcher("/root/report.jsp").forward(request,response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dealingReportNote(HttpServletRequest request, HttpServletResponse response) {
        NoteService noteService=new NoteServiceImpl();
        ManagerService managerService=new ManagerServiceImpl();
        String root = request.getParameter("root");
        String username = request.getParameter("username");
        String reportUser = request.getParameter("reportUser");
        String action = request.getParameter("action");
        Integer noteId = WebUtil.toInteger(request.getParameter("noteId"));
        UserService userService=new UserServiceImpl();
        User user=userService.queryUserByUserName(username);
        Appeal appeal=new Appeal();
        appeal.setManagerId(user.getId());
        appeal.setNoteId(noteId);
        LogUtil logUtil=new LogUtil();
        Logger logger = null;
        try {
            logger = logUtil.WriteLog("com.cjj.www.service.ManagerService");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String title = noteService.queryNoteByNoteId(noteId).getTitle();
        switch (action) {
            case "忽略":
                //根据笔记id以及举报人的用户名删除对应的举报信息
                managerService.deleteReportMsg(noteId, reportUser);
                //提示
                assert logger != null;
                logger.log(Level.INFO,"{0}的 管理员 {1} 忽略了 {2} 对标题为 {3} 的笔记的举报！",new Object[]{root,username,reportUser,title});
                request.setAttribute("reportResult", "已忽略");
                break;
            case "驳回":
                //驳回
                managerService.backNoteReleaseStatus(noteId);
                managerService.deleteReportMsg(noteId);
                managerService.saveOperation(appeal);
                //删除笔记id对应的举报信息
                assert logger != null;
                logger.log(Level.INFO,"{0}的 管理员 {1} 处理了 {2} 对标题为 {3} 的笔记的举报，已将该笔记驳回！",new Object[]{root,username,reportUser,title});
                request.setAttribute("reportResult", "已驳回");
                break;
            case "删除":
                //删除
                managerService.deleteNoteByArea(noteId);
                managerService.deleteReportMsg(noteId);
                //删除信息
                managerService.saveOperation(appeal);
                assert logger != null;
                logger.log(Level.INFO,"{0}的 管理员 {1} 处理了 {2} 对标题为 {3} 的笔记的举报，已将该笔记删除！",new Object[]{root,username,reportUser,title});
                request.setAttribute("reportResult", "已删除");
                break;
        }
        logUtil.getFileHandler().close();
        managerService.dealReportNote(request,response);
    }

    @Override
    public void chargeAllNote(HttpServletRequest request, HttpServletResponse response) {
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
        try {
            req.getRequestDispatcher("/root/totalDelete.jsp").forward(request,response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteNote(HttpServletRequest request, HttpServletResponse response) {
        NoteService noteService=new NoteServiceImpl();
        ManagerService managerService=new ManagerServiceImpl();
        Integer noteId = WebUtil.toInteger(request.getParameter("noteId"));
        String title = noteService.queryNoteByNoteId(noteId).getTitle();
        managerService.deleteNote(noteId);
        request.setAttribute("deleteMsg","删除笔记成功！");
        LogUtil logUtil=new LogUtil();
        Logger logger = null;
        try {
            logger = logUtil.WriteLog("com.cjj.www.controller.ManagerServlet");
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert logger != null;
        logger.log(Level.INFO,"超级管理员删除了标题为 {0} 的笔记",new Object[]{title});
        logUtil.getFileHandler().close();
        chargeAllNote(request,response);
    }

    @Override
    public void batchDeleteNote(HttpServletRequest request, HttpServletResponse response) {
        ManagerService managerService=new ManagerServiceImpl();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String root = request.getParameter("root");
        request.setAttribute("username",username);
        request.setAttribute("password",password);
        request.setAttribute("root",root);
        List<Note> notes = managerService.queryNote();
        request.setAttribute("notes",notes);
        try {
            request.getRequestDispatcher("/root/totalBatch.jsp").forward(request,response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void confirm(HttpServletRequest request, HttpServletResponse response) {
        NoteService noteService=new NoteServiceImpl();
        ManagerService managerService=new ManagerServiceImpl();
        String[] noteIds = request.getParameterValues("noteId");
        for (String id:noteIds){
            Integer Id = WebUtil.toInteger(id);
            String title = noteService.queryNoteByNoteId(Id).getTitle();
            managerService.deleteNote(Id);
            LogUtil logUtil=new LogUtil();
            Logger logger = null;
            try {
                logger = logUtil.WriteLog("com.cjj.www.controller.ManagerServlet");
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert logger != null;
            logger.log(Level.INFO,"超级管理员删除了标题为 {0} 的笔记",new Object[]{title});
            logUtil.getFileHandler().close();
        }
        request.setAttribute("batchMsg","批量删除成功！");
        batchDeleteNote(request,response);
    }

    @Override
    public void chargeManagerUser(HttpServletRequest request, HttpServletResponse response) {
        ManagerService managerService=new ManagerServiceImpl();
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
        try {
            request.getRequestDispatcher("/root/chargeAll.jsp").forward(request,response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setManager(HttpServletRequest request, HttpServletResponse response) {
        ManagerService managerService=new ManagerServiceImpl();
        String password = request.getParameter("password");
        request.setAttribute("password",password);
        Integer userId=WebUtil.toInteger(request.getParameter("userId"));
        managerService.resetUser(userId);
        UserService userService=new UserServiceImpl();
        User user=userService.queryUserByUserId(userId);
        managerService.resetAppeal(user.getUsername());
        request.setAttribute("resetMsg","解除身份成功！");
        LogUtil logUtil=new LogUtil();
        Logger logger = null;
        try {
            logger = logUtil.WriteLog("com.cjj.www.controller.ManagerServlet");
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert logger != null;
        logger.log(Level.INFO,"超级管理员解除了 {0} 在 {1} 的管理员身份！",new Object[]{user.getUsername(),user.getRoot()});
        logUtil.getFileHandler().close();
        chargeManagerUser(request,response);
    }

    @Override
    public void changeUser(HttpServletRequest request, HttpServletResponse response) {
        ManagerService managerService=new ManagerServiceImpl();
        Integer userId=WebUtil.toInteger(request.getParameter("userId"));
        request.setAttribute("password",request.getParameter("password"));
        UserService userService=new UserServiceImpl();
        User user = userService.queryUserByUserId(userId);
        String zoom = request.getParameter("zoom");
        boolean check = managerService.changeUserZoom(userId, zoom);
        if(check){
            request.setAttribute("changeMsg","修改"+user.getUsername()+"身份成功！");
            LogUtil logUtil=new LogUtil();
            Logger logger = null;
            try {
                logger = logUtil.WriteLog("com.cjj.www.service.ManagerService");
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert logger != null;
            logger.log(Level.INFO,"超级管理员将 {0} 的权限从 {1} 转移到了 {2} ！",new Object[]{user.getUsername(),user.getRoot(),zoom});
            logUtil.getFileHandler().close();
        }else {
            request.setAttribute("changeMsg","他已经是"+zoom+"的区域管理员了！");
        }
        chargeManagerUser(request,response);
    }

    @Override
    public void notice(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String root = request.getParameter("root");
        //跳转页面
        request.setAttribute("username",username);
        request.setAttribute("password",password);
        request.setAttribute("root",root);
        try {
            request.getRequestDispatcher("/root/notice.jsp").forward(request,response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void publishNote(HttpServletRequest request, HttpServletResponse response) {
        ManagerService managerService=new ManagerServiceImpl();
        String title = request.getParameter("title");
        String main = request.getParameter("main");
        //存入数据库
        String result = managerService.publishNotice(main, title);
        request.setAttribute("resultMsg",result);
        PublishNotice publishNotice=new PublishNotice();
        Session session=null;
        try {
            publishNotice.onMessage("小孩子不懂，传着玩的",session);
        } catch (IOException e) {
            e.printStackTrace();
        }
        notice(request,response);
    }

}
