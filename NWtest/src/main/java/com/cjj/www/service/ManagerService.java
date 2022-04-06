package com.cjj.www.service;

import com.cjj.www.pojo.Note;
import com.cjj.www.pojo.User;
import com.cjj.www.pojo.UserStatus;

import java.util.List;
import java.util.Map;

public interface ManagerService {
    /**
     * 管理员分为：区域管理员和系统管理员
     * 区域管理员可以：
     * 登录该区域自己的账号
     * 1、查询其区域的笔记以及对应的发布者名称
     * 2、可以禁止用户在该区域继续发表笔记，但是当前已经发布的笔记要手动删除
     * 3、可以删除该用户的笔记
     * 4、审核用户还没有发布的笔记
     */
    boolean login(String account,String password);
    /*
    登录之后靠账号查到自己是在哪一个区
     */
    String searchZoom(String account);
    List<Note> queryNoteByArea(String zoomName);
    boolean changeUserStatus(Integer userId, String userStatus, String zoomName);
    boolean deleteNoteByArea(Integer noteId);
    boolean setNoteReleaseStatus(Integer noteId,String releaseStatus);
    List<User> queryAllUser();
    Map<Integer,String> queryZoomStatus(String zoomName);

}
