package com.cjj.www.service;

import com.cjj.www.dao.*;
import com.cjj.www.pojo.Note;
import com.cjj.www.pojo.Paging;
import com.cjj.www.pojo.Tag;
import com.cjj.www.pojo.User;
import com.cjj.www.util.WebUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

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
        return noteDao.queryNoteByUserId(user.getId());
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
            }else {
                for (Tag tag1:tags1){
                    Integer id=tag1.getNoteId();
                    Integer id1=tag.getNoteId();
                    if(id.equals(id1)){
                        return "这个笔记已经有"+tag.getTagMain()+"这个标签了！不要重复添加";
                    }
                }
            }
            tagDao.addTag(tag);
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
            if (note.getReleaseStatus().equals("1")) {
                notes1.add(note);
            }
        }
        return notes1;
    }
    /*
    写一个方法来将该用户的笔记分类：
    1、还未通过审核的笔记
    2、被驳回的笔记
     */
    public List<Note> checkingNote(List<Note> notes){
        List<Note> notes1=new ArrayList<>();
        for (Note note:notes){
            if(note.getReleaseStatus().equals("0")){
                notes1.add(note);
            }
        }
        return notes1;
    }
    public List<Note> turnBackNote(List<Note> notes){
        List<Note> notes1=new ArrayList<>();
        for (Note note:notes){
            if(note.getReleaseStatus().equals("-1")){
                notes1.add(note);
            }
        }
        return notes1;
    }

    @Override
    public List<Note> checkPublishNote(List<Note> notes) {
        List<Note> notes1=new ArrayList<>();
        for (Note note:notes){
            if(note.getReleaseStatus().equals("1")){
                notes1.add(note);
            }
        }
        return notes1;
    }

    @Override
    public boolean addBrowser(Integer noteId) {
        NoteDao noteDao=new NoteDaoImpl();
        return noteDao.addBrowse(noteId);
    }

    @Override
    public List<Note> sortNote(List<Note> notes, String action) {
        switch (action) {
            case "按点赞量排序":
                notes.sort((o1, o2) -> {
                    if (o1.getLikeCount().equals(o2.getLikeCount())) {
                        return o1.getId() - o2.getId();
                    } else {
                        return o2.getLikeCount() - o1.getLikeCount();
                    }
                });
                return notes;
            case "按浏览量排序":
                notes.sort((o1, o2) -> {
                    if (o1.getBrowse().equals(o2.getBrowse())) {
                        return o1.getId() - o2.getId();
                    } else {
                        return o2.getBrowse() - o1.getBrowse();
                    }
                });
                return notes;
            case "按热度排序":
            /*
            热度计算公式：浏览量占百分之七十，点赞占百分之三十
             */
                notes.sort((o1, o2) ->{
                    if(o2.getLikeCount()*0.3+o2.getBrowse()*0.7==o1.getLikeCount()*0.3+o1.getBrowse()*0.7){
                        return o1.getId()-o2.getId();
                    }else {
                        if(o2.getLikeCount()*0.3+o2.getBrowse()*0.7-o1.getLikeCount()*0.3+o1.getBrowse()*0.7>0){
                            return 1;
                        }else {
                            return -1;
                        }
                    }
            });
                return notes;
        }
            return null;
    }

    @Override
    public HttpServletRequest sort(HttpServletRequest request) {
        NoteService noteService=new NoteServiceImpl();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String action = request.getParameter("action");
        String root = request.getParameter("root");
        request.setAttribute("username",username);
        request.setAttribute("password",password);
        request.setAttribute("root",root);
        request.setAttribute("action",action);
        String number = request.getParameter("number");
        request.setAttribute("number",number);
        String pageNo = request.getParameter("pageNo");//当前页码
        Paging paging=new Paging();
            /*
            number用于区分当前页面
            1为首页、2为分区页、3为搜索页
             */
        if(number.equals("1")){
            //查询总记录数
            Integer record = noteService.queryNoteTotalPage();
            //转化为总页数
            Integer pageTotal = record % paging.getPageSize()>0 ? record/4+1:record/4;
                //查询显示在首页的所有笔记,并排好序
            List<Note> notes1 = noteService.sortNote(noteService.queryNote(), action);
                /*
                分页：
                由于分页逻辑不一样，所以不能用之前写好的方法。
                由于笔记已经提前查好，所以逻辑是每次只传四张笔记过去，页数跳转的时候需要重新查询所有笔记达到更新的目的。
                 */
            //若页面为null，说明是第一页
            if(pageNo==null){
                paging.setPageNo(1);
            }
            else {
                Integer no = WebUtil.toInteger(pageNo);
                if(no>pageTotal||no<=0){
                    request.setAttribute("jumpMsg","无效的数字！");
                    paging.setPageNo(1);
                }
                else {
                    paging.setPageNo(WebUtil.toInteger(pageNo));
                }
            }
            if(pageTotal==0){
                pageTotal=1;
            }
            //将需要显示的笔记封装
            List<Note> notes=new ArrayList<>();
            //起点: pageSize*pageNo-4, 终点：pageSize*PageNo-1并且循环变量不能大于分类好的笔记的size。
            for (int i=paging.getPageSize()*paging.getPageNo()-4;i<=paging.getPageSize()*paging.getPageNo()-1;i++){
                if(i==notes1.size()){
                    break;
                }else {
                    notes.add(notes1.get(i));
                }

            }
            request.setAttribute("notes",notes);
            request.setAttribute("pageTotal",pageTotal);
            request.setAttribute("pageNo",paging.getPageNo());
            request.setAttribute("record",record);
            request.setAttribute("sortMsg","笔记"+action+"如下！");
        }else if(number.equals("2")){
            String zoomName = request.getParameter("zoomName");
            //查询总记录数
            Integer record = noteService.queryNoteTotalPage(zoomName);
            // 转化为总页数
            Integer pageTotal = record % paging.getPageSize()>0 ? record/4+1:record/4;
            //查询该分区笔记
            List<Note> notes1 = noteService.sortNote(noteService.queryNoteByZoom(zoomName),action);
            if(pageNo==null){
                paging.setPageNo(1);
            }
            else {
                Integer no = WebUtil.toInteger(pageNo);
                if(no>pageTotal||no<=0){
                    request.setAttribute("jumpMsg","无效的数字！");
                    paging.setPageNo(1);
                }
                else {
                    paging.setPageNo(WebUtil.toInteger(pageNo));
                }
            }
            if(pageTotal==0){
                pageTotal=1;
            }
            //将需要显示的笔记封装
            List<Note> notes=new ArrayList<>();
            //起点: pageSize*pageNo-4, 终点：pageSize*PageNo-1并且循环变量不能大于分类好的笔记的size。
            for (int i=paging.getPageSize()*paging.getPageNo()-4;i<=paging.getPageSize()*paging.getPageNo()-1;i++){
                if(i==notes1.size()){
                    break;
                }else {
                    notes.add(notes1.get(i));
                }

            }
            request.setAttribute("notes",notes);
            request.setAttribute("pageTotal",pageTotal);
            request.setAttribute("pageNo",paging.getPageNo());
            request.setAttribute("record",record);
            request.setAttribute("zoomName",zoomName);
            request.setAttribute("sortMsg",zoomName+"笔记"+action+"如下！");
        }
        return request;
    }

    @Override
    public List<Note> queryNoteByZoom(String zoomName) {
        NoteDao noteDao=new NoteDaoImpl();
        return check(noteDao.queryNoteByZoom(zoomName));
    }
}
