package com.cjj.www.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface PagingService {
    /**
     *
     * @param httpServletRequest 对应的servlet的req
     * @return req：传递给其它方法继续执行操作
     */
    HttpServletRequest paging(HttpServletRequest httpServletRequest);

    /**
     *
     * @param httpServletRequest 对应servlet的req
     * @param zoomName 对应的区域名
     * @return req：传递给其它方法继续执行分页操作
     */
    HttpServletRequest paging(HttpServletRequest httpServletRequest,String zoomName);

    /**
     *
     * @param request 对应servlet的req
     * @param tag 对应的区域名
     * @param response 对应servlet的resp
     * @return req ：传递给其它方法继续执行分页操作
     */
    HttpServletRequest paging(HttpServletRequest request, String tag, HttpServletResponse response);

    /**
     *
     * @param request 对应servlet的req
     * @param root 对应管理区的名称
     * @param response 对应servlet的resp
     * @param flag 用于方法重载，无实际意义
     * @return req：传递给其它方法继续执行操作
     */
    HttpServletRequest paging(HttpServletRequest request, String root, HttpServletResponse response,Integer flag);

    /**
     *
     * @param request 对应servlet的req
     * @return req：传递给其它方法继续执行操作
     */
    HttpServletRequest superManagerPaging(HttpServletRequest request);

    /**
     *
     * @param request 对应servlet的req
     * @param response 对应servlet的resp
     */
    void loginPage(HttpServletRequest request,HttpServletResponse response);

    /**
     *
     * @param request 对应servlet的req
     * @param response 对应servlet的resp
     */
    void searchResult(HttpServletRequest request,HttpServletResponse response);
    /**
     *
     * @param request 对应servlet的req
     * @param response 对应servlet的resp
     */
    void findPage(HttpServletRequest request,HttpServletResponse response);
}
