package com.cjj.www.service;

import com.cjj.www.pojo.Note;
import com.cjj.www.pojo.Paging;
import com.cjj.www.pojo.User;
import com.cjj.www.util.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class PagingServiceImpl implements PagingService{
    NoteService noteService=new NoteServiceImpl();
    Paging paging=new Paging();

    @Override
    public HttpServletRequest paging(HttpServletRequest request) {
        String pageNo = request.getParameter("pageNo");
        Integer record=noteService.queryNoteTotalPage();
        Integer pageTotal = record % paging.getPageSize()>0 ? record/4+1:record/4;
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
        Integer begin=(paging.getPageNo()-1)*paging.getPageSize();
        List<Note> notes = noteService.queryNotePaging(begin, paging.getPageSize());
        if(pageTotal==0){
            pageTotal=1;
        }
        request.setAttribute("record",record);
        request.setAttribute("pageTotal",pageTotal);
        request.setAttribute("note",notes);
        request.setAttribute("pageNo",paging.getPageNo());
        return request;
    }
    public HttpServletRequest paging(HttpServletRequest request,String zoomName) {
        String pageNo = request.getParameter("pageNo");
        Integer record=noteService.queryNoteTotalPage(zoomName);
        Integer pageTotal = record % paging.getPageSize()>0 ? record/4+1:record/4;
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
        Integer begin=(paging.getPageNo()-1)*paging.getPageSize();
        List<Note> notes = noteService.queryNotePaging(begin, paging.getPageSize(),zoomName);
        if(pageTotal==0){
            pageTotal=1;
        }
        request.setAttribute("record",record);
        request.setAttribute("pageTotal",pageTotal);
        request.setAttribute("note",notes);
        request.setAttribute("pageNo",paging.getPageNo());
        return request;
    }
    public HttpServletRequest paging(HttpServletRequest request, String tag, HttpServletResponse response){
        String pageNo = request.getParameter("pageNo");
        Integer record=noteService.queryNoteTotalPageSearchByTag(tag);
        Integer pageTotal = record % paging.getPageSize()>0 ? record/4+1:record/4;
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
        Integer begin=(paging.getPageNo()-1)*paging.getPageSize();
        List<Note> notes = noteService.queryNoteByTag(tag,begin);
        request.setAttribute("record",record);
        request.setAttribute("pageTotal",pageTotal);
        request.setAttribute("notes",notes);
        request.setAttribute("pageNo",paging.getPageNo());
        return request;
    }
    public HttpServletRequest paging(HttpServletRequest request, String root, HttpServletResponse response,Integer flag){
        String pageNo = request.getParameter("pageNo");
        ManagerService managerService=new ManagerServiceImpl();
        Integer record=managerService.queryTotalPage(root);
        Integer pageTotal = record % paging.getPageSize()>0 ? record/4+1:record/4;
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
        Integer begin=(paging.getPageNo()-1)*paging.getPageSize();
        List<Note> notes = managerService.queryNoteByAreaPaging(begin,paging.getPageSize(),root);
        request.setAttribute("record",record);
        request.setAttribute("pageTotal",pageTotal);
        request.setAttribute("notes",notes);
        request.setAttribute("pageNo",paging.getPageNo());
        return request;
    }

    @Override
    public HttpServletRequest superManagerPaging(HttpServletRequest request) {
        String pageNo = request.getParameter("pageNo");
        ManagerService managerService=new ManagerServiceImpl();
        Integer record=managerService.queryTotalPage();
        Integer pageTotal = record % paging.getPageSize()>0 ? record/4+1:record/4;
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
        Integer begin=(paging.getPageNo()-1)*paging.getPageSize();
        List<Note> notes = managerService.queryNotePage(begin, paging.getPageSize());
        if(pageTotal==0){
            pageTotal=1;
        }
        request.setAttribute("record",record);
        request.setAttribute("pageTotal",pageTotal);
        request.setAttribute("notes",notes);
        request.setAttribute("pageNo",paging.getPageNo());
        return request;
    }

    @Override
    public void loginPage(HttpServletRequest request, HttpServletResponse response) {
        UserService userService=new UserServiceImpl();
        String username = request.getParameter("username");

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
    }

    @Override
    public void searchResult(HttpServletRequest request, HttpServletResponse response) {
        String username=request.getParameter("username");

        String tag=request.getParameter("tag");
        UserService userService=new UserServiceImpl();
        User user = userService.queryUserByUserName(username);
        request.setAttribute("root",user.getRoot());
        PagingService pagingService=new PagingServiceImpl();
        HttpServletRequest req = pagingService.paging(request, tag, response);
        req.setAttribute("username",username);

        req.setAttribute("tag",tag);
        try {
            req.getRequestDispatcher("/notebook/search.jsp").forward(request,response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void findPage(HttpServletRequest request, HttpServletResponse response) {
        PagingService pagingService=new PagingServiceImpl();
        UserService userService=new UserServiceImpl();
        LikeActService likeActService=new LikeActServiceImpl();
        CollectService collectService=new CollectServiceImpl();
        CommentService commentService=new CommentServiceImpl();
        String action = request.getParameter("action");
        String username = request.getParameter("username");

        if(action.equals("游戏区")||action.equals("美食区")||action.equals("学习区")||action.equals("动漫区")||action.equals("科技区")){
            HttpServletRequest req = pagingService.paging(request,action);
            User user = userService.queryUserByUserName(username);
            req.setAttribute("root",user.getRoot());
            req.setAttribute("zoomName",action);
            req.setAttribute("username",username);

            try {
                req.getRequestDispatcher("/notebook/zoom.jsp").forward(request,response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }else if(action.equals("个人主页")){
            //查找对应的小红书号：
            request.setAttribute("userNumber",userService.queryUserByUserName(username).getUserNumber());
            request.setAttribute("username",username);
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
            //被管理员删掉的笔记
            List<Note> notes7 = noteService.checkDeleteNote(notes);
            request.setAttribute("notes7",notes7);
            User user = userService.queryUserByUserName(username);
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


            try {
                request.getRequestDispatcher("/User/page/home.jsp").forward(request,response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
