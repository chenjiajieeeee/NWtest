package com.cjj.www.service;

import com.cjj.www.controller.NoteServlet;
import com.cjj.www.dao.UserDao;
import com.cjj.www.dao.UserDaoImpl;
import com.cjj.www.pojo.Note;
import com.cjj.www.pojo.User;
import com.cjj.www.util.CodeUtil;
import com.cjj.www.util.Encryption;
import com.cjj.www.util.MailUtil;
import com.cjj.www.util.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService{
    @Override
    public String userLogin(String username, String password) {
        UserDao userDao=new UserDaoImpl();
        User user = userDao.queryUserByUserName(username);
        if(userDao.check(username, password)){
            if(user.getActivateStatus().equals("0")){
                return "该账号未激活，请先激活！";
            }else {
                return "登录成功！";
            }
        }else {
            return "账号或密码错误";
        }
    }

    @Override
    public void userLogin(HttpServletRequest request, HttpServletResponse response) {
        UserService userService=new UserServiceImpl();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Encryption encryption=new Encryption();
        String salt = userService.queryUserByUserName(username).getSalt();
        String newPassword = encryption.encryptMD5(password,salt);
        String check = userService.userLogin(username, newPassword);
        switch (check) {
            case "登录成功！": {
            /*
              登录成功后在首页显示所有的笔记
              把全部图书保存在request域中
              将请求转发到首页所在的页面中，遍历显示所有的笔记！
              使用jstl来写会更加的简洁
             */
                PagingService pagingService = new PagingServiceImpl();
                HttpServletRequest req = pagingService.paging(request);
                User user = userService.queryUserByUserName(username);
                req.setAttribute("root", user.getRoot());
                req.setAttribute("username", username);
                try {
                    req.getRequestDispatcher("/notebook/homepage.jsp").forward(request, response);
                } catch (ServletException | IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "账号或密码错误":
                request.setAttribute("msg", "Wrong username or password!");
                request.setAttribute("username", username);
                //请求转发
                try {
                    request.getRequestDispatcher("/User/page/login.jsp").forward(request, response);
                } catch (ServletException | IOException e) {
                    e.printStackTrace();
                }
                break;
            case "该账号未激活，请先激活！": {
                request.setAttribute("username", username);
                request.setAttribute("error", check);
                User user = userService.queryUserByUserName(username);
                new Thread(new MailUtil(user.getMail(), user.getCode())).start();
                try {
                    request.getRequestDispatcher("/User/page/confirm.jsp").forward(request, response);
                } catch (ServletException | IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    @Override
    public String userRegister(String username, String password,String mail) {
        if (!mail.matches("^\\w+@(\\w+\\.)+\\w+$")) {
            return "邮箱格式不正确！";
        }
        UserDao ud = new UserDaoImpl();
        User user1 = ud.queryUserByUserName(username);
        if (user1.getUsername() == null) {
            //生成随机激活码
            String code = CodeUtil.generateUniqueCode();
            User user = new User();
            user.setUsername(username);
            Encryption encryption=new Encryption();
            String salt = encryption.salt();
            String newPassword=encryption.encryptMD5(password,salt);
            user.setPassword(newPassword);
            user.setMail(mail);
            user.setCode(code);
            if(ud.saveUser(user)){
                new Thread(new MailUtil(mail,code)).start();
                return "正在跳转页面";
            }else {
                return "服务器出问题了，该死！";
            }
        } else {
            return "用户名已存在！";
        }
    }

    @Override
    public void userRegister(HttpServletRequest request, HttpServletResponse response) {
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String mail = request.getParameter("mail");
        //调用业务层对应的方法
        UserService userService=new UserServiceImpl();
        String result=userService.userRegister(username,password,mail);
        //处理结果跳转相应页面
        switch (result) {
            case "用户名已存在！":
                request.setAttribute("msg", "The user name already exists!");
                try {
                    request.getRequestDispatcher("/User/page/register.jsp").forward(request, response);
                } catch (ServletException | IOException e) {
                    e.printStackTrace();
                }
                break;
            case "正在跳转页面":
                request.setAttribute("username", username);
                try {
                    request.getRequestDispatcher("/User/page/confirm.jsp").forward(request, response);
                } catch (ServletException | IOException e) {
                    e.printStackTrace();
                }
                break;
            case "邮箱格式不正确！":
                request.setAttribute("msg", "mail format not correct!");
                try {
                    request.getRequestDispatcher("/User/page/register.jsp").forward(request, response);
                } catch (ServletException | IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    public void confirm(HttpServletRequest request, HttpServletResponse response) {
        UserService userService=new UserServiceImpl();
        String code = request.getParameter("code");
        String username = request.getParameter("username");
        if(userService.queryUserByUserName(username).getCode().equals(code)){
            request.setAttribute("msg","注册成功！");
            //改变用户激活状态
            userService.activateUser(username);
            try {
                request.getRequestDispatcher("/User/page/login.jsp").forward(request,response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }else {
            request.setAttribute("error","验证码错误！，请重新输入");
            try {
                request.getRequestDispatcher("/User/page/confirm.jsp").forward(request,response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public User queryUserByUserName(String username) {
        UserDao userDao=new UserDaoImpl();
        return userDao.queryUserByUserName(username);

    }

    @Override
    public User queryUserByUserId(Integer userId) {
        UserDao userDao=new UserDaoImpl();
        return userDao.queryUserByUserId(userId);
    }

    @Override
    public void addFriend(HttpServletRequest request, HttpServletResponse response) {
        UserService userService=new UserServiceImpl();
        String username = request.getParameter("username");
        Integer friendId = WebUtil.toInteger(request.getParameter("friendId"));
        User user = userService.queryUserByUserName(username);
        String result = userService.addFriend(user.getId(), friendId);
        request.setAttribute("addMsg",result);
        NoteServlet noteServlet=new NoteServlet();
        try {
            noteServlet.detail(request,response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String addFriend(Integer userId, Integer friendId) {
        if(userId.equals(friendId)){
            return "不要添加自己为好友！";
        }else {
            UserDao userDao=new UserDaoImpl();
            List<Integer> friendsId = userDao.queryFriend(userId);
            for (Integer id:friendsId){
                if(id.equals(friendId)){
                    return "已经添加过这个用户了！";
                }
            }
            userDao.addFriend(userId,friendId);
            return "添加好友成功！";
        }
    }

    @Override
    public List<User> viewFriend(Integer userId) {
        UserDao userDao=new UserDaoImpl();
        //找到好友的id
        List<Integer> friends = userDao.queryFriend(userId);
        //根据好友id查询好友信息如好友的名字
        List<User> users=new ArrayList<>();
        for (Integer id:friends){
            users.add(userDao.queryUserByUserId(id));
        }
        return users;
    }

    @Override
    public List<User> viewFans(Integer userId) {
        UserDao userDao=new UserDaoImpl();
        List<Integer> fans = userDao.queryFans(userId);
        List<User> users=new ArrayList<>();
        for (Integer id:fans){
            users.add(userDao.queryUserByUserId(id));
        }
        return users;
    }

    @Override
    public boolean activateUser(String username) {
        UserDao userDao=new UserDaoImpl();
        return userDao.activateUser(username);
    }

    @Override
    public void history(HttpServletRequest request, HttpServletResponse response) {
        UserService userService=new UserServiceImpl();
        NoteService noteService=new NoteServiceImpl();
        String root = request.getParameter("root");
        String username = request.getParameter("username");
        User user = userService.queryUserByUserName(username);
        Cookie[] cookies = request.getCookies();
        Cookie cookieByName = WebUtil.findCookieByName(cookies, user.getId().toString());
        List<Note> notes=new ArrayList<>();
        if(cookieByName==null){
            request.setAttribute("historyMsg","历史记录为空！");
        }else {
            String value = cookieByName.getValue();
            String[] split = value.split("-");
            List<Integer> ids=new ArrayList<>();
            for (String id: split) {
                ids.add(WebUtil.toInteger(id));
            }
            for (Integer id:ids){
                Note note = noteService.queryNoteByNoteId(id);
                if(note!=null){
                    notes.add(note);
                }
            }
        }
        request.setAttribute("username",username);
        request.setAttribute("notes",notes);
        request.setAttribute("root",root);
        try {
            request.getRequestDispatcher("/notebook/history.jsp").forward(request,response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
