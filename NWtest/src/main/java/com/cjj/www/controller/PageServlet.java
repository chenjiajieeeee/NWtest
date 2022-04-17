package com.cjj.www.controller;

import com.cjj.www.dao.UserDao;
import com.cjj.www.dao.UserDaoImpl;
import com.cjj.www.pojo.Note;


import com.cjj.www.pojo.User;
import com.cjj.www.service.*;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.List;

@WebServlet("/page/*")
public class PageServlet extends BaseServlet {
        UserDao userDao=new UserDaoImpl();
        CollectService collectService=new CollectServiceImpl();
        CommentService commentService=new CommentServiceImpl();
        LikeActService likeActService=new LikeActServiceImpl();
        private final NoteService noteService=new NoteServiceImpl();
        private final PagingService pagingService=new PagingServiceImpl();
        public void findPage(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
            String action = request.getParameter("action");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            if(action.equals("游戏区")||action.equals("美食区")||action.equals("学习区")||action.equals("动漫区")||action.equals("科技区")){
                HttpServletRequest req = pagingService.paging(request,action);
                UserService userService=new UserServiceImpl();
                User user = userService.queryUserByUserName(username);
                req.setAttribute("root",user.getRoot());
                req.setAttribute("zoomName",action);
                req.setAttribute("username",username);
                req.setAttribute("password",password);
                req.getRequestDispatcher("/notebook/zoom.jsp").forward(request,response);
            }else if(action.equals("个人主页")){
                request.setAttribute("username",username);
                request.setAttribute("password",password);
                List<Note> notes = noteService.queryNoteByUsername(username);
                /*
                对笔记分类
                 */
                //发布了的笔记
                List<Note> notes6 = noteService.checkPublishNote(notes);
                request.setAttribute("notes6",notes6);
                //还没有通过审核的笔记
                List<Note> notes4 = noteService.checkingNote(notes);
                request.setAttribute("notes4",notes4);
                //在审核中被驳回的笔记
                List<Note> notes5 = noteService.turnBackNote(notes);
                request.setAttribute("notes5",notes5);
                User user = userDao.queryUserByUserName(username);
                List<Note> notes1 = likeActService.queryLikeNoteByUserId(user.getId());
                List<Note> notes2 = collectService.queryCollectNoteByUserId(user.getId());
                List<Note> notes3 = commentService.queryCommentNoteByUserId(user.getId());
                //点赞的笔记
                request.setAttribute("notes1",notes1);
                //收藏的笔记
                request.setAttribute("notes2",notes2);
                //评论的笔记
                request.setAttribute("notes3",notes3);
                request.setAttribute("root",user.getRoot());
                request.getRequestDispatcher("/User/page/home.jsp").forward(request,response);
            }
        }
        public void sort(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
            HttpServletRequest req = noteService.sort(request);
            req.getRequestDispatcher("/notebook/sort.jsp").forward(request,response);
        }
}
