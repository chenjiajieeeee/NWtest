package com.cjj.www.service;

import com.cjj.www.dao.*;
import com.cjj.www.pojo.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

}
