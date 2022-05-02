package com.cjj.www.dao;

import com.cjj.www.pojo.Note;
import com.cjj.www.pojo.Report;

import java.util.List;

public interface NoteDao {
    /**
     * 用于查询该用户下的笔记
     * @param id 该用户的id
     * @return list集合，该用户笔记的集合
     */
    List <Note> queryNoteByUserId(Integer id);

    /**
     * 用于查询所有笔记
     * @return list集合，所有笔记的集合
     */
    List<Note> queryNote();

    /**
     * 用于查询对应分区的所有笔记
     * @param zoomName 分区名
     * @return list集合，分区笔记的集合
     */
    List<Note> queryNoteByZoom(String zoomName);

    /**
     * 用于保存笔记
     * @param note 笔记对象，用于保存笔记
     * @return ture为保存成功，false为保存失败
     */
    boolean saveNote(Note note);

    /**
     * 修改笔记内容
     * @param main 新的笔记内容
     * @param id 修改的笔记的笔记id
     * @return ture为修改成功 false为修改失败
     */
    boolean updateNoteMain(String main ,Integer id);

    /**
     * 修改笔记标题
     * @param title 新的笔记标题
     * @param id 修改的笔记的笔记id
     * @return ture为修改成功，false为修改失败
     */
    boolean updateNoteTitle(String title,Integer id);

    /**
     * 用于删除笔记
     * @param id 需要删除的笔记的id
     * @return ture为删除成功，false为删除失败
     */
    boolean deleteNote(Integer id);

    /**
     * 根据笔记id查询该笔记内容
     * @param noteId 查询的笔记的id
     * @return note对象
     */
    Note queryNoteByNoteId(Integer noteId);

    /**
     * 分页查询笔记
     * @param begin 查询起始点
     * @param end 查询结束点
     * @return list集合，note对象的集合
     */
    List<Note> queryNotePaging(Integer begin,Integer end);

    /**
     * 分页查询分区笔记
     * @param begin 查询起始点
     * @param end 查询结束点
     * @param zoomName 限定查询范围
     * @return list对象，note对象的集合
     */
    List<Note> queryNotePaging(Integer begin,Integer end,String zoomName);

    /**
     * 查询笔记总数
     * @return count笔记总数
     */
    Integer queryNoteTotalPage();

    /**
     * 查询分区笔记总数
     * @param zoomName 限定查询范围
     * @return count笔记总数
     */
    Integer queryNoteTotalPage(String zoomName);

    /**
     * 插入图片地址
     * @param url 图片的地址
     * @param noteId 笔记的id
     */
    void insertPictureUrl(String url, Integer noteId);

    /**
     * 记录笔记的浏览量
     * @param noteId 笔记的id
     */
    void addBrowse(Integer noteId) ;

    /**
     * 将举报信息存入数据库
     * @param report 举报的对象
     * @return ture为举报成功 false为举报失败
     */
    boolean insertReportMsg(Report report);

    /**
     * 判断用户是否对笔记举报多次
     * @param username 用户名
     * @param noteId 笔记id
     * @return ture为举报多次 false为未举报
     */
   boolean queryReported(String username,Integer noteId);
}
