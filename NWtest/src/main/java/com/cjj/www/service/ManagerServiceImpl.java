package com.cjj.www.service;

import com.cjj.www.dao.*;
import com.cjj.www.pojo.Note;
import com.cjj.www.pojo.User;
import com.cjj.www.pojo.UserStatus;

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
        NoteDao noteDao=new NoteDaoImpl();
        return noteDao.deleteNote(noteId);
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
    public List<User> queryAllUser() {
        ManagerDao managerDao=new ManagerDaoImpl();
        return managerDao.queryAllUser();
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


}
