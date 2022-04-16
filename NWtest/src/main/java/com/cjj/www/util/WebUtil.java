package com.cjj.www.util;

import javax.servlet.http.Cookie;

public class WebUtil {
    /*
    将传过来的字符串参数转化为整数
    编写toInteger
     */
    public static Integer toInteger(String toIntegerId){
        int Allid=0;
        int count=0;
        for (int i=toIntegerId.length()-1;i>=0;i--){
            int i1 = toIntegerId.charAt(i)-48;
            Allid+= (int) (Math.pow(10,count++)*i1);
        }
        return Allid;
    }
    public static Cookie findCookieByName(Cookie[] cookies,String name){
        for (Cookie cookie:cookies){
            if(name.equals(cookie.getName())){
                return cookie;
            }
        }
        return null;
    }
}
