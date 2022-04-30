package com.cjj.www.dao;

import com.cjj.www.pojo.Note;
import com.cjj.www.pojo.Report;

import java.sql.SQLException;
import java.util.List;

public interface NoteDao {
    /**
     * 查询笔记
     * 1、根据笔记id查询笔记
     * 2、查询所有笔记
     * 3、根据分区查询笔记
     */
    List <Note> queryNoteByUserId(Integer id);
    List<Note> queryNote();
    List<Note> queryNoteByZoom(String zoomName);
    /**
     * 保存笔记
     */
    boolean saveNote(Note note);

    /**
     * 修改笔记
     * 均为根据笔记id修改笔记
     */
    boolean updateNoteMain(String main ,Integer id);
    boolean updateNoteTitle(String title,Integer id);
    /**
     * 删除笔记
     * id；笔记的id
     * 根据笔记的id删除笔记
     */
    boolean deleteNote(Integer id);

    /**
     * 根据noteId来查询笔记
     *
     */
    Note queryNoteByNoteId(Integer noteId);
    /*
    分页功能对应查找响应数量的笔记 begin表示从哪里开始查询
                             end表示到哪里为止
    每页规定显示四个笔记
     */
    List<Note> queryNotePaging(Integer begin,Integer end);
    /*
    分区的话要筛选区域
    方法重载 多加一个限定词：分区名
     */
    List<Note> queryNotePaging(Integer begin,Integer end,String zoomName);

     /*
     查询总记录
      */
    Integer queryNoteTotalPage();
     /*
     查询分区总记录，方法重载
      */
    Integer queryNoteTotalPage(String zoomName);
    /*
    插入图片地址
     */
    boolean insertPictureUrl(String url,Integer noteId);
    /*
    点入笔记查看详情时，浏览量加一
     */
    boolean addBrowse(Integer noteId) ;
    /*
    举报功能
     */
    boolean insertReportMsg(Report report);
    //判断同一用户对该笔记是否举报多次
   boolean queryReported(String username,Integer noteId);
}
