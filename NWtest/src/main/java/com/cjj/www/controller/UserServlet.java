package com.cjj.www.controller;


import com.cjj.www.dao.UserDao;
import com.cjj.www.dao.UserDaoImpl;
import com.cjj.www.pojo.Note;
import com.cjj.www.pojo.Tag;

import com.cjj.www.pojo.User;
import com.cjj.www.service.*;
import com.cjj.www.util.WebUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


@WebServlet("/user/*")
public class UserServlet extends BaseServlet{
    private final UserService userService=new UserServiceImpl();
    NoteService noteService=new NoteServiceImpl();
    ManagerService managerService=new ManagerServiceImpl();
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean check = userService.userLogin(username, password);
        if (check){
            /*
              登录成功后在首页显示所有的笔记
              把全部图书保存在request域中
              将请求转发到首页所在的页面中，遍历显示所有的笔记！
              使用jstl来写会更加的简洁
             */
            PagingService pagingService=new PagingServiceImpl();
            HttpServletRequest req = pagingService.paging(request);
            User user = userService.queryUserByUserName(username);
            req.setAttribute("root",user.getRoot());
            req.setAttribute("username",username);
            req.setAttribute("password",password);
            req.getRequestDispatcher("/notebook/homepage.jsp").forward(request,response);
        }
        else {
            request.setAttribute("msg","Wrong username or password!");
            request.setAttribute("username",username);
            //请求转发
            request.getRequestDispatcher("/User/page/login.jsp").forward(request,response);
        }
    }
    public void register(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        //调用业务层对应的方法
        UserService userService=new UserServiceImpl();
        boolean result=userService.userRegister(username,password);
        //处理结果跳转相应页面
        if(result){
            request.setAttribute("msg","The user name already exists!");
            request.getRequestDispatcher("/User/page/register.jsp").forward(request,response);
        }
        else {
            request.setAttribute("registerMsg","注册成功！");
            request.getRequestDispatcher("/User/page/login.jsp").forward(request,response);
        }
    }
    public void updateUserInformation(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("root",request.getParameter("root"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String action = request.getParameter("action");
        request.setAttribute("username", username);
        request.setAttribute("password", password);
        switch (action) {
            case "修改个人信息":
                request.getRequestDispatcher("/User/page/updatepage.jsp").forward(request, response);
                break;
            case "确认更改用户名": {
                String oldName = request.getParameter("oldName");
                String newName = request.getParameter("newName");
                UserRoleService userRoleService = new UserRoleServiceImpl();
                String updateMsg = userRoleService.updateUsername(oldName, newName);
                if ("用户名不能为空!".equals(updateMsg)) {
                    request.setAttribute("username", oldName);
                } else if ("该用户名已经存在了!".equals(updateMsg)) {
                    request.setAttribute("username", oldName);
                } else if ("修改用户名成功!".equals(updateMsg)) {
                    request.setAttribute("username", newName);
                }
                request.setAttribute("updateMsg", updateMsg);
                request.getRequestDispatcher("/User/page/updatepage.jsp").forward(request, response);
                break;
            }
            case "确认更改密码": {
                String oldPassword = request.getParameter("oldPassword");
                String newPassword = request.getParameter("newPassword");
                UserRoleService userRoleService = new UserRoleServiceImpl();
                boolean check = userRoleService.updatePassword(username, oldPassword, newPassword);
                if (check) {
                    request.setAttribute("updateMsg", "密码修改成功！请重新登录！");
                    request.getRequestDispatcher("/User/page/login.jsp").forward(request, response);
                } else {
                    request.setAttribute("errorMsg", "确认旧密码正确以及新密码不能为空！");
                    request.setAttribute("password", password);
                    request.getRequestDispatcher("/User/page/updatepage.jsp").forward(request, response);
                }
                break;
            }
        }
    }
    public void updateNote(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("root",request.getParameter("root"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String action = request.getParameter("action");
        request.setAttribute("username",username);
        request.setAttribute("password",password);
        Integer noteId = WebUtil.toInteger(request.getParameter("id"));

        switch (action) {
            case "修改": {
                Note note = noteService.queryNoteByNoteId(noteId);
                request.setAttribute("note", note);
                List<Tag> tags = noteService.queryTagByNoteId(noteId);
                request.setAttribute("tags", tags);
                request.getRequestDispatcher("/User/page/updateNote.jsp").forward(request, response);
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
                request.getRequestDispatcher("/User/page/home.jsp").forward(request, response);
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
                request.getRequestDispatcher("/User/page/updateNote.jsp").forward(request, response);
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
                request.getRequestDispatcher("/User/page/updateNote.jsp").forward(request, response);
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
                request.getRequestDispatcher("/User/page/home.jsp").forward(request, response);
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
                request.getRequestDispatcher("/User/page/updateNote.jsp").forward(request,response);
            }
        }
    }
    public void publishNote(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String root = request.getParameter("root");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        request.setAttribute("username",username);
        request.setAttribute("password",password);
        request.setAttribute("root",root);
        if(action.equals("发布笔记")){
            request.getRequestDispatcher("/User/page/publishNote.jsp").forward(request,response);
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
            request.getRequestDispatcher("/User/page/publishNote.jsp").forward(request,response);
        }
    }
    public void fileUpLoad(HttpServletRequest request,HttpServletResponse response){
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
                        NoteService noteService=new NoteServiceImpl();
                        noteService.insertNotePicture(baseUrl,WebUtil.toInteger(data.get(0)));
                    }
                }
                Integer noteId=WebUtil.toInteger(data.get(0));
                String username=data.get(1);
                String password=data.get(2);
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
                request.setAttribute("password",password);
                request.setAttribute("root",data.get(3));
                request.getRequestDispatcher("/User/page/home.jsp").forward(request,response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
