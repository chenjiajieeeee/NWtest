package com.cjj.www.dao;

import com.cjj.www.pojo.Note;
import com.cjj.www.pojo.User;
import com.cjj.www.pojo.UserStatus;

import java.util.List;

public interface ManagerDao {
    /**
     * 管理员分为：区域管理员和系统管理员
     * 区域管理员可以：
     * 登录该区域自己的账号
     * 1、查询其区域的笔记以及对应的发布者名称
     * 2、可以禁止用户在该区域继续发表笔记，但是当前已经发布的笔记要手动删除
     * 3、可以删除该用户的笔记
     * 4、审核用户还没有发布的笔记
     */
    List<Note>queryNoteByAreaPaging(Integer begin,Integer end,String zoomName);
    boolean ChangeUserStatus(Integer userId, String userStatus,String zoomName);
    boolean deleteNoteByManager(Integer id);
    boolean setNoteReleaseStatus(Integer noteId,String releaseStatus);
    /**
     * 还有要查到每个用户的信息以及其在该区域的禁用状态
     */
    List<User> queryAllUser();
    List<UserStatus> queryZoomStatus();
    Integer queryNoteTotalPage(String zoomName);
}
