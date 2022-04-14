package com.cjj.www.service;

import com.cjj.www.pojo.Note;
import com.cjj.www.pojo.Paging;
import com.cjj.www.util.WebUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
}
