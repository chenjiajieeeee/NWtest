package com.cjj.www.dao;

import com.cjj.www.pojo.*;

import java.util.List;

public interface ManagerDao {
    /**
     *
     * @param begin 查询起始点
     * @param end 查询终止点
     * @param zoomName 限定查询范围
     * @return list集合，为笔记对象的集合
     */
    List<Note>queryNoteByAreaPaging(Integer begin,Integer end,String zoomName);

    /**
     *
     * @param id 需要进行删除的笔记
     */
    void deleteNoteByManager(Integer id);

    /**
     *
     * @param noteId 需要设置状态的笔记
     * @param releaseStatus 新的发布状态
     * @return ture为设置成功，false为设置失败
     */
    boolean setNoteReleaseStatus(Integer noteId,String releaseStatus);

    /**
     *
     * @return 所有用户在某一区域的禁用状态
     */
    List<UserStatus> queryZoomStatus();

    /**
     *
     * @param zoomName 限定查询范围
     * @return 笔记总数
     */
    Integer queryNoteTotalPage(String zoomName);

    /**
     *
     * @param noteId 需要驳回的笔记的id
     * @return ture为驳回成功，false为驳回失败
     */
    boolean backNoteReleaseStatus(Integer noteId);

    /**
     * 假定删除
     * @param noteId 假定删除的笔记的id
     * @return ture为删除成功，false为删除失败
     */
    boolean deleteNoteByNoteId(Integer noteId);

    /**
     * 查询举报信息
     * @param zoomName 限定查询范围
     * @return list集合，为举报信息的集合
     */
    List<Report> queryReportedNoteMsg(String zoomName);

    /**
     * 删除举报信息
     * @param noteId 需要删除的举报的笔记的id
     * @param username 举报人的用户名
     */
    void deleteReportMsg(Integer noteId, String username);

    /**
     *
     * @param noteId 需要删除举报信息的笔记的id，全部删除
     */
    void deleteReportMsg(Integer noteId);

    /**
     *
     * @return 笔记的总数
     */
    Integer queryNoteTotalPage();

    /**
     *
     * @param begin 查询起始点
     * @param end 查询结束点
     * @return list集合 ，查询到的笔记对象的集合
     */
    List<Note> queryNotePage(Integer begin,Integer end);

    /**
     *
     * @param root 拥有的权限
     * @return list集合，为拥有该权限的管理员的集合
     */
    List<User> queryUserByRoot(String root);

    /**
     * 保存敏感操作
     * @param appeal appeal对象保存
     */
    void saveOperation(Appeal appeal);

    /**
     * 删除敏感操作
     * @param noteId 敏感操作对应的笔记的id
     */
    void deleteOperation(Integer noteId);

    /**
     * 增加某一管理员的被申述次数
     * @param username 管理员的用户名
     */
    void addAppeal(String username);

    /**
     * 重置被申述次数
     * @param username 执行重置的管理员的用户名
     */
    void resetAppeal(String username);

    /**
     *
     * @param noteId 被执行敏感操作的笔记的id
     * @return 该管理员的id
     */
    Integer findManager(Integer noteId);

    /**
     * 用于解除管理员的身份
     * @param userId 该管理员的用户id
     */
    void resetUser(Integer userId);

    /**
     * 用于修改管理员的身份
     * @param userId 被修改的管理员的id
     * @param zoom 修改去到的区域
     * @return ture为修改成功 false为修改失败
     */
    boolean changeZoom(Integer userId,String zoom);

    /**
     * 用于发布公告
     * @param main 公告的内容
     * @param title 公告的标题
     * @return ture为发布成功，false为发布失败
     */
    boolean publishNotice(String main,String title);

    /**
     * 用于查询公告
     * @return 封装好的notice对象
     */
    Notice queryNotice();
}
