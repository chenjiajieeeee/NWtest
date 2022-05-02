package com.cjj.www.service;

import com.cjj.www.dao.*;
import com.cjj.www.pojo.*;
import com.cjj.www.util.Encryption;
import com.cjj.www.util.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserRoleServiceImpl implements UserRoleService{
    @Override
    public void updateUserInformation(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("root",request.getParameter("root"));
        String username = request.getParameter("username");

        String action = request.getParameter("action");
        request.setAttribute("username", username);

        switch (action) {
            case "修改个人信息":
                try {
                    request.getRequestDispatcher("/User/page/updatepage.jsp").forward(request, response);
                } catch (ServletException | IOException e) {
                    e.printStackTrace();
                }
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
                try {
                    request.getRequestDispatcher("/User/page/updatepage.jsp").forward(request, response);
                } catch (ServletException | IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "确认更改密码": {
                String oldPassword = request.getParameter("oldPassword");
                String newPassword = request.getParameter("newPassword");
                //转为哈希密码
                UserRoleService userRoleService = new UserRoleServiceImpl();
                boolean check = userRoleService.updatePassword(username, oldPassword, newPassword);
                if (check) {
                    request.setAttribute("updateMsg", "密码修改成功！请重新登录！");
                    try {
                        request.getRequestDispatcher("/User/page/login.jsp").forward(request, response);
                    } catch (ServletException | IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    request.setAttribute("errorMsg", "确认旧密码正确以及新密码不能为空！");

                    try {
                        request.getRequestDispatcher("/User/page/updatepage.jsp").forward(request, response);
                    } catch (ServletException | IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            }
        }
    }

    @Override
    public String updateUsername(String oldName,String newName) {
        UserRoleDao userRoleDao=new UserRoleDaoImpl();
        if(newName.equals("")){
            return "用户名不能为空!";
        }
        else {
            UserDao userDao=new UserDaoImpl();
            User user = userDao.queryUserByUserName(newName);
            if(user.getUsername()==null){
                userRoleDao.updateUserName(oldName,newName);
                return "修改用户名成功!";
            }else {
                return "该用户名已经存在了!";
            }
        }
    }

    @Override
    public boolean updatePassword(String username,String oldPassword, String newPassword) {
        if(newPassword.equals("")){
            return false;
        }
        else {
            UserRoleDao userRoleDao = new UserRoleDaoImpl();
            Encryption encryption=new Encryption();
            UserDao userDao=new UserDaoImpl();
            String salt = userDao.queryUserByUserName(username).getSalt();
            String OldPassword = encryption.encryptMD5(oldPassword,salt);
            String newSalt = encryption.salt();
            String NewPassword = encryption.encryptMD5(newPassword,newSalt);
            return userRoleDao.updatePassword(username, OldPassword, NewPassword,newSalt);
        }
    }

    @Override
    public boolean addFriend(Integer userId, Integer friendId) {
        UserDao userDao=new UserDaoImpl();
        return userDao.addFriend(userId,friendId);
    }

    @Override
    public void chat(HttpServletRequest request, HttpServletResponse response) {
        UserService userService=new UserServiceImpl();
        String username = request.getParameter("username");

        String root = request.getParameter("root");
        //先把关注了的用户找出来
        User user = userService.queryUserByUserName(username);
        List<User> users = userService.viewFriend(user.getId());
        //再把他的粉丝找出来
        List<User> users1 = userService.viewFans(user.getId());
        request.setAttribute("users",users);
        request.setAttribute("username",username);

        request.setAttribute("root",root);
        request.setAttribute("users1",users1);
        //跳转到聊天页面
        try {
            request.getRequestDispatcher("/User/page/chat.jsp").forward(request,response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void report(HttpServletRequest request, HttpServletResponse response) {
        NoteService noteService=new NoteServiceImpl();
        CommentService commentService=new CommentServiceImpl();
        CollectService collectService=new CollectServiceImpl();
        LikeActService likeActService=new LikeActServiceImpl();
        //接收基本数据
        String username = request.getParameter("username");

        String root = request.getParameter("root");
        String action = request.getParameter("action");
        Integer noteId = WebUtil.toInteger(request.getParameter("noteId"));
        request.setAttribute("username",username);

        request.setAttribute("root",root);
        request.setAttribute("action",action);
        if("提交".equals(action)) {
            //接收举报信息
            String main = request.getParameter("main");
            Note note = noteService.queryNoteByNoteId(noteId);
            //封装成举报对象
            Report report = new Report();
            report.setNoteId(noteId);
            report.setMain(main);
            report.setUsername(username);
            report.setZoomName(note.getZoomName());
            String result = noteService.report(report);
            if(result.equals("举报成功！请耐心等待管理员处理")||result.equals("您已经举报过了!请耐心等待管理员处理！")){
                request.setAttribute("reportMsg",result);
                UserDao userDao=new UserDaoImpl();
                User user = userDao.queryUserByUserName(username);
                boolean check1 = likeActService.judgeLikeOrNot(noteId, user.getId());
                request.setAttribute("check",check1);
                request.setAttribute("username",username);

                request.setAttribute("note",note);
                List<Tag> tags = noteService.queryTagByNoteId(noteId);
                List<Comment> comments = commentService.queryCommentByNoteId(noteId);
                request.setAttribute("comments",comments);
                request.setAttribute("tags",tags);
                boolean result1 = collectService.collectAct(noteId, user.getId());
                request.setAttribute("result",result1);
                try {
                    request.getRequestDispatcher("/notebook/notedetail.jsp").forward(request,response);
                } catch (ServletException | IOException e) {
                    e.printStackTrace();
                }
            }else if (result.equals("请填写举报理由!")||result.equals("抱歉，服务器出问题了")){
                request.setAttribute("reportMsg",result);
                request.setAttribute("note",noteService.queryNoteByNoteId(noteId));
                try {
                    request.getRequestDispatcher("/notebook/report.jsp").forward(request,response);
                } catch (ServletException | IOException e) {
                    e.printStackTrace();
                }
            }
        }else if ("举报".equals(action)){
            request.setAttribute("note",noteService.queryNoteByNoteId(noteId));
            try {
                request.getRequestDispatcher("/notebook/report.jsp").forward(request,response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }
    }

}
