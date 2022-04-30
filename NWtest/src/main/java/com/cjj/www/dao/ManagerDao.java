package com.cjj.www.dao;

import com.cjj.www.pojo.*;

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
    boolean deleteNoteByManager(Integer id);
    boolean setNoteReleaseStatus(Integer noteId,String releaseStatus);
    /**
     * 还有要查到每个用户的信息以及其在该区域的禁用状态
     */

    List<UserStatus> queryZoomStatus();
    Integer queryNoteTotalPage(String zoomName);
    boolean backNoteReleaseStatus(Integer noteId);
    //假定删除
    //noteId查找该笔记所在行并将其status该为-2
    boolean deleteNoteByNoteId(Integer noteId);
    /*
    显示被举报的笔记
    对被举报的笔记进行操作：驳回、删除
    操作完成后删除该举报信息。
     */
    List<Report> queryReportedNoteMsg(String zoomName);
    boolean deleteReportMsg(Integer noteId,String username);
    boolean deleteReportMsg(Integer noteId);
    Integer queryNoteTotalPage();
    List<Note> queryNotePage(Integer begin,Integer end);
    //查找不同分区的管理员：
    List<User> queryUserByRoot(String root);
    //当区域管理员对笔记执行驳回或删除操作时，记录下他们的操作
    boolean saveOperation(Appeal appeal);
    //当驳回的笔记被修改，则只删除操作信息、当驳回的笔记被申诉，则加1
    //删除同理
    boolean deleteOperation(Integer noteId);
    boolean addAppeal(String username);
    //解除管理员身份之后重置申诉次数
    boolean resetAppeal(String username);
    //找到执行驳回或删除操作的管理员
    Integer findManager(Integer noteId);
    boolean resetUser(Integer userId);
    //转变区域管理员的身份
    boolean changeZoom(Integer userId,String zoom);
    //发布公告
    //修改逻辑：发布公告改为修改公告内容
    boolean publishNotice(String main,String title);
    //显示公告
    Notice queryNotice();
}
