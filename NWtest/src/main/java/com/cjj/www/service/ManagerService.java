package com.cjj.www.service;

import com.cjj.www.pojo.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface ManagerService {
    /**
     *
     * @param begin 起始页数转换来的查询起始点
     * @param end end=begin+pageSize 查询重点
     * @param zoomName 限定查询范围仅为某一区的笔记
     * @return list 集合每一个对象都为note对象
     */
    List<Note> queryNoteByAreaPaging(Integer begin,Integer end,String zoomName);

    /**
     *
     * @param userId 需要改变状态的用户的id
     * @param userStatus 改变后的状态 1 为允许发表笔记， 0为禁止发表笔记
     * @param zoomName 限定范围，表示改变用户在某一区域的发布状态
     * @return true为修改成功， false为修改失败
     */
    boolean changeUserStatus(Integer userId, String userStatus, String zoomName);

    /**
     *
     * @param noteId 删除的笔记的id
     * @return ture为删除成功，false为删除失败
     */
    boolean deleteNoteByArea(Integer noteId);

    /**
     *
     * @param noteId 需要改变状态的笔记
     * @param releaseStatus 改变后的状态 1为发布状态 0为待审核状态 -1 为驳回状态 -2 为管理员删除状态（该状态下只有用户本人能看见该笔记）
     * @return ture 为修改成功 false 为修改失败
     */
    boolean setNoteReleaseStatus(Integer noteId,String releaseStatus);

    /**
     *
     * @param zoomName 限定查询某一区域所有用户的状态
     *
     * @return map集合， 键为用户的id，值为该用户在zoomName限定区的状态
     */
    Map<Integer,String> queryZoomStatus(String zoomName);

    /**
     *
     * @param zoomName 限定查询条件在某一区域进行查询
     * @return 某一分区下的总笔记数
     */
    Integer queryTotalPage(String zoomName);

    /**
     *
     * @return 所有笔记的总数
     */
    Integer queryTotalPage();

    /**
     *
     * @param noteId 执行驳回操作的笔记的id
     * @return ture为执行成功， false为执行失败
     */
    boolean backNoteReleaseStatus(Integer noteId);

    /**
     *
     * @param noteId 执行进行审核的笔记的id
     */
    void changeNoteReleaseStatus(Integer noteId);

    /**
     *
     * @param zoomName 限定查询范围为某一区域的笔记
     * @return list集合，每一个元素都是note对象
     */
    List<Note> queryNoteByZoom(String zoomName);

    /**
     *
     * @param zoomName 限定查询范围为某一区域被举报的笔记
     * @return list集合， 每一个元素都是report对象 （包括被举报的笔记的id， 举报人的id ，举报的内容）
     */
    List<Report> queryReportedNote(String zoomName);

    /**
     *
     * @param noteId 对被举报的笔记进行处理后，删除该举报信息，通过该笔记id查找该举报信息
     * @param username 举报人的名字
     */
    void deleteReportMsg(Integer noteId, String username);

    /**
     *
     * @param noteId 对被举报的笔记进行处理后，删除关于该笔记的所有举报信息，通过该笔记id查找该举报信息
     */
    void deleteReportMsg(Integer noteId);

    /**
     *
     * @param begin 查询笔记起始点
     * @param end 查询笔记结束点
     * @return list集合，为查询到的笔记
     */
    List<Note> queryNotePage(Integer begin,Integer end);

    /**
     *
     * @param noteId 需要执行删除操作的笔记的id
     */
    void deleteNote(Integer noteId);

    /**
     *
     * @return list集合，为数据库中所有的笔记对象
     */
    List<Note> queryNote();

    /**
     *
     * @param appeal appeal对象，当管理员对某一笔记执行敏感操作时将其封装为appeal对象进行储存
     */
    void saveOperation(Appeal appeal);

    /**
     *
     *  @param noteId 删除关于该笔记的敏感操作记录
     */
    void deleteOperation(Integer noteId);

    /**
     *
     * @param username 当用户选择申述时，根据执行敏感操作的用户的用户名找到该用户对象并且把其被申述次数加1
     */
    void addAppeal(String username);

    /**
     * 解除管理员身份之后重置申诉次数
     * @param username 该前管理员的用户名
     */
    void resetAppeal(String username);

    /**
     *  找到对笔记执行驳回或删除操作的管理员
     * @param noteId 被执行驳回或删除操作的管理员
     * @return User对象 为该管理员的信息
     */
    User findManager(Integer noteId);

    /**
     *
     * @param root 权限证明
     * @return 该区域所有管理员的信息
     */
    List<User> queryUserByRoot(String root);

    /**
     *
     * @param userId 需要解除管理员身份的用户的id
     */
    void resetUser(Integer userId);

    /**
     *
     * @param userId 需要转变身份的管理员的id
     * @param zoom 转移区域的名称
     * @return ture为成功，false为失败
     */
    boolean changeUserZoom(Integer userId,String zoom);

    /**
     *
     * @param main 公告的内容
     * @param title 公告的标题
     * @return 三种情况 ： “标题不能为空” “内容不能为空” “发布公告成功”
     */
    String publishNotice(String main,String title);

    /**
     *
     * @return 返回已经写入数据库了的所有的公告
     */
    Notice queryNotices();
    /**
     * 转移managerServlet
     * 不再赘述
     */
    void chargeNote(HttpServletRequest request, HttpServletResponse response);
    void reNote(HttpServletRequest request,HttpServletResponse response);
    void chargeUser(HttpServletRequest request,HttpServletResponse response);
    void setUserStatus(HttpServletRequest request,HttpServletResponse response);
    void chargeNoteBatch(HttpServletRequest request,HttpServletResponse response);
    void chargeNoteBatchs(HttpServletRequest request,HttpServletResponse response);
    void dealReportNote(HttpServletRequest request,HttpServletResponse response);
    void dealingReportNote(HttpServletRequest request,HttpServletResponse response);
    void chargeAllNote(HttpServletRequest request,HttpServletResponse response);
    void deleteNote(HttpServletRequest request,HttpServletResponse response);
    void batchDeleteNote(HttpServletRequest request,HttpServletResponse response);
    void confirm(HttpServletRequest request,HttpServletResponse response);
    void chargeManagerUser(HttpServletRequest request,HttpServletResponse response);
    void setManager(HttpServletRequest request,HttpServletResponse response);
    void changeUser(HttpServletRequest request,HttpServletResponse response);
    void notice(HttpServletRequest request,HttpServletResponse response);
    void publishNote(HttpServletRequest request,HttpServletResponse response);
}
