package com.cjj.www.dao;

import com.cjj.www.pojo.Note;

import java.util.List;

public interface NoteDao {
    /**
     * 查询笔记
     */
    List <Note> queryNoteByUserId(Integer id);
    List<Note> queryNote();
    /**
     * 保存笔记
     */
    boolean saveNote(Note note);

    /**
     * 修改笔记
     */
    boolean updateNoteMain(String main ,Integer id);
    boolean updateNoteTitle(String title,Integer id);
    /**
     * 删除笔记
     */
    boolean deleteNote(Integer id);
    Note queryNoteByNoteId(Integer noteId);
    /*
    分页功能对应查找响应数量的笔记
    每页规定显示四个笔记
     */
    List<Note> queryNotePaging(Integer begin,Integer end);
    /*
    分区的话要筛选区域
     */
    List<Note> queryNotePaging(Integer begin,Integer end,String zoomName);


    Integer queryNoteTotalPage();
    Integer queryNoteTotalPage(String zoomName);
    boolean insertPictureUrl(String url,Integer noteId);
}
