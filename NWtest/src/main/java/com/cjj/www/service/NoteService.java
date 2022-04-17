package com.cjj.www.service;

import com.cjj.www.pojo.Note;
import com.cjj.www.pojo.Tag;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface NoteService {
    /**
     * 用于将笔记封装成list集合输出在首页
     *
     */
    List<Note> queryNote();
    /**
     * 用于将笔记封装成list集合输出在个人的空间
     * 进入个人首页时将用户名作为请求参数发送
     */
    List<Note> queryNoteByUsername(String username);
    /**
     * 常规的用户更新笔记，删除笔记，发布笔记
     */
    boolean updateNoteMain(String newMain,Integer id);
    boolean updateNoteTitle(String newTitle,Integer id);
    boolean deleteNote(Integer id);
    String saveNote(Note note ,String zoomName);
    Note queryNoteByNoteId(Integer noteId);
    /*
    分页的业务：
    servlet层会接收当前页数，并用公式(page No-1)*pageSize 得到begin
     */
    List<Note> queryNotePaging(Integer begin,Integer end);
    List<Note> queryNotePaging(Integer begin,Integer end,String zoomName);
    Integer queryNoteTotalPage();
    Integer queryNoteTotalPage(String zoomName);
    /*
    添加标签的业务，在笔记修改的页面可以为自己的笔记添加标签
    添加标签的注意事项：
    同一个笔记只能有同一个标签，不能反复添加，所以在添加之前要先看看这个笔记是否重复
    但是因为是复选框，所以会一次性添加多个tag
    这是需要封装好一个个tag在利用循环来进行添加。
     */
    String addTag(List<Tag> tags);
    /*
    搜索：用户输入tag，利用tag搜索所有含有含tag的笔记id
    同时通过该笔记id搜索到所有的笔记，也要分页。
     */
    List<Note> queryNoteByTag(String Tag,Integer begin);
    List<Tag> queryTagByNoteId(Integer noteId);
    Integer queryNoteTotalPageSearchByTag(String Tag);
    boolean insertNotePicture(String url,Integer noteId);
     List<Note> checkingNote(List<Note> notes);
     List<Note> turnBackNote(List<Note> notes);
    List<Note> checkPublishNote(List<Note> notes);
    boolean addBrowser(Integer noteId);
    List<Note> sortNote(List<Note> notes,String action);
    HttpServletRequest sort(HttpServletRequest request);
    List<Note> queryNoteByZoom(String zoomName);
    //查找被管理员删除的笔记
    List<Note> checkDeleteNote(List<Note> notes);
    //申诉
    boolean appeal(Integer noteId);
}
