package com.cjj.www.service;

import com.cjj.www.dao.*;
import com.cjj.www.pojo.*;
import com.cjj.www.util.WebUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
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
    public void updateNote(HttpServletRequest request, HttpServletResponse response) {
        NoteService noteService=new NoteServiceImpl();
        ManagerService managerService=new ManagerServiceImpl();
        request.setAttribute("root",request.getParameter("root"));
        String username = request.getParameter("username");

        String action = request.getParameter("action");
        request.setAttribute("username",username);

        Integer noteId = WebUtil.toInteger(request.getParameter("id"));

        switch (action) {
            case "修改": {
                Note note = noteService.queryNoteByNoteId(noteId);
                request.setAttribute("note", note);
                List<Tag> tags = noteService.queryTagByNoteId(noteId);
                request.setAttribute("tags", tags);
                try {
                    request.getRequestDispatcher("/User/page/updateNote.jsp").forward(request, response);
                } catch (ServletException | IOException e) {
                    e.printStackTrace();
                }
                break;
            } case "申诉":{
                /*
                调用申诉方法：
                 */

                boolean check = noteService.appeal(noteId);
                if(check){
                    User manager = managerService.findManager(noteId);
                    managerService.addAppeal(manager.getUsername());
                    managerService.deleteOperation(noteId);
                    request.setAttribute("appealMsg","申诉成功！");
                }else {
                    request.setAttribute("appealMsg","服务器出问题了！");
                }
                List<Note> notes = noteService.queryNoteByUsername(username);
                CommentService commentService = new CommentServiceImpl();
                UserDao userDao = new UserDaoImpl();
                User user = userDao.queryUserByUserName(username);
                LikeActService likeActService = new LikeActServiceImpl();
                CollectService collectService = new CollectServiceImpl();
                List<Note> notes1 = likeActService.queryLikeNoteByUserId(user.getId());
                List<Note> notes2 = collectService.queryCollectNoteByUserId(user.getId());
                List<Note> notes3 = commentService.queryCommentNoteByUserId(user.getId());
                //点赞的笔记
                request.setAttribute("notes1", notes1);
                //收藏的笔记
                request.setAttribute("notes2", notes2);
                //评论的笔记
                request.setAttribute("notes3", notes3);
                List<Note> notes6 = noteService.checkPublishNote(notes);
                request.setAttribute("notes6",notes6);
                //还没有通过审核的笔记
                List<Note> notes4 = noteService.checkingNote(notes);
                request.setAttribute("notes4",notes4);
                //在审核中被驳回的笔记
                List<Note> notes5 = noteService.turnBackNote(notes);
                request.setAttribute("notes5",notes5);
                //被管理员删掉的笔记
                List<Note> notes7 = noteService.checkDeleteNote(notes);
                request.setAttribute("notes7",notes7);
                try {
                    request.getRequestDispatcher("/User/page/home.jsp").forward(request, response);
                } catch (ServletException | IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "确认修改标题": {
                String newTitle = request.getParameter("newTitle");
                boolean check = noteService.updateNoteTitle(newTitle, noteId);
                Note note = noteService.queryNoteByNoteId(noteId);
                request.setAttribute("note", note);
                if (check) {
                    request.setAttribute("updateMsg", "修改标题成功！等待审核中！");
                    managerService.changeNoteReleaseStatus(noteId);
                    managerService.deleteOperation(noteId);
                } else {
                    request.setAttribute("updateMsg", "标题不能为空哦！");
                }
                List<Tag> tags = noteService.queryTagByNoteId(noteId);
                request.setAttribute("tags", tags);
                try {
                    request.getRequestDispatcher("/User/page/updateNote.jsp").forward(request, response);
                } catch (ServletException | IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "确认修改内容": {
                String newMain = request.getParameter("newMain");
                boolean check = noteService.updateNoteMain(newMain, noteId);
                Note note = noteService.queryNoteByNoteId(noteId);
                if (check) {
                    request.setAttribute("updateMsg", "修改内容成功！等待审核中！");
                    managerService.changeNoteReleaseStatus(noteId);
                    managerService.deleteOperation(noteId);
                } else {
                    request.setAttribute("updateMsg", "内容不能为空哦！");
                }
                request.setAttribute("note", note);
                List<Tag> tags = noteService.queryTagByNoteId(noteId);
                request.setAttribute("tags", tags);
                try {
                    request.getRequestDispatcher("/User/page/updateNote.jsp").forward(request, response);
                } catch (ServletException | IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "删除": {
                boolean check = noteService.deleteNote(noteId);
                if (check) {
                    request.setAttribute("deleteMsg", "删除成功了！");
                } else {
                    request.setAttribute("deleteMsg", "由于某种不可抗力删除失败！");
                }
                List<Note> notes = noteService.queryNoteByUsername(username);
                CommentService commentService = new CommentServiceImpl();
                UserDao userDao = new UserDaoImpl();
                User user = userDao.queryUserByUserName(username);
                LikeActService likeActService = new LikeActServiceImpl();
                CollectService collectService = new CollectServiceImpl();
                List<Note> notes1 = likeActService.queryLikeNoteByUserId(user.getId());
                List<Note> notes2 = collectService.queryCollectNoteByUserId(user.getId());
                List<Note> notes3 = commentService.queryCommentNoteByUserId(user.getId());
                //点赞的笔记
                request.setAttribute("notes1", notes1);
                //收藏的笔记
                request.setAttribute("notes2", notes2);
                //评论的笔记
                request.setAttribute("notes3", notes3);
                List<Note> notes6 = noteService.checkPublishNote(notes);
                request.setAttribute("notes6",notes6);
                //还没有通过审核的笔记
                List<Note> notes4 = noteService.checkingNote(notes);
                request.setAttribute("notes4",notes4);
                //在审核中被驳回的笔记
                List<Note> notes5 = noteService.turnBackNote(notes);
                request.setAttribute("notes5",notes5);
                //被管理员删掉的笔记
                List<Note> notes7 = noteService.checkDeleteNote(notes);
                request.setAttribute("notes7",notes7);
                try {
                    request.getRequestDispatcher("/User/page/home.jsp").forward(request, response);
                } catch (ServletException | IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "确认添加" :{
                String[] tags = request.getParameterValues("tag");
                Tag tag=new Tag();
                List<Tag> tags1=new ArrayList<>();
                for (String tag1 : tags) {
                    tag.setTagMain(tag1);
                    tag.setNoteId(noteId);
                    tags1.add(tag);
                    tag=new Tag();
                }
                String addTagResult = noteService.addTag(tags1);
                request.setAttribute("addTagResult",addTagResult);
                List<Tag> tags2 = noteService.queryTagByNoteId(noteId);
                Note note = noteService.queryNoteByNoteId(noteId);
                request.setAttribute("note",note);
                request.setAttribute("title",note.getTitle());
                request.setAttribute("tags",tags2);
                try {
                    request.getRequestDispatcher("/User/page/updateNote.jsp").forward(request,response);
                } catch (ServletException | IOException e) {
                    e.printStackTrace();
                }
            }
        }
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
    public void publishNote(HttpServletRequest request, HttpServletResponse response) {
        NoteService noteService=new NoteServiceImpl();
        String action = request.getParameter("action");
        String root = request.getParameter("root");
        String username = request.getParameter("username");

        request.setAttribute("username",username);

        request.setAttribute("root",root);
        if(action.equals("发布笔记")){
            try {
                request.getRequestDispatcher("/User/page/publishNote.jsp").forward(request,response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }else if(action.equals("确认发布")){
            String title = request.getParameter("title");
            String main = request.getParameter("main");
            String zoomName = request.getParameter("zoomName");
            UserService userService=new UserServiceImpl();
            User user = userService.queryUserByUserName(username);
            Note note=new Note();
            note.setUserId(user.getId());
            note.setZoomName(zoomName);
            note.setMain(main);
            note.setTitle(title);
            String result = noteService.saveNote(note, zoomName);
            request.setAttribute("sendMsg",result);
            try {
                request.getRequestDispatcher("/User/page/publishNote.jsp").forward(request,response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }
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
        ManagerService managerService=new ManagerServiceImpl();
        managerService.deleteOperation(tags.get(0).getNoteId());
        managerService.changeNoteReleaseStatus(tags.get(0).getNoteId());
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
    public void uploadFile(HttpServletRequest request, HttpServletResponse response) {
        NoteService noteService=new NoteServiceImpl();
        ManagerService managerService=new ManagerServiceImpl();
        if(ServletFileUpload.isMultipartContent(request)){
            List<String> data=new ArrayList<>();
            //创建对应的实现类
            FileItemFactory fileItemFactory=new DiskFileItemFactory();
            //创建用于上传文件的数据类
            ServletFileUpload fileUpload=new ServletFileUpload(fileItemFactory);
            try {
                List<FileItem> fileItems = fileUpload.parseRequest(request);
                for (FileItem fileItem:fileItems){
                    if(fileItem.isFormField()){
                        //true为普通表单项
                        String string = fileItem.getString("UTF-8");
                        data.add(string);
                    }else {
                        String name = fileItem.getName();
                        String baseUrl="http://localhost:8080/nw/notebook/notePicture/"+name;
                        fileItem.write(new File("D:\\NWtest\\src\\main\\webapp\\notebook\\notePicture\\"+name));
                        /*
                        将该地址存到数据库里面
                         */
                        noteService.insertNotePicture(baseUrl,WebUtil.toInteger(data.get(0)));
                    }
                }
                Integer noteId=WebUtil.toInteger(data.get(0));
                String username=data.get(1);

                List<Note> notes = noteService.queryNoteByUsername(username);
                CommentService commentService = new CommentServiceImpl();
                UserDao userDao = new UserDaoImpl();
                User user = userDao.queryUserByUserName(username);
                LikeActService likeActService = new LikeActServiceImpl();
                CollectService collectService = new CollectServiceImpl();
                List<Note> notes1 = likeActService.queryLikeNoteByUserId(user.getId());
                List<Note> notes2 = collectService.queryCollectNoteByUserId(user.getId());
                List<Note> notes3 = commentService.queryCommentNoteByUserId(user.getId());
                //点赞的笔记
                request.setAttribute("notes1", notes1);
                //收藏的笔记
                request.setAttribute("notes2", notes2);
                //评论的笔记
                request.setAttribute("notes3", notes3);
                List<Note> notes6 = noteService.checkPublishNote(notes);
                request.setAttribute("notes6",notes6);
                //还没有通过审核的笔记
                List<Note> notes4 = noteService.checkingNote(notes);
                request.setAttribute("notes4",notes4);
                //在审核中被驳回的笔记
                List<Note> notes5 = noteService.turnBackNote(notes);
                request.setAttribute("notes5",notes5);
                //被管理员删掉的笔记
                List<Note> notes7 = noteService.checkDeleteNote(notes);
                managerService.deleteOperation(noteId);
                request.setAttribute("notes7",notes7);
                request.setAttribute("uploadMsg", "上传文件成功！等待审核中！");
                managerService.changeNoteReleaseStatus(noteId);
                request.setAttribute("username",username);
                request.setAttribute("root",data.get(2));
                request.getRequestDispatcher("/User/page/home.jsp").forward(request,response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void insertNotePicture(String url, Integer noteId) {
        NoteDao noteDao=new NoteDaoImpl();
        noteDao.insertPictureUrl(url, noteId);
    }



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
    public void addBrowser(Integer noteId) {
        NoteDao noteDao=new NoteDaoImpl();
        noteDao.addBrowse(noteId);
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
                    Integer n2=o2.getLikeCount()*3+o2.getBrowse()*7;
                    Integer n1=o1.getLikeCount()*3+o1.getBrowse()*7;
                    if(n1.equals(n2)){
                        return o1.getId()-o2.getId();
                    }else {
                        return n2-n1;
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

        String action = request.getParameter("action");
        String root = request.getParameter("root");
        request.setAttribute("username",username);

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

    @Override
    public List<Note> checkDeleteNote(List<Note> notes) {
        List<Note> notes1=new ArrayList<>();
        for (Note note:notes){
            if(note.getReleaseStatus().equals("-2")){
                notes1.add(note);
            }
        }
        return notes1;
    }

    @Override
    public boolean appeal(Integer noteId) {
        NoteService noteService=new NoteServiceImpl();
        Note note = noteService.queryNoteByNoteId(noteId);
        //判断笔记状态：如果是驳回状态下申诉，直接让笔记进入待审核状态
        //：如果是删除状态下申诉，申诉后删除笔记。
        if(note.getReleaseStatus().equals("-1")){
            ManagerDao managerDao=new ManagerDaoImpl();
            return managerDao.setNoteReleaseStatus(noteId,"0");
        }if (note.getReleaseStatus().equals("-2")){
            return noteService.deleteNote(noteId);
        }
        return false;
    }

    @Override
    public String report(Report report) {
        NoteDao noteDao=new NoteDaoImpl();
        if(report.getMain().equals("")){
            return "请填写举报理由!";
        }else {
            boolean check = noteDao.queryReported(report.getUsername(), report.getNoteId());
            if(check){
                return "您已经举报过了!请耐心等待管理员处理！";
            }
            else {
                boolean check1 = noteDao.insertReportMsg(report);
                if(check1){
                    return "举报成功！请耐心等待管理员处理";
                }else {
                    return "抱歉，服务器出问题了";
                }
            }
        }
    }

    @Override
    public void detail(HttpServletRequest request, HttpServletResponse response) {
        LikeActService likeActService=new LikeActServiceImpl();
        NoteService noteService=new NoteServiceImpl();
        CommentService commentService=new CommentServiceImpl();
        CollectService collectService=new CollectServiceImpl();
        String username = request.getParameter("username");

        UserDao userDao=new UserDaoImpl();
        User user = userDao.queryUserByUserName(username);
        request.setAttribute("root",user.getRoot());
        Integer noteId = WebUtil.toInteger(request.getParameter("noteId"));
        boolean check = likeActService.judgeLikeOrNot(noteId, user.getId());
        List<Comment> comments = commentService.queryCommentByNoteId(noteId);
        List<Tag> tags = noteService.queryTagByNoteId(noteId);
        request.setAttribute("comments",comments);
        Note note = noteService.queryNoteByNoteId(noteId);
        boolean result = collectService.judgeCollectOrNot(noteId, user.getId());
        request.setAttribute("result",result);
        request.setAttribute("username", username);

        request.setAttribute("note", note);
        request.setAttribute("tags",tags);
        request.setAttribute("check",check);
        /*
        历史记录：将查看的笔记id以及userid送过去，userid用于辨别这个笔记是谁查看过的
         */
        /*
        浏览数加一
         */
        noteService.addBrowser(noteId);
        Cookie[] cookies = request.getCookies();
        Cookie cookieByName = WebUtil.findCookieByName(cookies, user.getId().toString());
        if(cookieByName==null){
            Cookie cookie=new Cookie(user.getId().toString(),request.getParameter("noteId"));
            response.addCookie(cookie);
        }else {
            String noteId1 = request.getParameter("noteId");
            //获取当前记录
            String Ids=cookieByName.getValue();
            //去除分隔符-
            String[] split = Ids.split("-");
            //将其转化为数组进行操作
            LinkedList<String> list=new LinkedList<>(Arrays.asList(split));
            //如果新增笔记已经在别的地方已经浏览过了
            if(list.contains(noteId1)){
                //将原纪录去除，并将新纪录添加到第一个
                list.remove(noteId1);
            }
            list.addFirst(noteId1);
            //将数组取出加上分隔符保存进cookie
            StringBuilder stringBuilder=new StringBuilder();
            for (String string:list){
                stringBuilder.append(string).append("-");
            }
            String newIds = stringBuilder.substring(0,stringBuilder.length()-1);
            Cookie cookie=new Cookie(user.getId().toString(),newIds);
            response.addCookie(cookie);
        }
        try {
            request.getRequestDispatcher("/notebook/notedetail.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

}
