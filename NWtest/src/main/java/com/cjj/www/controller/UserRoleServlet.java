package com.cjj.www.controller;

import com.cjj.www.dao.UserDao;
import com.cjj.www.dao.UserDaoImpl;
import com.cjj.www.pojo.Note;
import com.cjj.www.pojo.Tag;
import com.cjj.www.pojo.User;
import com.cjj.www.service.*;
import com.cjj.www.util.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/UserRoleServlet")
public class UserRoleServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*
        执行完操作后统一重新登录
         */
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String action=req.getParameter("action");
        String username = req.getParameter("username");
        String password=req.getParameter("password");
        req.setAttribute("username",username);
        req.setAttribute("password",password);
        if("修改个人信息".equals(action)){
            req.getRequestDispatcher("/User/page/updatepage.jsp").forward(req,resp);
        }
        else if("确认更改用户名".equals(action)){
            String oldName=req.getParameter("oldName");
            String newName=req.getParameter("newName");
            UserRoleService userRoleService=new UserRoleServiceImpl();
            String updateMsg = userRoleService.updateUsername(oldName, newName);
            if("用户名不能为空!".equals(updateMsg)){
                req.setAttribute("username",oldName);
            }else if("该用户名已经存在了!".equals(updateMsg)){
                req.setAttribute("username",oldName);
            }else if("修改用户名成功!".equals(updateMsg)){
                req.setAttribute("username",newName);
            }req.setAttribute("updateMsg",updateMsg);
            req.getRequestDispatcher("/User/page/updatepage.jsp").forward(req,resp);
        }
        else if("确认更改密码".equals(action)){
            String oldPassword=req.getParameter("oldPassword");
            String newPassword=req.getParameter("newPassword");
            UserRoleService userRoleService=new UserRoleServiceImpl();
            boolean check = userRoleService.updatePassword(username, oldPassword, newPassword);
            if(check){
                req.setAttribute("updateMsg","密码修改成功！请重新登录！");
                req.getRequestDispatcher("/User/page/login.jsp").forward(req,resp);
            }
            else {
                req.setAttribute("errorMsg","确认旧密码正确以及新密码不能为空！");
                req.setAttribute("password",password);
                req.getRequestDispatcher("/User/page/updatepage.jsp").forward(req,resp);
            }

        }
        else if("修改".equals(action)){
            String title=req.getParameter("title");
            String zoom=req.getParameter("zoom");
            String main=req.getParameter("main");
            String id=req.getParameter("id");
            Integer noteId = WebUtil.toInteger(id);
            NoteService noteService=new NoteServiceImpl();
            List<Tag> tags = noteService.queryTagByNoteId(noteId);
            req.setAttribute("tags",tags);
            req.setAttribute("title",title);
            req.setAttribute("zoom",zoom);
            req.setAttribute("main",main);
            req.setAttribute("id",id);
            String url=noteService.queryNoteByNoteId(noteId).getNotePictureUrl();
            req.setAttribute("url",url);
            req.getRequestDispatcher("/User/page/updateNote.jsp").forward(req,resp);
        }
        else if("确认修改标题".equals(action)){
            Integer id = WebUtil.toInteger(req.getParameter("id"));
            String newTitle=req.getParameter("newTitle");
            String oldTitle=req.getParameter("title");
            String main=req.getParameter("main");
            String zoom=req.getParameter("zoom");
            NoteService noteService=new NoteServiceImpl();
            Note note = noteService.queryNoteByNoteId(id);
            boolean check = noteService.updateNoteTitle(newTitle, id);
            req.setAttribute("main",main);
            req.setAttribute("zoom",zoom);
            req.setAttribute("id",id);
            if(check){
                req.setAttribute("updateMsg","修改标题成功了！");
                req.setAttribute("title",newTitle);
            }
            else {
                req.setAttribute("updateMsg","标题不能为空哦！");
                req.setAttribute("title",oldTitle);
            }
            List<Tag> tags = noteService.queryTagByNoteId(id);
            req.setAttribute("tags",tags);
            req.setAttribute("url",note.getNotePictureUrl());
            req.getRequestDispatcher("/User/page/updateNote.jsp").forward(req,resp);
        }
        else if("确认修改内容".equals(action)){
            Integer id = WebUtil.toInteger(req.getParameter("id"));
            String title=req.getParameter("title");
            String oldMain=req.getParameter("oldMain");
            String newMain=req.getParameter("newMain");
            String zoom=req.getParameter("zoom");
            NoteService noteService=new NoteServiceImpl();
            boolean check = noteService.updateNoteMain(newMain, id);
            req.setAttribute("title",title);
            req.setAttribute("zoom",zoom);
            req.setAttribute("id",id);
            if(check){
                req.setAttribute("updateMsg","内容已经修改了！");
                req.setAttribute("main",newMain);
            }
            else {
                req.setAttribute("updateMsg","内容不能为空哦！");
                req.setAttribute("main",oldMain);
            }
            List<Tag> tags = noteService.queryTagByNoteId(id);
            req.setAttribute("tags",tags);
            Note note = noteService.queryNoteByNoteId(id);
            req.setAttribute("url",note.getNotePictureUrl());
            req.getRequestDispatcher("/User/page/updateNote.jsp").forward(req,resp);
        }else if("删除".equals(action)){
            Integer id = WebUtil.toInteger(req.getParameter("id"));
            NoteService noteService=new NoteServiceImpl();
            boolean check = noteService.deleteNote(id);
            if(check){
                req.setAttribute("deleteMsg","删除成功了！");
            }
            else {
                req.setAttribute("deleteMsg","由于某种不可抗力删除失败！");
            }
            List<Note> notes4 = noteService.queryNoteByUsername(username);
            req.setAttribute("notes",notes4);
            CommentService commentService=new CommentServiceImpl();
            User user=new User();
            UserDao userDao=new UserDaoImpl();
            user=userDao.queryUserByUserName(username);
            List<Note> notes1 = commentService.queryCommentNoteByUserId(user.getId());
            req.setAttribute("notes1",notes1);
            LikeActService likeActService=new LikeActServiceImpl();
            List<Note> notes2 = likeActService.queryLikeNoteByUserId(user.getId());
            req.setAttribute("notes2",notes2);
            CollectService collectService=new CollectServiceImpl();
            List<Note> notes3 = collectService.queryCollectNoteByUserId(user.getId());
            req.setAttribute("notes3",notes3);
            req.getRequestDispatcher("/User/page/home.jsp").forward(req,resp);
        }else if("发布笔记".equals(action)){
            req.getRequestDispatcher("/User/page/publishNote.jsp").forward(req,resp);
        }else if("确认发布".equals(action)){
            String title=req.getParameter("title");
            String main=req.getParameter("main");
            String zoomName=req.getParameter("zoomName");
            UserDao userDao=new UserDaoImpl();
            User user=userDao.queryUserByUserName(username);
            NoteService noteService=new NoteServiceImpl();
            Note note=new Note();
            note.setTitle(title);
            note.setMain(main);
            note.setZoomName(zoomName);
            note.setUserId(user.getId());
            String result = noteService.saveNote(note, zoomName);
            req.setAttribute("sendMsg",result);
            req.getRequestDispatcher("/User/page/publishNote.jsp").forward(req,resp);
        }else if("确认添加".equals(action)){
             /*
             接收字符串数组
              */
            Integer noteId = WebUtil.toInteger(req.getParameter("id"));
            String[] tags = req.getParameterValues("tag");
            NoteService noteService=new NoteServiceImpl();
            Tag tag=new Tag();
            List<Tag> tags1=new ArrayList<>();
            for (String tag1 : tags) {
                tag.setTagMain(tag1);
                tag.setNoteId(noteId);
                tags1.add(tag);
                tag=new Tag();
            }
            String addTagResult = noteService.addTag(tags1);
            req.setAttribute("addTagResult",addTagResult);
            List<Tag> tags2 = noteService.queryTagByNoteId(noteId);
            Note note = noteService.queryNoteByNoteId(noteId);
            req.setAttribute("title",note.getTitle());
            req.setAttribute("zoom",note.getZoomName());
            req.setAttribute("main",note.getMain());
            req.setAttribute("tags",tags2);
            req.setAttribute("id",noteId);
            req.setAttribute("url",note.getNotePictureUrl());
            req.getRequestDispatcher("/User/page/updateNote.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
    /*
     * 由于传过来的id是字符串，所以我要将他变成整型
     * 思路就是第一个字符串乘以10的零次方加第二个字符串乘以10的1次方，刚刚好对上
     */
}
