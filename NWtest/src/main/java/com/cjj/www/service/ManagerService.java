package com.cjj.www.service;

import com.cjj.www.pojo.*;

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
    /*
    登录之后靠账号查到自己是在哪一个区
     */
    List<Note> queryNoteByAreaPaging(Integer begin,Integer end,String zoomName);
    boolean changeUserStatus(Integer userId, String userStatus, String zoomName);
    boolean deleteNoteByArea(Integer noteId);
    boolean setNoteReleaseStatus(Integer noteId,String releaseStatus);

    Map<Integer,String> queryZoomStatus(String zoomName);
    Integer queryTotalPage(String zoomName);
    //总管理员查询记录数
    Integer queryTotalPage();
    boolean backNoteReleaseStatus(Integer noteId);
    boolean changeNoteReleaseStatus(Integer noteId);
    List<Note> queryNoteByZoom(String zoomName);
    List<Report> queryReportedNote(String zoomName);
    boolean deleteReportMsg(Integer noteId,String username);
    boolean deleteReportMsg(Integer noteId);
    List<Note> queryNotePage(Integer begin,Integer end);
    boolean deleteNote(Integer noteId);
    List<Note> queryNote();
    //当区域管理员对笔记执行驳回或删除操作时，记录下他们的操作
    boolean saveOperation(Appeal appeal);
    //当驳回的笔记被修改，则只删除操作信息、当驳回的笔记被申诉，则加1
    //删除同理
    boolean deleteOperation(Integer noteId);
    boolean addAppeal(String username);
    //解除管理员身份之后重置申诉次数
    boolean resetAppeal(String username);
    //找到对笔记执行驳回或删除操作的管理员
    User findManager(Integer noteId);
    List<User> queryUserByRoot(String root);
    boolean resetUser(Integer userId);
<<<<<<< HEAD
=======
    //转变身份
    boolean changeUserZoom(Integer userId,String zoom);
    //发布公告
    String publishNotice(String main,String title);
    //查询公告
    List<Notice> queryNotices();
>>>>>>> 983e94e (ninth)
}
