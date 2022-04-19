package com.cjj.www.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface PagingService {
    //分页的业务编写
    HttpServletRequest paging(HttpServletRequest httpServletRequest);
    HttpServletRequest paging(HttpServletRequest httpServletRequest,String zoomName);
    HttpServletRequest paging(HttpServletRequest request, String tag, HttpServletResponse response);
    HttpServletRequest paging(HttpServletRequest request, String root, HttpServletResponse response,Integer flag);
    HttpServletRequest superManagerPaging(HttpServletRequest request);
}
