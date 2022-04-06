package com.cjj.www.service;

import com.cjj.www.dao.*;
import com.cjj.www.pojo.Note;
import com.cjj.www.pojo.Tag;
import com.cjj.www.pojo.User;

import java.util.ArrayList;
import java.util.List;

public class NoteServiceImpl implements NoteService {
    @Override
    public List<Note> queryNote() {
        NoteDao noteDao=new NoteDaoImpl();
        List<Note> notes = noteDao.queryNote();
        return check(notes);
    }

    @Override
    public List<Note> queryNoteByUsername(String username) {
        NoteDao noteDao=new NoteDaoImpl();
        User user=new User();
        user.setUsername(username);
        UserDao userDao=new UserDaoImpl();
        user=userDao.queryUserByUserName(username);
        return check(noteDao.queryNoteByUserId(user.getId()));
    }

    @Override
    public List<Note> queryNoteByNoteZoom(String zoom) {
        NoteDao noteDao=new NoteDaoImpl();
        return check(noteDao.queryNoteByZoom(zoom));


    }

    @Override
    public boolean updateNoteTitle(String title, Integer id) {
        if(title.equals("")){
            return false;
        }
        else {
            NoteDao noteDao=new NoteDaoImpl();
            return noteDao.updateNoteTitle(title, id);
        }
    }

    @Override
    public boolean deleteNote(Integer id) {
        NoteDao noteDao=new NoteDaoImpl();
         return noteDao.deleteNote(id);
    }

    @Override
    public boolean updateNoteMain(String newMain, Integer id) {
        if(newMain.equals("")){
            return false;
        }
        else {
            NoteDao noteDao=new NoteDaoImpl();
            return noteDao.updateNoteMain(newMain, id);
        }
    }



    @Override
    public String saveNote(Note note ,String zoomName) {
        if(note.getMain().equals("") || note.getTitle().equals("") || note.getZoomName().equals("")){
            return "标题内容分区都不能为空！";
        }
        NoteDao noteDao=new NoteDaoImpl();
        UserStatusDao userStatusDao=new UserStatusDaoImpl();
        String userStatus = userStatusDao.queryUserStatus(note.getUserId(), zoomName);
        if(userStatus.equals("0")){
            return "您在"+zoomName+"被管理员禁止发布笔记！请找管理员申诉！";
        }else {
            noteDao.saveNote(note);
            return "已经成功提交！待管理员审核后即可显示";
        }
    }

    @Override
    public Note queryNoteByNoteId(Integer noteId) {
        NoteDao noteDao=new NoteDaoImpl();
        return   noteDao.queryNoteByNoteId(noteId);
    }

    @Override
    public List<Note> queryNotePaging(Integer begin, Integer end) {
        NoteDao noteDao=new NoteDaoImpl();
        return noteDao.queryNotePaging(begin,end);
    }

    @Override
    public List<Note> queryNotePaging(Integer begin, Integer end, String zoomName) {
        NoteDao noteDao=new NoteDaoImpl();
        return noteDao.queryNotePaging(begin,end,zoomName);
    }

    @Override
    public Integer queryNoteTotalPage() {
        NoteDao noteDao=new NoteDaoImpl();
       return noteDao.queryNoteTotalPage();
    }

    @Override
    public Integer queryNoteTotalPage(String zoomName) {
        NoteDao noteDao=new NoteDaoImpl();
        return noteDao.queryNoteTotalPage(zoomName);
    }

    @Override
    public String addTag(List<Tag> tags) {
        TagDao tagDao=new TagDaoImpl();
        /*
        遍历提交的标签集合，如果有重复的标签就不添加并返回错误信息
         */
        if(tags.isEmpty()){
            return "不能不选标签就提交啊！";
        }
        for (Tag tag:tags){
            /*
            先看看有没有这个标签，如果没有那肯定可以添加
            如果有就看看笔记id是不是一样的
            如果一样就不行了
             */
            List<Tag> tags1 = tagDao.queryTagByTagMain(tag.getTagMain());
            if(tags1.isEmpty()){
                System.out.println(1);
                tagDao.addTag(tag);
                continue;
            }else {
                for (Tag tag1:tags1){
                    if(tag1.getNoteId().equals(tag.getNoteId())){
                        return "这个笔记已经有"+tag.getTagMain()+"这个标签了！不要重复添加";
                    }else {
                        tagDao.addTag(tag);
                        break;
                    }
                }
            }
        }
        return "提交成功！";
    }

    @Override
    public List<Note> queryNoteByTag(String Tag,Integer begin) {
        TagDao tagDao=new TagDaoImpl();
        NoteService noteService=new NoteServiceImpl();
        List<Integer> noteIds = tagDao.queryNoteIdByTagMain(Tag,begin);
        List<Note> notes=new ArrayList<>();
        for (Integer noteId:noteIds){
            notes.add(noteService.queryNoteByNoteId(noteId));
        }
        return notes;
    }

    @Override
    public List<Tag> queryTagByNoteId(Integer noteId) {
        TagDao tagDao=new TagDaoImpl();
        return tagDao.queryTagByNoteId(noteId);
    }

    @Override
    public Integer queryNoteTotalPageSearchByTag(String Tag) {
        TagDao tagDao=new TagDaoImpl();
        return tagDao.queryNoteTotalPageByMain(Tag);
    }

    @Override
    public boolean insertNotePicture(String url, Integer noteId) {
        NoteDao noteDao=new NoteDaoImpl();
        return  noteDao.insertPictureUrl(url,noteId);
    }


    /**
     * 写一个方法来审核笔记
     */
    private List<Note> check(List<Note> notes){
        List<Note> notes1=new ArrayList<>();
        /*
        检查查询到的笔记，将不通过审核的筛掉
         */
        for (Note note : notes) {
            if (!note.getReleaseStatus().equals("0")) {
                notes1.add(note);
            }
        }
        return notes1;
    }

}
