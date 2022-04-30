package com.cjj.www.controller;

import com.cjj.www.service.ManagerService;
import com.cjj.www.service.ManagerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/manager/*")
public class ManagerServlet extends BaseServlet{
         ManagerService managerService=new ManagerServiceImpl();

    public void chargeNote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        managerService.chargeNote(request,response);
    }
    public void reNote(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        managerService.reNote(request,response);
    }
    public void chargeUser(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        managerService.chargeUser(request,response);
    }
    public void setUserStatus(HttpServletRequest request,HttpServletResponse response)  {
        managerService.setUserStatus(request,response);
    }
    public void chargeNoteBatch(HttpServletRequest request,HttpServletResponse response){
        managerService.chargeNoteBatch(request,response);
    }
    public void chargeNoteBatchs(HttpServletRequest request,HttpServletResponse response) {
        managerService.chargeNoteBatchs(request,response);
    }
    public void dealReportedNote(HttpServletRequest request,HttpServletResponse response){
        managerService.dealReportNote(request,response);
    }public void dealingReportNote(HttpServletRequest request,HttpServletResponse response){
        managerService.dealingReportNote(request,response);
    }
    public void chargeAllNote(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        managerService.chargeAllNote(request,response);
    }
    public void deleteNote(HttpServletRequest request,HttpServletResponse response){
        managerService.deleteNote(request,response);
    }
    public void batchDeleteNote(HttpServletRequest request,HttpServletResponse response){
        managerService.batchDeleteNote(request,response);
    }
    public void confirm(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
       managerService.confirm(request,response);
    }
    public void chargeManagerUser(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        managerService.chargeManagerUser(request,response);
    }
    public void resetManager(HttpServletRequest request,HttpServletResponse response){
        managerService.setManager(request,response);
    }
    public void changeUser(HttpServletRequest request,HttpServletResponse response) {
        managerService.changeUser(request,response);
    }
    public void notice(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        managerService.notice(request,response);
    }
    public void publishNotice(HttpServletRequest request,HttpServletResponse response) {
        managerService.publishNote(request,response);
    }
}
